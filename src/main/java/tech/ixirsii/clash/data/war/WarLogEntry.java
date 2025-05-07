package tech.ixirsii.clash.data.war;

import java.time.ZonedDateTime;

/**
 * Clan war log entry.
 *
 * @author Ryan Porterfield
 * @param attacksPerMember Number of attacks per member.
 * @param clan             Clan.
 * @param endTime          End time.
 * @param opponent         Opponent.
 * @param result           Result.
 * @param teamSize         Team size.
 * @since 1.0.0
 */
public record WarLogEntry(
        int attacksPerMember,
        WarLogClan clan,
        ZonedDateTime endTime,
        WarLogClan opponent,
        Result result,
        int teamSize) {
}
