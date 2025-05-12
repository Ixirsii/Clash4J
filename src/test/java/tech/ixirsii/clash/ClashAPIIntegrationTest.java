package tech.ixirsii.clash;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tech.ixirsii.clash.data.Page;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.data.clan.Clan;
import tech.ixirsii.clash.data.clan.ClanMember;
import tech.ixirsii.clash.data.clan.WarFrequency;
import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.CapitalLeague;
import tech.ixirsii.clash.data.league.ClanWarLeagueGroup;
import tech.ixirsii.clash.data.league.ClanWarLeagueWar;
import tech.ixirsii.clash.data.league.League;
import tech.ixirsii.clash.data.league.LeagueSeason;
import tech.ixirsii.clash.data.league.PlayerRanking;
import tech.ixirsii.clash.data.league.WarLeague;
import tech.ixirsii.clash.data.player.Player;
import tech.ixirsii.clash.data.war.War;
import tech.ixirsii.clash.data.war.WarLogEntry;
import tech.ixirsii.clash.exception.BadRequestException;
import tech.ixirsii.clash.exception.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.ixirsii.clash.TestData.PLAYER_TAG;

@Tag("integration")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClashAPIIntegrationTest {
    private static final String CLAN_TAG = "#2Q82UJVY";
    private static final int LOCATION_ID = 32000249;
    private static final String PLAYER_ID = "#2Q09RPGL8";

    private final ClashAPI api;

    ClashAPIIntegrationTest() {
        api = new ClashAPI(System.getenv("API_TOKEN"));
    }

    /* *********************************************** Clan APIs ************************************************ */

    @Test
    void GIVEN_clan_tag_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons() {
        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Capital raid seasons should not be empty");
    }

    @Test
    void GIVEN_clan_tag_WHEN_clan_THEN_returns_clan() {
        // When
        final Clan actual = api.clan(CLAN_TAG).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void GIVEN_clan_tag_without_pound_WHEN_clan_THEN_returns_clan() {
        // When
        final Clan actual = api.clan(CLAN_TAG.substring(1)).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void GIVEN_name_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_warFrequency_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final WarFrequency warFrequency = WarFrequency.MORE_THAN_ONCE_PER_WEEK;
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        warFrequency,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_locationId_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        LOCATION_ID,
                        null,
                        null,
                        null,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_minMembers_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int minMembers = 10;
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        minMembers,
                        null,
                        null,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_maxMembers_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int maxMembers = 50;
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        null,
                        maxMembers,
                        null,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_minClanPoints_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int minClanPoints = 1000;
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        null,
                        null,
                        minClanPoints,
                        null,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_minClanLevel_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final int minClanLevel = 10;
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        null,
                        null,
                        null,
                        minClanLevel,
                        null,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_labels_WHEN_clans_THEN_returns_clans() {
        // Given
        final String name = "midwest warrior";
        final String labelIds = "56000000,56000004,56000016";
        final int limit = 5;

        // When
        final Page<Clan> actual = api.clans(
                        name,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        labelIds,
                        limit,
                        null,
                        null)
                .block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clans should not be empty");
    }

    @Test
    void GIVEN_clan_tag_WHEN_currentWar_THEN_returns_currentWar() {
        // When
        final War actual = api.currentWar(CLAN_TAG).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void GIVEN_clan_tag_WHEN_leagueGroup_THEN_returns_leagueGroup() {
        // When
        final ClanWarLeagueGroup actual = api.leagueGroup(CLAN_TAG).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void GIVEN_war_tag_WHEN_leagueWar_THEN_returns_leagueWar() {
        // Given
        final ClanWarLeagueGroup group = api.leagueGroup(CLAN_TAG).block();

        // When
        final ClanWarLeagueWar actual = api.leagueWar(group.rounds().getFirst().warTags().getFirst()).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void GIVEN_clan_tag_WHEN_members_THEN_returns_members() {
        // When
        final Page<ClanMember> actual = api.members(CLAN_TAG, null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Members should not be empty");
    }

    @Test
    void GIVEN_clan_tag_WHEN_warLog_THEN_returns_war_logs() {
        // When
        final Page<WarLogEntry> actual = api.warLog(CLAN_TAG, null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "War log entries should not be empty");
    }

    /* ********************************************** Player APIs *********************************************** */

    @Test
    void GIVEN_player_tag_WHEN_player_THEN_returns_player() {
        // When
        final Player actual = api.player(PLAYER_TAG).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    /* ********************************************** League APIs *********************************************** */

    @Test
    void GIVEN_league_ID_WHEN_builderBaseLeague_THEN_returns_builder_base_league() {
        // Given
        final int leagueId = 44000000;

        // When
        final BuilderBaseLeague actual = api.builderBaseLeague(leagueId).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void WHEN_builderBaseLeagues_THEN_returns_builder_base_leagues() {
        // When
        final Page<BuilderBaseLeague> actual = api.builderBaseLeagues(null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Builder base leagues should not be empty");
    }

    @Test
    void GIVEN_league_ID_WHEN_capitalLeague_THEN_returns_capital_league() {
        // Given
        final int leagueId = 85000001;

        // When
        final CapitalLeague actual = api.capitalLeague(leagueId).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void WHEN_capitalLeagues_THEN_returns_capital_leagues() {
        // When
        final Page<CapitalLeague> actual = api.capitalLeagues(null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Clan capital leagues should not be empty");
    }

    @Test
    void GIVEN_league_ID_WHEN_league_THEN_returns_league() {
        // Given
        final int leagueId = 29000001;

        // When
        final League actual = api.league(leagueId).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void WHEN_leagues_THEN_returns_leagues() {
        // When
        final Page<League> actual = api.leagues(null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Leagues should not be empty");
    }

    @Test
    void GIVEN_league_ID_and_season_ID_WHEN_leagueSeason_THEN_returns_player_rankings() {
        // Given
        final int leagueId = 29000022;
        final LocalDate now = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        final String seasonId = formatter.format(now);

        // When
        final Page<PlayerRanking> actual = api.leagueSeason(leagueId, seasonId, null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "Player rankings should not be empty");
    }

    @Test
    void GIVEN_league_ID_WHEN_leagueSeasons_THEN_returns_league_seasons() {
        // Given
        final int leagueId = 29000022;

        // When
        final Page<LeagueSeason> actual = api.leagueSeasons(leagueId, null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "League seasons should not be empty");
    }

    @Test
    void GIVEN_league_ID_WHEN_warLeague_THEN_returns_war_league() {
        // Given
        final int leagueId = 48000001;

        // When
        final WarLeague actual = api.warLeague(leagueId).block();

        // Then
        assertNotNull(actual, "Response should not be null");
    }

    @Test
    void WHEN_warLeagues_THEN_returns_war_leagues() {
        // When
        final Page<WarLeague> actual = api.warLeagues(null, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertFalse(actual.items().isEmpty(), "War leagues should not be empty");
    }

    /* ********************************************* Location APIs ********************************************** */

    /* ********************************************* Gold Pass APIs ********************************************* */

    /* *********************************************** Label APIs *********************************************** */

    /* ************************************************* Paging ************************************************* */

    @Test
    void GIVEN_limit_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons() {
        // Given
        final int limit = 5;

        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, limit, null, null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertNotNull(actual.paging(), "Paging should not be null");
        assertFalse(actual.items().isEmpty(), "Capital raid seasons should not be empty");
        assertTrue(actual.items().size() <= limit, "Capital raid seasons should not exceed limit");
    }

    @Test
    void GIVEN_after_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons() {
        // Given
        final int limit = 5;
        final Page<CapitalRaidSeason> first = api.capitalRaidSeasons(CLAN_TAG, limit, null, null).block();

        // When
        final Page<CapitalRaidSeason> actual =
                api.capitalRaidSeasons(CLAN_TAG, limit, first.paging().cursors().after(), null).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertNotNull(actual.paging(), "Paging should not be null");
        assertFalse(actual.items().isEmpty(), "Capital raid seasons should not be empty");
        assertTrue(actual.items().size() <= limit, "Capital raid seasons should not exceed limit");
    }

    @Test
    void GIVEN_before_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons() {
        // Given
        final int limit = 5;
        final Page<CapitalRaidSeason> first = api.capitalRaidSeasons(CLAN_TAG, limit, null, null).block();
        final Page<CapitalRaidSeason> second =
                api.capitalRaidSeasons(CLAN_TAG, limit, first.paging().cursors().after(), null).block();

        // When
        final Page<CapitalRaidSeason> actual =
                api.capitalRaidSeasons(CLAN_TAG, limit, null, second.paging().cursors().before()).block();

        // Then
        assertNotNull(actual, "Response should not be null");
        assertNotNull(actual.items(), "Items should not be null");
        assertNotNull(actual.paging(), "Paging should not be null");
        assertFalse(actual.items().isEmpty(), "Capital raid seasons should not be empty");
        assertTrue(actual.items().size() <= limit, "Capital raid seasons should not exceed limit");
    }

    /* ********************************************** Error cases *********************************************** */

    @Test
    void GIVEN_BadRequest_WHEN_clans_THEN_throws_BadRequestException() {
        // Given
        final String name = "m";
        final int limit = 5;

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clans(
                                name,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                limit,
                                null,
                                null)
                        .block(),
                "Should throw BadRequestException wrapped in RuntimeException");

        // Then
        assertInstanceOf(BadRequestException.class, actual.getCause(), "Cause should be BadRequestException");
    }

    @Test
    void GIVEN_NotFound_WHEN_clans_THEN_throws_NotFoundException() {
        // Given
        final String clanTag = "string";

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(clanTag).block(),
                "Should throw NotFoundException wrapped in RuntimeException");

        // Then
        assertInstanceOf(NotFoundException.class, actual.getCause(), "Cause should be NotFoundException");
    }
}
