package tech.ixirsii.clash.data.war;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

import java.util.List;

/**
 * War clan.
 *
 * @author Ryan Porterfield
 * @param attacks               Attack count.
 * @param badgeURL              Clan badge URLs.
 * @param clanLevel             Clan level.
 * @param destructionPercentage Destruction percentage.
 * @param members               Clan members.
 * @param name                  Clan name.
 * @param stars                 Stars earned.
 * @param tag                   Clan tag.
 * @since 1.0.0
 */
public record WarClan(
        int attacks,
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        int clanLevel,
        double destructionPercentage,
        List<WarClanMember> members,
        String name,
        int stars,
        String tag) {
}
