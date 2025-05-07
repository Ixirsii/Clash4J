package tech.ixirsii.clash.data.player;

/**
 * Legend league tournament season result.
 *
 * @author Ryan Porterfield
 * @param trophies Final seasonal trophies.
 * @param id       Season ID.
 * @param rank     Final seasonal rank.
 * @since 1.0.0
 */
public record LegendLeagueTournamentSeasonResult(int trophies, String id, int rank) {
}
