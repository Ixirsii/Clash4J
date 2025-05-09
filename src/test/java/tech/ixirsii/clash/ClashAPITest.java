package tech.ixirsii.clash;

import lombok.NonNull;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ixirsii.clash.data.Cursor;
import tech.ixirsii.clash.data.Page;
import tech.ixirsii.clash.data.Paging;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.data.clan.Clan;
import tech.ixirsii.clash.data.clan.ClanMember;
import tech.ixirsii.clash.data.clan.WarFrequency;
import tech.ixirsii.clash.data.league.ClanWarLeagueGroup;
import tech.ixirsii.clash.data.league.ClanWarLeagueWar;
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static tech.ixirsii.clash.TestData.BAD_REQUEST_ERROR;
import static tech.ixirsii.clash.TestData.CAPITAL_RAID_SEASON;
import static tech.ixirsii.clash.TestData.CLAN;
import static tech.ixirsii.clash.TestData.CLAN_MEMBER;
import static tech.ixirsii.clash.TestData.CLAN_TAG;
import static tech.ixirsii.clash.TestData.FORBIDDEN_ERROR;
import static tech.ixirsii.clash.TestData.INTERNAL_SERVER_ERROR;
import static tech.ixirsii.clash.TestData.LEAGUE_GROUP;
import static tech.ixirsii.clash.TestData.LEAGUE_WAR;
import static tech.ixirsii.clash.TestData.METHOD_NOT_ALLOWED;
import static tech.ixirsii.clash.TestData.NOT_FOUND_ERROR;
import static tech.ixirsii.clash.TestData.SERVICE_UNAVAILABLE_ERROR;
import static tech.ixirsii.clash.TestData.TOO_MANY_REQUESTS_ERROR;
import static tech.ixirsii.clash.TestData.WAR;
import static tech.ixirsii.clash.TestData.WAR_LOG;

@ExtendWith(MockitoExtension.class)
class ClashAPITest {
    private static final String TOKEN = "token";

    @Mock
    private ResponseBody body;

    @Mock
    private Call call;

    @Mock
    private OkHttpClient client;

    @Mock
    private Response response;

    private ClashAPI api;

    @BeforeEach
    void setup() {
        api = new ClashAPI(client, TOKEN);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(body, call, client, response);
    }

    /* *********************************************** Clan APIs ************************************************ */

