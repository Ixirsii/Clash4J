package tech.ixirsii.clash.data.clan;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan war frequency.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public enum WarFrequency {
    /**
     * Always.
     */
    @JsonProperty("always")
    ALWAYS,
    /**
     * Any war frequency.
     */
    @JsonProperty("any")
    ANY,
    /**
     * More than once per week.
     */
    @JsonProperty("moreThanOncePerWeek")
    MORE_THAN_ONCE_PER_WEEK,
    /**
     * Once per week.
     */
    @JsonProperty("oncePerWeek")
    ONCE_PER_WEEK,
    /**
     * Less than once per week.
     */
    @JsonProperty("lessThanOncePerWeek")
    LESS_THAN_ONCE_PER_WEEK,
    /**
     * Never.
     */
    @JsonProperty("never")
    NEVER,
    /**
     * Unknown frequency.
     */
    @JsonProperty("unknown")
    UNKNOWN
}
