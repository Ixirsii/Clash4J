package tech.ixirsii.clash.data.player;

import tech.ixirsii.clash.data.Label;
import tech.ixirsii.clash.data.Role;
import tech.ixirsii.clash.data.capital.PlayerHouse;
import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.League;

import java.util.List;

/**
 * Player.
 *
 * @author Ryan Porterfield
 * @param achievements             Achievements.
 * @param attackWins               Attack wins.
 * @param bestBuilderBaseTrophies  All-time best builder base trophies.
 * @param bestTrophies             All-time best trophies.
 * @param builderBaseLeague        Builder base league.
 * @param builderBaseTrophies      Current builder base trophies.
 * @param builderHallLevel         Builder hall level.
 * @param clan                     Clan.
 * @param clanCapitalContributions Clan capital contributions.
 * @param defenseWins              Defense wins.
 * @param donations                Monthly donations.
 * @param donationsReceived        Monthly donations received.
 * @param expLevel                 Experience level.
 * @param heroes                   Heroes.
 * @param heroEquipment            Hero equipment.
 * @param labels                   Labels.
 * @param league                   League.
 * @param legendStatistics         Legend statistics.
 * @param name                     Player name.
 * @param playerHouse              Player house.
 * @param role                     Clan role.
 * @param spells                   Spells.
 * @param tag                      Player tag.
 * @param townHallLevel            Town hall level.
 * @param townHallWeaponLevel      Town hall weapon level.
 * @param troops                   Troops.
 * @param trophies                 Current trophies.
 * @param warPreference            War preference.
 * @param warStars                 Total war stars.
 * @since 1.0.0
 */
public record Player(
        List<AchievementProgress> achievements,
        int attackWins,
        int bestBuilderBaseTrophies,
        int bestTrophies,
        BuilderBaseLeague builderBaseLeague,
        int builderBaseTrophies,
        int builderHallLevel,
        PlayerClan clan,
        int clanCapitalContributions,
        int defenseWins,
        int donations,
        int donationsReceived,
        int expLevel,
        List<ItemLevel> heroes,
        List<HeroEquipment> heroEquipment,
        List<Label> labels,
        League league,
        LegendStatistics legendStatistics,
        String name,
        PlayerHouse playerHouse,
        Role role,
        List<ItemLevel> spells,
        String tag,
        int townHallLevel,
        int townHallWeaponLevel,
        List<ItemLevel> troops,
        int trophies,
        WarPreference warPreference,
        int warStars) {
}
