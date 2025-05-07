package tech.ixirsii.clash.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan label.
 *
 * @author Ryan Porterfield
 * @param iconURL Label icons.
 * @param id      Label ID.
 * @param name    Label name.
 * @since 1.0.0
 */
public record Label(@JsonProperty("iconUrls") IconURL iconURL, int id, String name) {
}
