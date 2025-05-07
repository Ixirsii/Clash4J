package tech.ixirsii.clash.data;

import java.net.URL;

/**
 * Icon URLs for a clan, player, or league.
 *
 * @author Ryan Porterfield
 * @param medium Medium icon URL.
 * @param small  Small icon URL.
 * @param tiny   Tiny icon URL.
 * @since 1.0.0
 */
public record IconURL(URL medium, URL small, URL tiny) {
}
