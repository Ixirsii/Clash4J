package tech.ixirsii.clash.data.capital;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

/**
 * Clan capital raid season clan.
 *
 * @author Ryan Porterfield
 * @param badgeURL Clan badge URLs.
 * @param level    Clan level.
 * @param name     Clan name.
 * @param tag      Clan tag.
 * @since 1.0.0
 */
public record CapitalRaidSeasonClan(@JsonProperty("badgeUrls") BadgeURL badgeURL, int level, String name, String tag) {
}
