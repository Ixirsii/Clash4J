package tech.ixirsii.clash.data;

import java.net.URL;

/**
 * Clan badge URLs.
 *
 * @author Ryan Porterfield
 * @param small  Small icon URL.
 * @param medium Medium icon URL.
 * @param large  Large icon URL.
 * @since 1.0.0
 */
public record BadgeURL(URL small, URL medium, URL large) {
}
