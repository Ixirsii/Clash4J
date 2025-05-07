package tech.ixirsii.clash.data.league;

/**
 * Clan war league member.
 *
 * @author Ryan Porterfield
 * @param name          Player name.
 * @param tag           Player tag.
 * @param townHallLevel Player's town hall level.
 * @since 1.0.0
 */
public record ClanWarLeagueMember(String name, String tag, int townHallLevel) {
}
