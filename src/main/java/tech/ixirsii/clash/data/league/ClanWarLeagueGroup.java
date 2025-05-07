package tech.ixirsii.clash.data.league;

import java.util.List;

/**
 * Clan war league group.
 *
 * @author Ryan Porterfield
 * @param clans  Clans in the group.
 * @param rounds War rounds.
 * @param season CWL season.
 * @param state  War state.
 * @param tag    Group tag.
 * @since 1.0.0
 */
public record ClanWarLeagueGroup(
        List<ClanWarLeagueClan> clans,
        List<ClanWarLeagueRound> rounds,
        String season,
        ClanWarLeagueState state,
        String tag) {
}
