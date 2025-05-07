package tech.ixirsii.clash.data.league;

/**
 * Player league ranking.
 *
 * @author Ryan Porterfield
 * @param attackWins   Seasonal attack wins.
 * @param clan         Player's clan.
 * @param defenseWins  Seasonal defense wins.
 * @param expLevel     Experience level.
 * @param league       League.
 * @param name         Player name.
 * @param previousRank Previous league rank.
 * @param rank         League rank.
 * @param tag          Player tag.
 * @param trophies     Trophy count.
 * @since 1.0.0
 */
public record PlayerRanking(
        int attackWins,
        PlayerRankingClan clan,
        int defenseWins,
        int expLevel,
        League league,
        String name,
        int previousRank,
        int rank,
        String tag,
        int trophies) {
}
