package tech.ixirsii.clash.data.capital;

import java.util.List;

/**
 * Clan capital raid season attack log.
 *
 * @author Ryan Porterfield
 * @param attackCount        Number of attacks.
 * @param defender           Defending clan.
 * @param districtCount      District count.
 * @param districts          Districts.
 * @param districtsDestroyed Districts destroyed.
 * @since 1.0.0
 */
public record CapitalRaidSeasonAttackLog(
        int attackCount,
        CapitalRaidSeasonClan defender,
        int districtCount,
        List<CapitalRaidSeasonDistrict> districts,
        int districtsDestroyed) {
}
