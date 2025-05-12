package tech.ixirsii.clash;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import reactor.core.publisher.Mono;
import tech.ixirsii.clash.data.ClientError;
import tech.ixirsii.clash.data.Label;
import tech.ixirsii.clash.data.Page;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.data.clan.Clan;
import tech.ixirsii.clash.data.clan.ClanMember;
import tech.ixirsii.clash.data.goldpass.GoldPassSeason;
import tech.ixirsii.clash.data.location.Location;
import tech.ixirsii.clash.data.clan.WarFrequency;
import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.CapitalLeague;
import tech.ixirsii.clash.data.league.ClanWarLeagueGroup;
import tech.ixirsii.clash.data.league.ClanWarLeagueWar;
import tech.ixirsii.clash.data.league.League;
import tech.ixirsii.clash.data.league.LeagueSeason;
import tech.ixirsii.clash.data.league.PlayerRanking;
import tech.ixirsii.clash.data.league.WarLeague;
import tech.ixirsii.clash.data.location.ClanBuilderBaseRanking;
import tech.ixirsii.clash.data.location.ClanCapitalRanking;
import tech.ixirsii.clash.data.location.ClanRanking;
import tech.ixirsii.clash.data.location.PlayerBuilderBaseRanking;
import tech.ixirsii.clash.data.player.Player;
import tech.ixirsii.clash.data.player.TokenResponse;
import tech.ixirsii.clash.data.war.War;
import tech.ixirsii.clash.data.war.WarLogEntry;
import tech.ixirsii.clash.exception.BadRequestException;
import tech.ixirsii.clash.exception.ClientException;
import tech.ixirsii.clash.exception.DeserializationException;
import tech.ixirsii.clash.exception.ForbiddenException;
import tech.ixirsii.clash.exception.InternalServerErrorException;
import tech.ixirsii.clash.exception.NotFoundException;
import tech.ixirsii.clash.exception.RequestException;
import tech.ixirsii.clash.exception.ServiceUnavailableException;
import tech.ixirsii.clash.exception.TooManyRequestException;
import tech.ixirsii.clash.internal.Deserializer;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clash of Clans API.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
@Slf4j
public class ClashAPI {
    /**
     * Base URL for Clash of Clans API.
     */
    private static final String BASE_URL = "https://api.clashofclans.com/v1";

    /**
     * HTTP client.
     */
    private final OkHttpClient client;
    /**
     * Object mapper for (de)serialization.
     */
    private final ObjectMapper mapper;
    /**
     * API token.
     */
    private final String token;

    /**
     * Construct a new {@link ClashAPI} with the default client and mapper.
     *
     * @param token API token.
     */
    public ClashAPI(@NonNull final String token) {
        this(new OkHttpClient(), token);
    }

    /**
     * Construct a new {@link ClashAPI} with the default mapper.
     *
     * @param client HTTP client.
     * @param token  API token.
     */
    public ClashAPI(@NonNull final OkHttpClient client, @NonNull final String token) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSX");

