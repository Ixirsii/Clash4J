package tech.ixirsii.clash.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;

/**
 * Clan builder base ranking.
 *
 * @author Ryan Porterfield
 * @param badgeURL              Badge URLs.
 * @param clanBuilderBasePoints Builder base points.
 * @param clanLevel             Clan level.
 * @param location              Location.
 * @param members               Member count.
 * @param name                  Clan name.
 * @param previousRank          Previous rank.
 * @param rank                  Current rank.
 * @param tag                   Clan tag.
 * @since 1.0.0
 */
public record ClanBuilderBaseRanking(
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        int clanBuilderBasePoints,
        int clanLevel,
        Location location,
        int members,
        String name,
        int previousRank,
        int rank,
        String tag) {
}
