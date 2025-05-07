package tech.ixirsii.clash.data.clan;

import java.util.List;

/**
 * Clan capital.
 *
 * @author Ryan Porterfield
 * @param capitalHallLevel Capital hall level.
 * @param districts        Districts.
 * @since 1.0.0
 */
public record ClanCapital(int capitalHallLevel, List<ClanDistrict> districts) {
}