    @Test
    void GIVEN_clan_tag_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/capitalRaidSeason.json");

        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, null, null, null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CAPITAL_RAID_SEASON), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_clan_tag_WHEN_clan_THEN_returns_clan() throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/clan.json");

        // When
        final Clan actual = api.clan(CLAN_TAG).block();

        // Then
        verifyResponse();

        assertEquals(CLAN, actual, "Clan should equal expected");
    }

    @Test
    void GIVEN_clan_tag_without_pound_WHEN_clan_THEN_returns_clan() throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/clan.json");

        // When
        final Clan actual = api.clan(CLAN_TAG.substring(1)).block();

        // Then
        verifyResponse();

        assertEquals(CLAN, actual, "Clan should equal expected");
    }

    @Test
    void GIVEN_nothing_WHEN_clans_THEN_returns_clans()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/clans.json");

        // When
        final Page<Clan> actual = api.clans(null, null, null, null, null, null, null, null, null, null, null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CLAN), new Paging(new Cursor("eyJwb3MiOjV9", "eyJwb3MiOjV9"))),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_everything_WHEN_clans_THEN_returns_clans()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/clans.json");

        // When
        final Page<Clan> actual = api.clans(
                "midwest warrior",
                WarFrequency.MORE_THAN_ONCE_PER_WEEK,
                32000249,
                10,
                50,
                2000,
                20,
                "Clan Wars,Clan Games,Clan Capital",
                5,
                "eyJwb3MiOjV9",
                "eyJwb3MiOjV9")
                .block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CLAN), new Paging(new Cursor("eyJwb3MiOjV9", "eyJwb3MiOjV9"))),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_clan_tag_WHEN_currentWar_THEN_returns_currentWar() throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/war.json");

        // When
        final War actual = api.currentWar(CLAN_TAG).block();

        // Then
        verifyResponse();

        assertEquals(WAR, actual, "Clan should equal expected");
    }

    @Test
    void GIVEN_clan_tag_WHEN_leagueGroup_THEN_returns_leagueGroup() throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/leagueGroup.json");

        // When
        final ClanWarLeagueGroup actual = api.leagueGroup(CLAN_TAG).block();

        // Then
        verifyResponse();

        assertEquals(LEAGUE_GROUP, actual, "Clan should equal expected");
    }

    @Test
    void GIVEN_war_tag_WHEN_leagueWar_THEN_returns_leagueWar() throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/leagueWar.json");

        // When
        final ClanWarLeagueWar actual = api.leagueWar(CLAN_TAG).block();

        // Then
        verifyResponse();

        assertEquals(LEAGUE_WAR, actual, "Clan should equal expected");
    }

    @Test
    void GIVEN_clan_tag_WHEN_members_THEN_returns_members()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/members.json");

        // When
        final Page<ClanMember> actual = api.members(CLAN_TAG, null, null, null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CLAN_MEMBER), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_clan_tag_WHEN_warLog_THEN_returns_war_logs()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/warLog.json");

        // When
        final Page<WarLogEntry> actual = api.warLog(CLAN_TAG, null, null, null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(WAR_LOG), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    /* ********************************************** Player APIs *********************************************** */

    /* ********************************************** League APIs *********************************************** */

    /* ********************************************* Location APIs ********************************************** */

    /* ********************************************* Gold Pass APIs ********************************************* */

    /* *********************************************** Label APIs *********************************************** */

    /* ************************************************* Paging ************************************************* */

    @Test
    void GIVEN_limit_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/capitalRaidSeason.json");

        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, 5, null, null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CAPITAL_RAID_SEASON), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_after_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/capitalRaidSeason.json");

        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, null, "eyJwb3MiOjV9", null).block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CAPITAL_RAID_SEASON), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    @Test
    void GIVEN_before_WHEN_capitalRaidSeasons_THEN_returns_capital_raid_seasons()
            throws IOException, URISyntaxException {
        // Given
        mockResponse("/response/capitalRaidSeason.json");

        // When
        final Page<CapitalRaidSeason> actual = api.capitalRaidSeasons(CLAN_TAG, null, null, "eyJwb3MiOjV9").block();

        // Then
        verifyResponse();

        assertEquals(
                new Page<>(List.of(CAPITAL_RAID_SEASON), null),
                actual,
                "Capital raid seasons should equal expected");
    }

    /* ********************************************** Error cases *********************************************** */

    @Test
    void GIVEN_BadRequest_WHEN_clan_THEN_throws_BadRequestException() throws IOException, URISyntaxException {
        // Given
        mockError("/response/badRequest.json", 400);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw BadRequestException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof BadRequestException e) {
            assertEquals(BAD_REQUEST_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be BadRequestException, but was " + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_Forbidden_WHEN_clan_THEN_throws_ForbiddenException() throws IOException, URISyntaxException {
        // Given
        mockError("/response/forbidden.json", 403);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw ForbiddenException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof ForbiddenException e) {
            assertEquals(FORBIDDEN_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be ForbiddenException, but was " + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_NotFound_WHEN_clan_THEN_throws_NotFoundException() throws IOException, URISyntaxException {
        // Given
        mockError("/response/notFound.json", 404);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw NotFoundException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof NotFoundException e) {
            assertEquals(NOT_FOUND_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be NotFoundException, but was " + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_MethodNotAllowed_WHEN_clan_THEN_throws_ClientException() throws IOException, URISyntaxException {
        // Given
        mockError("/response/methodNotAllowed.json", 405);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw ClientException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof ClientException e) {
            assertEquals(METHOD_NOT_ALLOWED, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be ClientException, but was "
                    + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_TooManyRequests_WHEN_clan_THEN_throws_TooManyRequestException() throws IOException, URISyntaxException {
        // Given
        mockError("/response/tooManyRequests.json", 429);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw TooManyRequestException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof TooManyRequestException e) {
            assertEquals(TOO_MANY_REQUESTS_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be TooManyRequestException, but was " + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_InternalServerError_WHEN_clan_THEN_throws_InternalServerErrorException()
            throws IOException, URISyntaxException {
        // Given
        mockError("/response/internalServerError.json", 500);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw InternalServerErrorException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof InternalServerErrorException e) {
            assertEquals(INTERNAL_SERVER_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be InternalServerErrorException, but was "
                    + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_ServiceUnavailable_WHEN_clan_THEN_throws_ServiceUnavailableException()
            throws IOException, URISyntaxException {
        // Given
        mockError("/response/serviceUnavailable.json", 503);

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw ServiceUnavailableException wrapped in RuntimeException");

        // Then
        verifyError();

        if (actual.getCause() instanceof ServiceUnavailableException e) {
            assertEquals(SERVICE_UNAVAILABLE_ERROR, e.getError(), "Error should equal expected");
        } else {
            fail("Cause should be ServiceUnavailableException, but was "
                    + actual.getCause().getClass().getSimpleName());
        }
    }

    @Test
    void GIVEN_empty_body_WHEN_clan_THEN_throws_DeserializationException() throws IOException {
        // Given
        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(body);
        when(body.string()).thenReturn("");

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw DeserializationException wrapped in RuntimeException");

        // Then
        verifyResponse();

        assertInstanceOf(DeserializationException.class, actual.getCause(), "Cause should be DeserializationException");
    }

    @Test
    void GIVEN_IOException_WHEN_clan_THEN_throws_RequestException() throws IOException {
        // Given
        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("Test exception"));

        // When
        final Exception actual = assertThrows(
                RuntimeException.class,
                () -> api.clan(CLAN_TAG).block(),
                "Should throw RequestException wrapped in RuntimeException");

        // Then
        verify(client, times(1)).newCall(any());
        verify(call, times(1)).execute();

        assertInstanceOf(RequestException.class, actual.getCause(), "Cause should be DeserializationException");
    }

    /* **************************************** Private utility methods ***************************************** */

    private Path getResourcePath(@NonNull final String path) throws URISyntaxException {
        final URL resource = getClass().getResource(path);

        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        } else {
            return Path.of(resource.toURI());
        }
    }

    private void mockError(@NonNull final String path, final int code) throws IOException, URISyntaxException {
        final String responseBody = Files.readString(getResourcePath(path));

        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(false);
        when(response.code()).thenReturn(code);
        when(response.message()).thenReturn("Error");
        when(response.body()).thenReturn(body);
        when(body.string()).thenReturn(responseBody);
    }

    private void mockResponse(@NonNull final String path) throws IOException, URISyntaxException {
        final String responseBody = Files.readString(getResourcePath(path));

        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(body);
        when(body.string()).thenReturn(responseBody);
    }

    private void verifyError() throws IOException {
        verify(client, times(1)).newCall(any());
        verify(call, times(1)).execute();
        verify(response, times(1)).isSuccessful();
        verify(response, times(1)).code();
        verify(response, times(1)).message();
        verify(response, times(1)).body();
        verify(body, times(1)).string();
        verify(response, times(1)).close();
    }

    private void verifyResponse() throws IOException {
        verify(client, times(1)).newCall(any());
        verify(call, times(1)).execute();
        verify(response, times(1)).isSuccessful();
        verify(response, times(1)).body();
        verify(body, times(1)).string();
        verify(response, times(1)).close();
    }
}
