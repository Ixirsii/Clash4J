package tech.ixirsii.clash.data.war;

/**
 * Clan war attack.
 *
 * @author Ryan Porterfield
 * @param attackerTag           Attacker's player tag.
 * @param defenderTag           Defender's player tag.'
 * @param destructionPercentage Destruction percentage.
 * @param duration              Attack duration in seconds.
 * @param order                 Attack order.
 * @param stars                 Stars earned.
 * @since 1.0.0
 */
public record WarAttack(
        String attackerTag,
        String defenderTag,
        double destructionPercentage,
        int duration,
        int order,
        int stars) {
}
