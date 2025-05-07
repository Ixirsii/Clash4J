package tech.ixirsii.clash.data.player;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * War preference.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum WarPreference {
    /**
     * Opt-out of wars.
     */
    @JsonProperty("out")
    OUT,
    /**
     * Opt-in to wars.
     */
    @JsonProperty("in")
    IN
}
