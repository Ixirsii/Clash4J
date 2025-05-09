package tech.ixirsii.clash.data.player;

/**
 * Legend league tournament season result.
 *
 * @author Ryan Porterfield
 * @param id       Season ID.
 * @param rank     Final seasonal rank.
 * @param trophies Final seasonal trophies.
 * @since 1.0.0
 */
public record LegendLeagueTournamentSeasonResult(String id, Integer rank, int trophies) {
}
