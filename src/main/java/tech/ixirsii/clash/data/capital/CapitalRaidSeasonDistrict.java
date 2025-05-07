package tech.ixirsii.clash.data.capital;

import java.util.List;

/**
 * Clan capital raid season district.
 *
 * @author Ryan Porterfield
 * @param attackCount        Attack count.
 * @param attacks            List of attacks.
 * @param destructionPercent Destruction percentage.
 * @param districtHallLevel  District hall level.
 * @param id                 District ID.
 * @param name               District name.
 * @param stars              District stars.
 * @param totalLooted        Total resources looted.
 * @since 1.0.0
 */
public record CapitalRaidSeasonDistrict(
        int attackCount,
        List<CapitalRaidSeasonAttack> attacks,
        int destructionPercent,
        int districtHallLevel,
        int id,
        String name,
        int stars,
        int totalLooted) {
}
