package tech.ixirsii.clash.data.location;

import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.PlayerRankingClan;

/**
 * Player builder base ranking.
 *
 * @author Ryan Porterfield
 * @param builderBaseLeague   Builder base league.
 * @param builderBaseTrophies Trophy count.
 * @param clan                Clan.
 * @param expLevel            Experience level.
 * @param name                Player name.
 * @param previousRank        Previous builder base league rank.
 * @param rank                Current builder base league rank.
 * @param tag                 Player tag.
 * @since 1.0.0
 */
public record PlayerBuilderBaseRanking(
        BuilderBaseLeague builderBaseLeague,
        int builderBaseTrophies,
        PlayerRankingClan clan,
        int expLevel,
        String name,
        int previousRank,
        int rank,
        String tag) {
}
