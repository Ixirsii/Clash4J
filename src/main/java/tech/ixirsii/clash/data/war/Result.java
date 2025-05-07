package tech.ixirsii.clash.data.war;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan war result.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum Result {
    /**
     * Loss.
     */
    @JsonProperty("lose")
    LOSE,
    /**
     * Win.
     */
    @JsonProperty("win")
    WIN,
    /**
     * Tie/draw.
     */
    @JsonProperty("tie")
    TIE
}
