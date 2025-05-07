package tech.ixirsii.clash.data.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.IconURL;

/**
 * Player league.
 *
 * @author Ryan Porterfield
 * @param iconURL Player icon URLs.
 * @param id      Player ID.
 * @param name    Player name.
 * @since 1.0.0
 */
public record League(@JsonProperty("iconUrls") IconURL iconURL, int id, String name) {
}
