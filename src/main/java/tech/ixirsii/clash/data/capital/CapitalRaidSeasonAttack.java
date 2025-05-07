package tech.ixirsii.clash.data.capital;

/**
 * Clan capital raid season attack.
 *
 * @author Ryan Porterfield
 * @param attacker           Attacker.
 * @param destructionPercent Destruction percentage.
 * @param stars              Stars earned.
 * @since 1.0.0
 */
public record CapitalRaidSeasonAttack(CapitalRaidSeasonAttacker attacker, int destructionPercent, int stars) {
}
