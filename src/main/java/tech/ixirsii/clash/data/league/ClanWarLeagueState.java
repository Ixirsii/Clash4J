package tech.ixirsii.clash.data.league;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan war league state.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum ClanWarLeagueState {
    /**
     * Not found.
     */
    @JsonProperty("groupNotFound")
    GROUP_NOT_FOUND,
    /**
     * Not in war.
     */
    @JsonProperty("notInWar")
    NOT_IN_WAR,
    /**
     * Prep day.
     */
    @JsonProperty("preparation")
    PREPARATION,
    /**
     * War day.
     */
    @JsonProperty("inWar")
    WAR,
    /**
     * Ended.
     */
    @JsonProperty("warEnded")
    ENDED
}
