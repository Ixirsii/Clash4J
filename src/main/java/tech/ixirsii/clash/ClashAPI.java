package tech.ixirsii.clash;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import reactor.core.publisher.Mono;
import tech.ixirsii.clash.data.ClientError;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.exception.BadRequestException;
import tech.ixirsii.clash.exception.ClientException;
import tech.ixirsii.clash.exception.DeserializationException;
import tech.ixirsii.clash.exception.ForbiddenException;
import tech.ixirsii.clash.exception.InternalServerErrorException;
import tech.ixirsii.clash.exception.NotFoundException;
import tech.ixirsii.clash.exception.RequestException;
import tech.ixirsii.clash.exception.ServiceUnavailableException;
import tech.ixirsii.clash.exception.TooManyRequestException;
import tech.ixirsii.clash.internal.APICookieJar;

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
    private static final String BASE_URL = "https://api.clashofclans.com/v1/";

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
        this(new OkHttpClient.Builder().cookieJar(new APICookieJar()).build(), token);
    }

    /**
     * Construct a new {@link ClashAPI} with the default mapper.
     *
     * @param client HTTP client.
     * @param token API token.
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
     * @param limit (Optional) Result size limit.
     * @param after (Optional) Get page after key.
     * @param before (Optional) Get page before key.
     * @return Clan's capital raid seasons.
     */
    public Mono<CapitalRaidSeason> capitalRaidSeason(
            @NonNull final String clanTag,
            final Integer limit,
            final String after,
            final String before) {
        log.trace("Getting capital raid seasons for clan {}", clanTag);

        final Map<String, String> queryParameters = getPaginationQueryParameters(limit, after, before);

        return get(
                "/clans/" + formatTag(clanTag) + "/capitalraidseasons",
                queryParameters,
                CapitalRaidSeason.class);
    }

    /* ********************************************************************************************************** *
     *                                                 Player APIs                                                *
     * ********************************************************************************************************** */

    /* ********************************************************************************************************** *
     *                                                 League APIs                                                *
     * ********************************************************************************************************** */

    /* ********************************************************************************************************** *
     *                                               Location APIs                                                *
     * ********************************************************************************************************** */

    /* ********************************************************************************************************** *
     *                                               Gold Pass APIs                                               *
     * ********************************************************************************************************** */

    /* ********************************************************************************************************** *
     *                                                 Label APIs                                                 *
     * ********************************************************************************************************** */

    /* ********************************************************************************************************** *
     *                                          Private utility functions                                         *
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
     * @param path API path to append to {@link ClashAPI#BASE_URL}.
     * @param queryParameters Query parameters.
     * @param type Response class.
     * @param <T>  Response type.
     * @return API response mono.
     */
    private <T> Mono<T> get(
            @NonNull final String path,
            @NonNull final Map<String, String> queryParameters,
            @NonNull final Class<T> type) {
        log.trace("Making GET request: \"{}\" ? {}", path, queryParameters);

        final Request.Builder requestBuilder = getBaseRequest(path, queryParameters);

        return getResponse(requestBuilder.build(), type);
    }

    /**
     * Get the base request builder.
     *
     * @param path API path.
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
     * @param limit (Optional) Result size limit.
     * @param after (Optional) Get page after key.
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

            return queryParameters;
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
        log.trace("Making API request: {} {}", request.method(), request.url());

        return Mono.fromCallable(() -> {
            final Call call = client.newCall(request);

            try (Response response = call.execute()) {
                if (response.isSuccessful()) {
                    log.debug("Received successful response");

                    return mapper.readValue(response.body().string(), type);
                } else {
                    log.warn("Received error response: {} - \"{}\"", response.code(), response.message());

                    final ClientError error = mapper.readValue(response.body().string(), ClientError.class);

                    if (response.code() == 400) {
                        throw new BadRequestException("Bad request", error);
                    } else if (response.code() == 403) {
                        throw new ForbiddenException("Forbidden", error);
                    } else if (response.code() == 404) {
                        throw new NotFoundException("Not found", error);
                    } else if (response.code() == 429) {
                        throw new TooManyRequestException("Too many requests", error);
                    } else if (response.code() == 500) {
                        throw new InternalServerErrorException("Internal server error", error);
                    } else if (response.code() == 503) {
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
