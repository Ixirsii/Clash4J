package tech.ixirsii.clash.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Normal war or hard mode.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum BattleModifier {
    /**
     * No modifier.
     */
    @JsonProperty("none")
    NONE,
    /**
     * Hard mode.
     */
    @JsonProperty("hardMode")
    HARD_MODE
}
