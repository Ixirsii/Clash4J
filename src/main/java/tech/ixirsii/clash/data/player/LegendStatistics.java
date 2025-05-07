package tech.ixirsii.clash.data.player;

/**
 * Player's legend league statistics.
 *
 * @author Ryan Porterfield
 * @param bestBuilderBaseSeason     Best builder base season.
 * @param bestSeason                Best legend league season.
 * @param currentSeason             Current legend league season.
 * @param legendTrophies            Current legend league trophies.
 * @param previousBuilderBaseSeason Previous builder base season.
 * @param previousSeason            Previous legend league season.
 * @since 1.0.0
 */
public record LegendStatistics(
        LegendLeagueTournamentSeasonResult bestBuilderBaseSeason,
        LegendLeagueTournamentSeasonResult bestSeason,
        LegendLeagueTournamentSeasonResult currentSeason,
        int legendTrophies,
        LegendLeagueTournamentSeasonResult previousBuilderBaseSeason,
        LegendLeagueTournamentSeasonResult previousSeason) {
}
