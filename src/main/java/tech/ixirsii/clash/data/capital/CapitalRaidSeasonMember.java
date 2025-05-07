package tech.ixirsii.clash.data.capital;

/**
 * Clan capital raid season member.
 *
 * @author Ryan Porterfield
 * @param attackLimit            Attack limit.
 * @param attacks                Number of attacks.
 * @param bonusAttackLimit       Bonus attack limit.
 * @param capitalResourcesLooted Clan capital resources looted.
 * @param name                   Player name.
 * @param tag                    Player tag.
 * @since 1.0.0
 */
public record CapitalRaidSeasonMember(
        int attackLimit,
        int attacks,
        int bonusAttackLimit,
        int capitalResourcesLooted,
        String name,
        String tag) {
}
