package tech.ixirsii.clash.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Village.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum Village {
    /**
     * Clan capital.
     */
    @JsonProperty("clanCapital")
    CLAN_CAPITAL,
    /**
     * Builder base.
     */
    @JsonProperty("builderBase")
    BUILDER_BASE,
    /**
     * Home.
     */
    @JsonProperty("home")
    HOME
}
