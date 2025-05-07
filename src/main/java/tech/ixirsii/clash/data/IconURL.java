package tech.ixirsii.clash.data;

import java.net.URL;

/**
 * Icon URLs for a clan, player, or league.
 *
 * @author Ryan Porterfield
 * @param tiny   Tiny icon URL.
 * @param small  Small icon URL.
 * @param medium Medium icon URL.
 * @since 1.0.0
 */
public record IconURL(URL tiny, URL small, URL medium) {
}
