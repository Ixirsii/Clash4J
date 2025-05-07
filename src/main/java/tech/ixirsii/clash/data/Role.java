package tech.ixirsii.clash.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Player's role in a clan.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum Role {
    /**
     * Player is not a member of the clan.
     */
    @JsonProperty("notMember")
    NOT_MEMBER,
    /**
     * Player is a member of the clan.
     */
    @JsonProperty("member")
    MEMBER,
    /**
     * Player is an admin (elder) of the clan.
     */
    @JsonProperty("admin")
    ADMIN,
    /**
     * Player is a co-leader of the clan.
     */
    @JsonProperty("coLeader")
    CO_LEADER,
    /**
     * Player is the leader of the clan.
     */
    @JsonProperty("leader")
    LEADER
}
