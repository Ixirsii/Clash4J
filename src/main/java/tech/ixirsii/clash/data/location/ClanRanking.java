package tech.ixirsii.clash.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;
import tech.ixirsii.clash.data.clan.Location;

/**
 * Clan ranking.
 *
 * @author Ryan Porterfield
 * @param badgeURL     Clan badge URLs.
 * @param clanLevel    Level.
 * @param clanPoints   Points.
 * @param location     Location.
 * @param members      Member count.
 * @param name         Name.
 * @param previousRank Previous rank.
 * @param rank         Current rank.
 * @param tag          Tag.
 * @since 1.0.0
 */
public record ClanRanking(
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        int clanLevel,
        int clanPoints,
        Location location,
        int members,
        String name,
        int previousRank,
        int rank,
        String tag) {
}
