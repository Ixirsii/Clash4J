package tech.ixirsii.clash.data.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

/**
 * League season player clan.
 *
 * @author Ryan Porterfield
 * @param badgeURL Clan badge URLs.
 * @param name     Clan name.
 * @param tag      Clan tag.
 * @since 1.0.0
 */
public record PlayerRankingClan(@JsonProperty("badgeUrls") BadgeURL badgeURL, String name, String tag) {
}
