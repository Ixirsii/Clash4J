package tech.ixirsii.clash.data.league;

import java.util.List;

/**
 * Clan war league round.
 *
 * @author Ryan Porterfield
 * @param warTags War tags in the round.
 * @since 1.0.0
 */
public record ClanWarLeagueRound(List<String> warTags) {
}
