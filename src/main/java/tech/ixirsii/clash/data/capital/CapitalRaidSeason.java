package tech.ixirsii.clash.data.capital;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clan capital raid season.
 *
 * @author Ryan Porterfield
 * @param attackLog               Attack log.
 * @param capitalTotalLoot        Total capital loot.
 * @param defenseLog              Defense log.
 * @param defensiveReward         Defensive reward.
 * @param endTime                 End time.
 * @param enemyDistrictsDestroyed Number of enemy districts destroyed.
 * @param offensiveReward         Offensive reward.
 * @param members                 Members.
 * @param raidsCompleted          Raids completed.
 * @param startTime               Start time.
 * @param state                   State.
 * @param totalAttacks            Total attacks.
 * @since 1.0.0
 */
public record CapitalRaidSeason(
        List<CapitalRaidSeasonAttackLog> attackLog,
        int capitalTotalLoot,
        List<CapitalRaidSeasonDefenseLog> defenseLog,
        int defensiveReward,
        ZonedDateTime endTime,
        int enemyDistrictsDestroyed,
        List<CapitalRaidSeasonMember> members,
        int offensiveReward,
        int raidsCompleted,
        ZonedDateTime startTime,
        String state,
        int totalAttacks) {
}