        this.client = client;
        this.mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule()
                        .addDeserializer(
                                ZonedDateTime.class,
                                new InstantDeserializer<>(InstantDeserializer.ZONED_DATE_TIME, formatter) {
                                }))
                .addModule(new Jdk8Module())
                .addModule(new ParameterNamesModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        this.token = token;
    }

    /* ********************************************************************************************************** *
     *                                                  Clan APIs                                                 *
     * ********************************************************************************************************** */

    /**
     * Get clan's capital raid seasons.
     *
     * @param clanTag Clan tag.
     * @param limit   (Optional) Result size limit.
     * @param after   (Optional) Get page after key.
     * @param before  (Optional) Get page before key.
     * @return Clan's capital raid seasons.
     */
    public Mono<Page<CapitalRaidSeason>> capitalRaidSeasons(
            @NonNull final String clanTag,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting capital raid seasons for clan {}", clanTag);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/clans/" + formatTag(clanTag) + "/capitalraidseasons", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan.
     *
     * @param clanTag Clan tag.
     * @return Clan information.
     */
    public Mono<Clan> clan(@NonNull final String clanTag) {
        log.trace("Getting clan {}", clanTag);

        return get("/clans/" + formatTag(clanTag), Clan.class);
    }

    /**
     * Search for clans.
     *
     * @param name          Clan name.
     * @param warFrequency  War frequency.
     * @param locationId    Location ID.
     * @param minMembers    Minimum number of members.
     * @param maxMembers    Maximum number of members.
     * @param minClanPoints Minimum clan points.
     * @param minClanLevel  Minimum clan level.
     * @param labelIds      Comma-separated list of label IDs.
     * @param limit         (Optional) Result size limit.
     * @param after         (Optional) Get page after key.
     * @param before        (Optional) Get page before key.
     * @return Clan search results.
     */
    public Mono<Page<Clan>> clans(
            final String name,
            final WarFrequency warFrequency,
            final Integer locationId,
            final Integer minMembers,
            final Integer maxMembers,
            final Integer minClanPoints,
            final Integer minClanLevel,
            final String labelIds,
            final Integer limit,
            final String after,
            final String before) {
        final Map<String, String> queryParameters = HashMap.newHashMap(11);

        if (name != null) {
            queryParameters.put("name", name);
        }

        if (warFrequency != null) {
            queryParameters.put("warFrequency", warFrequency.getValue());
        }

        if (locationId != null) {
            queryParameters.put("locationId", locationId.toString());
        }

        if (minMembers != null) {
            queryParameters.put("minMembers", minMembers.toString());
        }

        if (maxMembers != null) {
            queryParameters.put("maxMembers", maxMembers.toString());
        }

        if (minClanPoints != null) {
            queryParameters.put("minClanPoints", minClanPoints.toString());
        }

        if (minClanLevel != null) {
            queryParameters.put("minClanLevel", minClanLevel.toString());
        }

        if (labelIds != null) {
            queryParameters.put("labelIds", labelIds);
        }

        if (limit != null) {
            queryParameters.put("limit", limit.toString());
        }

        if (after != null) {
            queryParameters.put("after", after);
        }

        if (before != null) {
            queryParameters.put("before", before);
        }

        return get("/clans", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan's current war.
     *
     * @param clanTag Clan tag.
     * @return Clan's current war.
     */
    public Mono<War> currentWar(@NonNull final String clanTag) {
        log.trace("Getting current war for clan {}", clanTag);

        return get("/clans/" + formatTag(clanTag) + "/currentwar", War.class);
    }

    /**
     * Get clan's league group.
     *
     * @param clanTag Clan tag.
     * @return Clan's league group.'
     */
    public Mono<ClanWarLeagueGroup> leagueGroup(@NonNull final String clanTag) {
        log.trace("Getting league group for clan {}", clanTag);

        return get("/clans/" + formatTag(clanTag) + "/currentwar/leaguegroup", ClanWarLeagueGroup.class);
    }

    /**
     * Get an individual Clan War League war.
     *
     * @param warTag War tag.
     * @return Clan War League war.
     */
    public Mono<ClanWarLeagueWar> leagueWar(@NonNull final String warTag) {
        log.trace("Getting league war {}", warTag);

        return get("/clanwarleagues/wars/" + formatTag(warTag), ClanWarLeagueWar.class);
    }

    /**
     * Get clan members.
     *
     * @param clanTag Clan tag.
     * @param limit   (Optional) Result size limit.
     * @param after   (Optional) Get page after key.
     * @param before  (Optional) Get page before key.
     * @return Clan members.
     */
    public Mono<Page<ClanMember>> members(
            @NonNull final String clanTag,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting members for clan {}", clanTag);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/clans/" + formatTag(clanTag) + "/members", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan's war log.
     *
     * @param clanTag Clan tag.
     * @param limit   (Optional) Result size limit.
     * @param after   (Optional) Get page after key.
     * @param before  (Optional) Get page before key.
     * @return War log.
     */
    public Mono<Page<WarLogEntry>> warLog(
            @NonNull final String clanTag,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting war log for clan {}", clanTag);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/clans/" + formatTag(clanTag) + "/warlog", queryParameters, new TypeReference<>() {
        });
    }

    /* ********************************************************************************************************** *
     *                                                 Player APIs                                                *
     * ********************************************************************************************************** */

    /**
     * Get player.
     *
     * @param playerTag Player tag.
     * @return Player information.
     */
    public Mono<Player> player(@NonNull final String playerTag) {
        log.trace("Getting player {}", playerTag);

        return get("/players/" + formatTag(playerTag), Player.class);
    }

    /**
     * Verify player API token.
     *
     * @param playerTag Player tag.
     * @param token API token.
     * @return {@code true} if the token is valid, otherwise {@code false}.
     */
    public Mono<Boolean> verifyPlayer(@NonNull final String playerTag, @NonNull final String token) {
        log.trace("Verifying player {}", playerTag);

        final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(mediaType, "{\"token\":\"" + token + "\"}");

        return post("/players/" + formatTag(playerTag) + "/verifytoken", body, TokenResponse.class)
                .map(response -> response.status().equals("ok"));
    }

    /* ********************************************************************************************************** *
     *                                                 League APIs                                                *
     * ********************************************************************************************************** */

    /**
     * Get builder base league.
     *
     * @param leagueID League ID.
     * @return Builder base league information.
     */
    public Mono<BuilderBaseLeague> builderBaseLeague(final int leagueID) {
        log.trace("Getting builder base league {}", leagueID);

        return get("/builderbaseleagues/" + leagueID, BuilderBaseLeague.class);
    }

    /**
     * Get builder base leagues.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Builder base leagues.
     */
    public Mono<Page<BuilderBaseLeague>> builderBaseLeagues(
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting builder base leagues");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/builderbaseleagues", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan capital league.
     *
     * @param leagueID League ID.
     * @return Clan capital league information.
     */
    public Mono<CapitalLeague> capitalLeague(final int leagueID) {
        log.trace("Getting clan capital league {}", leagueID);

        return get("/capitalleagues/" + leagueID, CapitalLeague.class);
    }

    /**
     * Get clan capital leagues.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Clan capital leagues.
     */
    public Mono<Page<CapitalLeague>> capitalLeagues(
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting clan capital leagues");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/capitalleagues", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get league.
     *
     * @param leagueID League ID.
     * @return League information.
     */
    public Mono<League> league(final int leagueID) {
        log.trace("Getting league {}", leagueID);

        return get("/leagues/" + leagueID, League.class);
    }

    /**
     * Get leagues.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Leagues.
     */
    public Mono<Page<League>> leagues(
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting leagues");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/leagues", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get monthly (seasonal) rankings for a league.
     *
     * @param leagueID League ID.
     * @param seasonID Season ID.
     * @param limit    (Optional) Result size limit.
     * @param after    (Optional) Get page after key.
     * @param before   (Optional) Get page before key.
     * @return Monthly (seasonal) rankings for a league.
     */
    public Mono<Page<PlayerRanking>> leagueSeason(
            final int leagueID,
            @NonNull final String seasonID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting league {} season {}", leagueID, seasonID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/leagues/" + leagueID + "/seasons/" + seasonID, queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get months (seasons) for a league.
     *
     * @param leagueID League ID.
     * @param limit    (Optional) Result size limit.
     * @param after    (Optional) Get page after key.
     * @param before   (Optional) Get page before key.
     * @return Months (seasons) for a league.
     */
    public Mono<Page<LeagueSeason>> leagueSeasons(
            final int leagueID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting league {} seasons", leagueID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/leagues/" + leagueID + "/seasons", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan war league.
     *
     * @param leagueID League ID.
     * @return Clan war league information.
     */
    public Mono<WarLeague> warLeague(final int leagueID) {
        log.trace("Getting clan war league {}", leagueID);

        return get("/warleagues/" + leagueID, WarLeague.class);
    }

    /**
     * Get clan war leagues.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Clan war leagues.
     */
    public Mono<Page<WarLeague>> warLeagues(
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting clan war leagues");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/warleagues", queryParameters, new TypeReference<>() {
        });
    }

    /* ********************************************************************************************************** *
     *                                               Location APIs                                                *
     * ********************************************************************************************************** */

    /**
     * Get clan builder base rankings for a location.
     *
     * @param locationID Location ID.
     * @param limit      (Optional) Result size limit.
     * @param after      (Optional) Get page after key.
     * @param before     (Optional) Get page before key.
     * @return Clan builder base rankings for a location.
     */
    public Mono<Page<ClanBuilderBaseRanking>> clanBuilderBaseRankings(
            final int locationID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting clan builder base rankings for location {}", locationID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/locations/" + locationID + "/rankings/clans-builder-base", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan capital rankings for a location.
     *
     * @param locationID Location ID.
     * @param limit      (Optional) Result size limit.
     * @param after      (Optional) Get page after key.
     * @param before     (Optional) Get page before key.
     * @return Clan capital rankings for a location.
     */
    public Mono<Page<ClanCapitalRanking>> clanCapitalRankings(
            final int locationID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting clan capital rankings for location {}", locationID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/locations/" + locationID + "/rankings/capitals", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get clan rankings for a location.
     *
     * @param locationID Location ID.
     * @param limit      (Optional) Result size limit.
     * @param after      (Optional) Get page after key.
     * @param before     (Optional) Get page before key.
     * @return Clan rankings for a location.
     */
    public Mono<Page<ClanRanking>> clanRankings(
            final int locationID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting clan rankings for location {}", locationID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/locations/" + locationID + "/rankings/clans", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get location.
     *
     * @param locationID Location ID.
     * @return Location information.
     */
    public Mono<Location> location(final int locationID) {
        log.trace("Getting location {}", locationID);

        return get("/locations/" + locationID, Location.class);
    }

    /**
     * Get locations.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Locations.
     */
    public Mono<Page<Location>> locations(final Integer limit, final String after, final String before) {
        log.trace("Getting locations");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/locations", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get player builder base rankings for a location.
     *
     * @param locationID Location ID.
     * @param limit      (Optional) Result size limit.
     * @param after      (Optional) Get page after key.
     * @param before     (Optional) Get page before key.
     * @return Player builder base rankings for a location.
     */
    public Mono<Page<PlayerBuilderBaseRanking>> playerBuilderBaseRanking(
            final int locationID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting player builder base ranking for location {}", locationID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get(
                "/locations/" + locationID + "/rankings/players-builder-base",
                queryParameters,
                new TypeReference<>() {
                });
    }

    /**
     * Get player rankings for a location.
     *
     * @param locationID Location ID.
     * @param limit      (Optional) Result size limit.
     * @param after      (Optional) Get page after key.
     * @param before     (Optional) Get page before key.
     * @return Player rankings for a location.
     */
    public Mono<Page<tech.ixirsii.clash.data.location.PlayerRanking>> playerRankings(
            final int locationID,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting player rankings for location {}", locationID);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/locations/" + locationID + "/rankings/players", queryParameters, new TypeReference<>() {
        });
    }

    /* ********************************************************************************************************** *
     *                                               Gold Pass APIs                                               *
     * ********************************************************************************************************** */

    /**
     * Get gold pass season.
     *
     * @return Current gold pass season.
     */
    public Mono<GoldPassSeason> goldPassSeason() {
        log.trace("Getting gold pass season");

        return get("/goldpass/seasons/current", GoldPassSeason.class);
    }

    /* ********************************************************************************************************** *
     *                                                 Label APIs                                                 *
     * ********************************************************************************************************** */

    /**
     * Get clan labels.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Clan labels.
     */
    public Mono<Page<Label>> clanLabels(final Integer limit, final String after, final String before) {
        log.trace("Getting clan labels");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/labels/clans", queryParameters, new TypeReference<>() {
        });
    }

    /**
     * Get player labels.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Player labels.
     */
    public Mono<Page<Label>> playerLabels(final Integer limit, final String after, final String before) {
        log.trace("Getting player labels");

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get("/labels/players", queryParameters, new TypeReference<>() {
        });
    }

    /* ********************************************************************************************************** *
     *                                           Private utility methods                                          *
     * ********************************************************************************************************** */

    /**
     * HTTP encode a string.
     *
     * @param value Value to encode.
     * @return UTF-8 HTTP encoded value.
     */
    private String encode(@NonNull final String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    /**
     * HTTP encode a URL.
     *
     * @param endpoint        Base URL.
     * @param queryParameters Query parameters.
     * @return HTTP encoded URL.
     */
    private String encodeUrl(@NonNull final String endpoint, @NonNull final Map<String, String> queryParameters) {
        return queryParameters.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&", endpoint + "?", ""));
    }

    /**
     * Format a tag with a leading #.
     *
     * @param tag Tag (clan, player, etc.).
     * @return Formatted tag.
     */
    private String formatTag(@NonNull final String tag) {
        final String fullTag;

        if (tag.startsWith("#")) {
            fullTag = tag;
        } else {
            fullTag = "#" + tag;
        }

        return encode(fullTag);
    }

    /**
     * Make a GET request.
     *
     * @param path API path to append to {@link ClashAPI#BASE_URL}.
     * @param type Response class.
     * @param <T>  Response type.
     * @return API response mono.
     */
    private <T> Mono<T> get(@NonNull final String path, @NonNull final Class<T> type) {
        log.trace("Making GET request: \"{}\"", path);

        final Request.Builder requestBuilder = getBaseRequest(path, Collections.emptyMap());

        return getResponse(requestBuilder.build(), type);
    }

    /**
     * Make a GET request.
     *
     * @param path            API path to append to {@link ClashAPI#BASE_URL}.
     * @param queryParameters Query parameters.
     * @param type            Response class.
     * @param <T>             Response type.
     * @return API response mono.
     */
    private <T> Mono<T> get(
            @NonNull final String path,
            @NonNull final Map<String, String> queryParameters,
            @NonNull final TypeReference<T> type) {
        log.trace("Making GET request for type reference: \"{}\" ? {}", path, queryParameters);

        final Request.Builder requestBuilder = getBaseRequest(path, queryParameters);

        return getResponse(requestBuilder.build(), type);
    }

    /**
     * Get the base request builder.
     *
     * @param path            API path.
     * @param queryParameters Query parameters.
     * @return Base request builder.
     */
    private Request.Builder getBaseRequest(
            @NonNull final String path,
            @NonNull final Map<String, String> queryParameters) {
        final String url = BASE_URL + encodeUrl(path, queryParameters);

        return new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(url);
    }

    /**
     * Get pagination query parameters.
     *
     * @param limit  (Optional) Result size limit.
     * @param after  (Optional) Get page after key.
     * @param before (Optional) Get page before key8.
     * @return Pagination query parameters.
     */
    private Map<String, String> getPaginationQueryParameters(
            final Integer limit,
            final String after,
            final String before) {
        if (limit == null && after == null && before == null) {
            return Collections.emptyMap();
        } else {
            final Map<String, String> queryParameters = HashMap.newHashMap(3);

            if (limit != null) {
                queryParameters.put("limit", limit.toString());
            }

            if (after != null) {
                queryParameters.put("after", after);
            }

            if (before != null) {
                queryParameters.put("before", before);
            }

            return Collections.unmodifiableMap(queryParameters);
        }
    }

    /**
     * Make an API call.
     *
     * @param request API request.
     * @param type    Response class.
     * @param <T>     Response type.
     * @return API response mono.
     */
    private <T> Mono<T> getResponse(@NonNull final Request request, @NonNull final Class<T> type) {
        return getResponse(request, body -> mapper.readValue(body, type));
    }

    /**
     * Make an API call.
     *
     * @param request API request.
     * @param type    Response class.
     * @param <T>     Response type.
     * @return API response mono.
     */
    private <T> Mono<T> getResponse(@NonNull final Request request, @NonNull final TypeReference<T> type) {
        return getResponse(request, body -> mapper.readValue(body, type));
    }

    /**
     * Make an API call.
     *
     * @param request      API request.
     * @param deserializer Response body deserializer.
     * @param <T>          Response type.
     * @return API response mono.
     */
    private <T> Mono<T> getResponse(@NonNull final Request request, @NonNull final Deserializer<T> deserializer) {
        log.trace("Making API request: {} {}", request.method(), request.url());

        return Mono.fromCallable(() -> {
            final Call call = client.newCall(request);

            try (Response response = call.execute()) {
                if (response.isSuccessful()) {
                    log.debug("Received successful response");

                    return deserializer.deserialize(response.body().string());
                } else {
                    final int code = response.code();

                    log.warn("Received error response: {} - \"{}\"", code, response.message());

                    final ClientError error = mapper.readValue(response.body().string(), ClientError.class);

                    if (code == 400) {
                        throw new BadRequestException("Bad request", error);
                    } else if (code == 403) {
                        throw new ForbiddenException("Forbidden", error);
                    } else if (code == 404) {
                        throw new NotFoundException("Not found", error);
                    } else if (code == 429) {
                        throw new TooManyRequestException("Too many requests", error);
                    } else if (code == 500) {
                        throw new InternalServerErrorException("Internal server error", error);
                    } else if (code == 503) {
                        throw new ServiceUnavailableException("Service unavailable", error);
                    } else {
                        throw new ClientException("Unexpected status code", error);
                    }
                }
            } catch (final JsonProcessingException e) {
                log.error("Caught exception deserializing response", e);

                throw new DeserializationException(e.getMessage(), e);
            } catch (final IOException e) {
                log.error("Caught exception executing request", e);

                throw new RequestException(e.getMessage(), e);
            }
        });
    }

    /**
     * Make a POST request.
     *
     * @param path API path to append to {@link ClashAPI#BASE_URL}.
     * @param body Request body.
     * @param type Response class.
     * @param <T>  Response type.
     * @return API response mono.
     */
    private <T> Mono<T> post(
            @NonNull final String path,
            @NonNull final RequestBody body,
            @NonNull final Class<T> type) {
        log.trace("Making POST request: \"{}\"", path);

        final Request.Builder requestBuilder = getBaseRequest(path, Collections.emptyMap()).post(body);

        return getResponse(requestBuilder.build(), type);
    }
}
