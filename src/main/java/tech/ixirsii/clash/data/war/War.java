package tech.ixirsii.clash.data.war;

import java.time.ZonedDateTime;

/**
 * Clan war.
 *
 * @author Ryan Porterfield
 * @param attacksPerMember     Number of attacks per member.
 * @param clan                 Clan.
 * @param endTime              End time.
 * @param opponent             Opponent.
 * @param preparationStartTime Prep day start time.
 * @param startTime            War start time.
 * @param state                State.
 * @param teamSize             Team size.
 * @param warStartTime         War day start time.
 * @since 1.0.0
 */
public record War(
        int attacksPerMember,
        WarClan clan,
        ZonedDateTime endTime,
        WarClan opponent,
        ZonedDateTime preparationStartTime,
        ZonedDateTime startTime,
        State state,
        int teamSize,
        ZonedDateTime warStartTime) {
}
