package tech.ixirsii.clash.data.war;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan war state.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum State {
    /**
     * Clan not found.
     */
    @JsonProperty("clanNotFound")
    CLAN_NOT_FOUND,
    /**
     * Clan war log is private.
     */
    @JsonProperty("accessDenied")
    ACCESS_DENIED,
    /**
     * Not in war.
     */
    @JsonProperty("notInWar")
    NOT_IN_WAR,
    /**
     * In matchmaking.
     */
    @JsonProperty("inMatchmaking")
    IN_MATCHMAKING,
    /**
     * Enter war.
     */
    @JsonProperty("enterWar")
    ENTER_WAR,
    /**
     * Matched.
     */
    @JsonProperty("matched")
    MATCHED,
    /**
     * Preparation day.
     */
    @JsonProperty("preparation")
    PREPARATION,
    /**
     * War day.
     */
    @JsonProperty("war")
    WAR,
    /**
     * In war.
     */
    @JsonProperty("inWar")
    IN_WAR,
    /**
     * Ended.
     */
    @JsonProperty("ended")
    ENDED
}
