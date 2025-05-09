package tech.ixirsii.clash.data.clan;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Clan war frequency.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum WarFrequency {
    /**
     * Always.
     */
    ALWAYS("always"),
    /**
     * Any war frequency.
     */
    ANY("any"),
    /**
     * More than once per week.
     */
    MORE_THAN_ONCE_PER_WEEK("moreThanOncePerWeek"),
    /**
     * Once per week.
     */
    ONCE_PER_WEEK("oncePerWeek"),
    /**
     * Less than once per week.
     */
    LESS_THAN_ONCE_PER_WEEK("lessThanOncePerWeek"),
    /**
     * Never.
     */
    NEVER("never"),
    /**
     * Unknown frequency.
     */
    UNKNOWN("unknown");

    /**
     * API value.
     */
    @JsonValue
    private final String value;
}
