package tech.ixirsii.clash.data.clan;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.ixirsii.clash.data.BadgeURL;
import tech.ixirsii.clash.data.Label;
import tech.ixirsii.clash.data.league.CapitalLeague;
import tech.ixirsii.clash.data.league.WarLeague;
import tech.ixirsii.clash.data.location.Location;

import java.util.List;

/**
 * Clan.
 *
 * @author Ryan Porterfield
 * @param badgeURL                    Clan badge URLs.
 * @param capitalLeague               Clan capital league.
 * @param chatLanguage                Chat language.
 * @param clanBuilderBasePoints       Total builder base points.
 * @param clanCapital                 Clan capital.
 * @param clanCapitalPoints           Clan capital points.
 * @param clanLevel                   Clan level.
 * @param clanPoints                  Total clan points.
 * @param description                 Description.
 * @param isFamilyFriendly            Is the clan family friendly?
 * @param isWarLogPublic              Is the war log public?
 * @param labels                      Labels.
 * @param location                    Location.
 * @param members                     Member count.
 * @param memberList                  Members.
 * @param name                        Clan name.
 * @param requiredBuilderBaseTrophies Required number of builder base trophies to join.
 * @param requiredTownHallLevel       Required town hall level to join.
 * @param requiredTrophies            Required number of trophies  to join.
 * @param tag                         Clan tag.
 * @param type                        Clan join type.
 * @param warFrequency                War frequency.
 * @param warLeague                   War league.
 * @param warLosses                   Total war losses.
 * @param warTies                     Total war ties.
 * @param warWins                     Total war wins.
 * @param warWinStreak                Current war win streak.
 * @since 1.0.0
 */
public record Clan(
        @JsonProperty("badgeUrls") BadgeURL badgeURL,
        CapitalLeague capitalLeague,
        Language chatLanguage,
        int clanBuilderBasePoints,
        ClanCapital clanCapital,
        int clanCapitalPoints,
        int clanLevel,
        int clanPoints,
        String description,
        boolean isFamilyFriendly,
        boolean isWarLogPublic,
        List<Label> labels,
        Location location,
        int members,
        List<ClanMember> memberList,
        String name,
        int requiredBuilderBaseTrophies,
        @JsonProperty("requiredTownhallLevel") int requiredTownHallLevel,
        int requiredTrophies,
        String tag,
        Type type,
        WarFrequency warFrequency,
        WarLeague warLeague,
        int warLosses,
        int warTies,
        int warWins,
        int warWinStreak) {
}
