package tech.ixirsii.clash.data.capital;

import java.util.List;

/**
 * Clan capital raid season defense log.
 *
 * @author Ryan Porterfield
 * @param attackCount        Attack count.
 * @param attacker           Attacking clan.
 * @param districtCount      District count.
 * @param districts          Districts.
 * @param districtsDestroyed Districts destroyed.
 * @since 1.0.0
 */
public record CapitalRaidSeasonDefenseLog(
        int attackCount,
        CapitalRaidSeasonClan attacker,
        int districtCount,
        List<CapitalRaidSeasonDistrict> districts,
        int districtsDestroyed) {
}
