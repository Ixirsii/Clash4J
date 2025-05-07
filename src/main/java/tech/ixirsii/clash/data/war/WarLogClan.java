package tech.ixirsii.clash.data.war;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

/**
 * War log clan.
 *
 * @author Ryan Porterfield
 * @param attacks               War attacks.
 * @param badgeURL              Clan badge URLs.
 * @param clanLevel             Clan level.
 * @param destructionPercentage Destruction percentage.
 * @param expEarned             Experience earned.
 * @param name                  Clan name.
 * @param stars                 Stars earned.
 * @param tag                   Clan tag.
 * @since 1.0.0
 */
public record WarLogClan(
        int attacks,
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        int clanLevel,
        double destructionPercentage,
        int expEarned,
        String name,
        int stars,
        String tag) {
}
