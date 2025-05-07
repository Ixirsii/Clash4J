package tech.ixirsii.clash.data.goldpass;

import java.time.ZonedDateTime;

/**
 * Gold pass season.
 *
 * @author Ryan Porterfield
 * @param startTime Start date.
 * @param endTime   End date.
 * @since 1.0.0
 */
public record GoldPassSeason(ZonedDateTime startTime, ZonedDateTime endTime) {
}
