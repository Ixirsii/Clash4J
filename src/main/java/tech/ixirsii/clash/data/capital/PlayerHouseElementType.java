package tech.ixirsii.clash.data.capital;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Player house (clan capital) element type.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum PlayerHouseElementType {
    /**
     * Decoration element.
     */
    @JsonProperty("decoration")
    DECORATION,
    /**
     * Ground element.
     */
    @JsonProperty("ground")
    GROUND,
    /**
     * Roof element.
     */
    @JsonProperty("roof")
    ROOF,
    /**
     * Foot element.
     */
    @JsonProperty("walls")
    WALLS
}
