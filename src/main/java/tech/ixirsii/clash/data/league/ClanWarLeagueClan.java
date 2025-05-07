package tech.ixirsii.clash.data.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

import java.util.List;

/**
 * Clan war league clan.
 *
 * @author Ryan Porterfield
 * @param badgeURL  Clan badge URLs.
 * @param clanLevel Clan level.
 * @param members   Clan members.
 * @param name      Clan name.
 * @param tag       Clan tag.
 * @since 1.0.0
 */
public record ClanWarLeagueClan(
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        int clanLevel,
        List<ClanWarLeagueMember> members,
        String name,
        String tag) {
}
