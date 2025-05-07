package tech.ixirsii.clash.data.clan;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan invite type.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum Type {
    /**
     * Closed.
     */
    @JsonProperty("closed")
    CLOSED,
    /**
     * Invite only.
     */
    @JsonProperty("inviteOnly")
    INVITE_ONLY,
    /**
     * Open.
     */
    @JsonProperty("open")
    OPEN
}
