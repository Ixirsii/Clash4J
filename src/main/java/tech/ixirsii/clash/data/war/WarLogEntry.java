package tech.ixirsii.clash.data.war;

import tech.ixirsii.clash.data.BattleModifier;

import java.time.ZonedDateTime;

/**
 * Clan war log entry.
 *
 * @author Ryan Porterfield
 * @param attacksPerMember Number of attacks per member.
 * @param battleModifier   Battle modifier.
 * @param clan             Clan.
 * @param endTime          End time.
 * @param opponent         Opponent.
 * @param result           Result.
 * @param teamSize         Team size.
 * @since 1.0.0
 */
public record WarLogEntry(
        int attacksPerMember,
        BattleModifier battleModifier,
        WarLogClan clan,
        ZonedDateTime endTime,
        WarLogClan opponent,
        Result result,
        int teamSize) {
}
