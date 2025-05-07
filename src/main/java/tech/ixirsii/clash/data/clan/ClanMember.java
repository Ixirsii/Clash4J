package tech.ixirsii.clash.data.clan;

import tech.ixirsii.clash.data.Role;
import tech.ixirsii.clash.data.capital.PlayerHouse;
import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.League;

/**
 * Clan member.
 *
 * @author Ryan Porterfield
 * @param builderBaseLeague   Builder base league.
 * @param builderBaseTrophies Builder base trophies.
 * @param clanRank            Clan rank.
 * @param donations           Monthly donations.
 * @param donationsReceived   Monthly donations received.
 * @param expLevel            Experience level.
 * @param league              Player league.
 * @param name                Player name.
 * @param playerHouse         Player house (clan capital).
 * @param previousClanRank    Previous clan rank.
 * @param role                Clan role.
 * @param tag                 Player tag.
 * @param townHallLevel       Town hall level.
 * @param trophies            Trophy count.
 * @param versusTrophies      Builder base trophy count.
 * @since 1.0.0
 */
public record ClanMember(
        BuilderBaseLeague builderBaseLeague,
        int builderBaseTrophies,
        int clanRank,
        int donations,
        int donationsReceived,
        int expLevel,
        League league,
        String name,
        PlayerHouse playerHouse,
        int previousClanRank,
        Role role,
        String tag,
        int townHallLevel,
        int trophies,
        int versusTrophies) {
}
