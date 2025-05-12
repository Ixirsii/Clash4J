package tech.ixirsii.clash.data.league;

import tech.ixirsii.clash.data.war.WarClan;

import java.time.ZonedDateTime;

/**
 * Clan war league war.
 *
 * @author Ryan Porterfield
 * @param clan                 Primary clan.
 * @param endTime              End time.
 * @param opponent             Opponent clan.
 * @param preparationStartTime Preparation start time.
 * @param startTime            Start time.
 * @param state                War state.
 * @param teamSize             Team size.
 * @param warStartTime         War start time.
 * @since 1.0.0
 */
public record ClanWarLeagueWar(
        WarClan clan,
        ZonedDateTime endTime,
        WarClan opponent,
        ZonedDateTime preparationStartTime,
        ZonedDateTime startTime,
        ClanWarLeagueWarState state,
        int teamSize,
        ZonedDateTime warStartTime) {
}
