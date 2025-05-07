package tech.ixirsii.clash.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clan label.
 *
 * @author Ryan Porterfield
 * @param id      Label ID.
 * @param name    Label name.
 * @param iconURL Label icons.
 * @since 1.0.0
 */
public record Label(int id, String name, @JsonProperty("iconUrls") IconURL iconURL) {
}
