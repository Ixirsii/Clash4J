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
     * Ground element.
     */
    GROUND,
    /**
     * Roof element.
     */
    ROOF,
    /**
     * Foot element.
     */
    FOOT,
    /**
     * Decoration element.
     */
    @JsonProperty("DECO")
    DECORATION
}
