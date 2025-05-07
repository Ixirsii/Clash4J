package tech.ixirsii.clash;

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
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static tech.ixirsii.clash.TestData.CAPITAL_RAID_SEASON;
import static tech.ixirsii.clash.TestData.CLAN_TAG;

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
    void GIVEN_clan_tag_WHEN_capitalRaidSeason_THEN_returns_capital_raid_seasons()
            throws IOException, URISyntaxException {
        // Given
        final String responseBody =
                Files.readString(Path.of(getClass().getResource("/response/capitalRaidSeason.json").toURI()));

        when(client.newCall(any())).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(body);
        when(body.string()).thenReturn(responseBody);

        // When
        final CapitalRaidSeason actual = api.capitalRaidSeason(CLAN_TAG, null, null, null).block();

        // Then
        verify(client, times(1)).newCall(any());
        verify(call, times(1)).execute();
        verify(response, times(1)).isSuccessful();
        verify(response, times(1)).body();
        verify(body, times(1)).string();
        verify(response, times(1)).close();

        assertEquals(CAPITAL_RAID_SEASON, actual, "Capital raid season should equal expected");
    }
}
