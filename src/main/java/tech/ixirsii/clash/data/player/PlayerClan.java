package tech.ixirsii.clash.data.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

/**
 * Player clan.
 *
 * @author Ryan Porterfield
 * @param badgeURL  Clan badge URLs.
 * @param clanLevel Clan level.
 * @param name      Clan name.
 * @param tag       Clan tag.
 * @since 1.0.0
 */
public record PlayerClan(@JsonProperty("badgeUrls") BadgeURL badgeURL, int clanLevel, String name, String tag) {
}
