package tech.ixirsii.clash;

import lombok.NonNull;
import tech.ixirsii.clash.data.BadgeURL;
import tech.ixirsii.clash.data.BattleModifier;
import tech.ixirsii.clash.data.ClientError;
import tech.ixirsii.clash.data.IconURL;
import tech.ixirsii.clash.data.Label;
import tech.ixirsii.clash.data.Role;
import tech.ixirsii.clash.data.Village;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttack;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttackLog;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttacker;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonClan;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonDefenseLog;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonDistrict;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonMember;
import tech.ixirsii.clash.data.capital.PlayerHouse;
import tech.ixirsii.clash.data.capital.PlayerHouseElement;
import tech.ixirsii.clash.data.capital.PlayerHouseElementType;
import tech.ixirsii.clash.data.clan.Clan;
import tech.ixirsii.clash.data.clan.ClanCapital;
import tech.ixirsii.clash.data.clan.ClanDistrict;
import tech.ixirsii.clash.data.clan.ClanMember;
import tech.ixirsii.clash.data.clan.Language;
import tech.ixirsii.clash.data.clan.Location;
import tech.ixirsii.clash.data.clan.Type;
import tech.ixirsii.clash.data.clan.WarFrequency;
import tech.ixirsii.clash.data.league.BuilderBaseLeague;
import tech.ixirsii.clash.data.league.CapitalLeague;
import tech.ixirsii.clash.data.league.ClanWarLeagueClan;
import tech.ixirsii.clash.data.league.ClanWarLeagueGroup;
import tech.ixirsii.clash.data.league.ClanWarLeagueMember;
import tech.ixirsii.clash.data.league.ClanWarLeagueRound;
import tech.ixirsii.clash.data.league.ClanWarLeagueState;
import tech.ixirsii.clash.data.league.ClanWarLeagueWar;
import tech.ixirsii.clash.data.league.League;
import tech.ixirsii.clash.data.league.WarLeague;
import tech.ixirsii.clash.data.player.AchievementProgress;
import tech.ixirsii.clash.data.player.HeroEquipment;
import tech.ixirsii.clash.data.player.ItemLevel;
import tech.ixirsii.clash.data.player.LegendLeagueTournamentSeasonResult;
import tech.ixirsii.clash.data.player.LegendStatistics;
import tech.ixirsii.clash.data.player.Player;
import tech.ixirsii.clash.data.player.PlayerClan;
import tech.ixirsii.clash.data.player.TokenResponse;
import tech.ixirsii.clash.data.player.WarPreference;
import tech.ixirsii.clash.data.war.Result;
import tech.ixirsii.clash.data.war.State;
import tech.ixirsii.clash.data.war.War;
import tech.ixirsii.clash.data.war.WarAttack;
import tech.ixirsii.clash.data.war.WarClan;
import tech.ixirsii.clash.data.war.WarClanMember;
import tech.ixirsii.clash.data.war.WarLogClan;
import tech.ixirsii.clash.data.war.WarLogEntry;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public final class TestData {
    public static final String CLAN_NAME = "midwest warrior";
    public static final String CLAN_TAG = "#2Q82UJVY";
    public static final String PLAYER_NAME = "Pew™";
    public static final String PLAYER_TAG = "#GVY298UP";

    public static final ClientError BAD_REQUEST_ERROR = new ClientError("badRequest", "Bad request with tag string");
    public static final ClientError FORBIDDEN_ERROR = new ClientError("accessDenied", "Invalid authorization");
    public static final ClientError INTERNAL_SERVER_ERROR =
            new ClientError("internalServerError", "Unknown error happened when handling the request");
    public static final ClientError METHOD_NOT_ALLOWED =
            new ClientError("methodNotAllowed", "GET not allowed for endpoint");
    public static final ClientError NOT_FOUND_ERROR = new ClientError("notFound", "Not found with tag string");
    public static final ClientError SERVICE_UNAVAILABLE_ERROR =
            new ClientError("serviceUnavailable", "Service is temporarily unavailable because of maintenance");
    public static final ClientError TOO_MANY_REQUESTS_ERROR = new ClientError(
            "tooManyRequests",
            "Request was throttled, because amount of requests was above the threshold defined for the used API token");

    public static final BadgeURL BADGE_URL = new BadgeURL(
            getURL("https://api-assets.clashofclans.com/clans/2Q82UJVY/small.png"),
            getURL("https://api-assets.clashofclans.com/clans/2Q82UJVY/medium.png"),
            getURL("https://api-assets.clashofclans.com/clans/2Q82UJVY/large.png"));
    public static final CapitalRaidSeason CAPITAL_RAID_SEASON = new CapitalRaidSeason(
            List.of(new CapitalRaidSeasonAttackLog(
                    19,
                    new CapitalRaidSeasonClan(
                            BADGE_URL,
                            10,
                            "Defender",
                            CLAN_TAG),
                    9,
                    List.of(
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    1,
                                    "Barbarian Camp",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    2,
                                    "Wizard Valley",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    3,
                                    "Balloon Lagoon",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    4,
                                    "Builder's Workshop",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    5,
                                    "Dragon Cliffs",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    6,
                                    "Golem Quarry",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    7,
                                    "Skeleton Park",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    8,
                                    "Goblin Mines",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    3,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    1),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    75,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    0,
                                    "Capital Peak",
                                    3,
                                    12_000)
                    ),
                    9)),
            92_000,
            List.of(new CapitalRaidSeasonDefenseLog(
                    19,
                    new CapitalRaidSeasonClan(
                            BADGE_URL,
                            10,
                            "Attacker",
                            CLAN_TAG),
                    9,
                    List.of(
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    1,
                                    "Barbarian Camp",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    2,
                                    "Wizard Valley",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    3,
                                    "Balloon Lagoon",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    4,
                                    "Builder's Workshop",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    5,
                                    "Dragon Cliffs",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    6,
                                    "Golem Quarry",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    7,
                                    "Skeleton Park",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    2,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    8,
                                    "Goblin Mines",
                                    3,
                                    10_000),
                            new CapitalRaidSeasonDistrict(
                                    3,
                                    List.of(
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    50,
                                                    1),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    75,
                                                    2),
                                            new CapitalRaidSeasonAttack(
                                                    new CapitalRaidSeasonAttacker("Attacker", PLAYER_TAG),
                                                    100,
                                                    3)),
                                    100,
                                    10,
                                    0,
                                    "Capital Peak",
                                    3,
                                    12_000)
                    ),
                    9)),
            2_000,
            ZonedDateTime.of(2030, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC),
            9,
            List.of(new CapitalRaidSeasonMember(10, 19, 9, 92_000, "Member", PLAYER_TAG)),
            2_000,
            1,
            ZonedDateTime.of(2025, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC),
            "inProgress",
            19);
    public static final ClanMember CLAN_MEMBER = new ClanMember(
            new BuilderBaseLeague(44000030, "Titanium League II"),
            4165,
            5,
            1336,
            344,
            254,
            new League(
                    new IconURL(
                            getURL("https://api-assets.clashofclans.com/leagues/titani/medium.png"),
                            getURL("https://api-assets.clashofclans.com/leagues/titani/small.png"),
                            getURL("https://api-assets.clashofclans.com/leagues/titani/tiny.png")),
                    29000021,
                    "Titan League I"),
            PLAYER_NAME,
            new PlayerHouse(List.of(
                    new PlayerHouseElement(82000004, PlayerHouseElementType.GROUND),
                    new PlayerHouseElement(82000079, PlayerHouseElementType.WALLS),
                    new PlayerHouseElement(82000078, PlayerHouseElementType.ROOF),
                    new PlayerHouseElement(82000080, PlayerHouseElementType.DECORATION)
            )),
            5,
            Role.CO_LEADER,
            PLAYER_TAG,
            17,
            4768);
    public static final Clan CLAN = new Clan(
            BADGE_URL,
            new CapitalLeague(85000020, "Titan League II"),
            new Language("English", 75000000, "EN"),
            34525,
            new ClanCapital(
                    10,
                    List.of(
                            new ClanDistrict(10, 70000000, "Capital Peak"),
                            new ClanDistrict(5, 70000001, "Barbarian Camp"),
                            new ClanDistrict(5, 70000002, "Wizard Valley"),
                            new ClanDistrict(5, 70000003, "Balloon Lagoon"),
                            new ClanDistrict(5, 70000004, "Builder's Workshop"),
                            new ClanDistrict(5, 70000005, "Dragon Cliffs"),
                            new ClanDistrict(5, 70000006, "Golem Quarry"),
                            new ClanDistrict(4, 70000007, "Skeleton Park"),
                            new ClanDistrict(4, 70000008, "Goblin Mines"))),
            4638,
            22,
            35430,
            "TH17 graveyard",
            false,
            true,
            List.of(
                    new Label(
                            new IconURL(
                                    getURL("https://api-assets.clashofclans.com/labels/clanwars/medium.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clanwars/small.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clanwars/tiny.png")),
                            56000000,
                            "Clan Wars"),
                    new Label(
                            new IconURL(
                                    getURL("https://api-assets.clashofclans.com/labels/clangames/medium.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clangames/small.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clangames/tiny.png")),
                            56000004,
                            "Clan Games"),
                    new Label(
                            new IconURL(
                                    getURL("https://api-assets.clashofclans.com/labels/clancapital/medium.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clancapital/small.png"),
                                    getURL("https://api-assets.clashofclans.com/labels/clancapital/tiny.png")),
                            56000016,
                            "Clan Capital")
            ),
            new Location("US", 32000249, true, null, "United States"),
            45,
            List.of(CLAN_MEMBER),
            CLAN_NAME,
            0,
            10,
            1000,
            CLAN_TAG,
            Type.INVITE_ONLY,
            WarFrequency.MORE_THAN_ONCE_PER_WEEK,
            new WarLeague(48000011, "Crystal League II"),
            90,
            2,
            379,
            0);
    public static final ClanWarLeagueGroup LEAGUE_GROUP = new ClanWarLeagueGroup(
            List.of(new ClanWarLeagueClan(
                    BADGE_URL,
                    22,
                    List.of(new ClanWarLeagueMember(PLAYER_NAME, PLAYER_TAG, 17)),
                    CLAN_NAME,
                    CLAN_TAG)),
            List.of(
                    new ClanWarLeagueRound(List.of("#war11", "#war12", "#war13", "#war14")),
                    new ClanWarLeagueRound(List.of("#war21", "#war22", "#war23", "#war24")),
                    new ClanWarLeagueRound(List.of("#war31", "#war32", "#war33", "#war34")),
                    new ClanWarLeagueRound(List.of("#war41", "#war42", "#war43", "#war44")),
                    new ClanWarLeagueRound(List.of("#war51", "#war52", "#war53", "#war54")),
                    new ClanWarLeagueRound(List.of("#war61", "#war62", "#war63", "#war64")),
                    new ClanWarLeagueRound(List.of("#war71", "#war72", "#war73", "#war74"))),
            "2025-01",
            ClanWarLeagueState.ENDED,
            "#LEAGUE");
    public static final WarAttack WAR_ATTACK = new WarAttack(PLAYER_TAG, PLAYER_TAG, 100, 65, 1, 3);
    public static final WarClan WAR_CLAN = new WarClan(
            30,
            BADGE_URL,
            22,
            100,
            List.of(
                    new WarClanMember(
                            List.of(WAR_ATTACK, WAR_ATTACK),
                            WAR_ATTACK,
                            1,
                            PLAYER_NAME,
                            2,
                            PLAYER_TAG,
                            17)),
            CLAN_NAME,
            90,
            CLAN_TAG);
    public static final ClanWarLeagueWar LEAGUE_WAR = new ClanWarLeagueWar(
            WAR_CLAN,
            ZonedDateTime.of(2025, 1, 3, 12, 0, 0, 0, ZoneOffset.UTC),
            WAR_CLAN,
            ZonedDateTime.of(2025, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC),
            ZonedDateTime.of(2025, 1, 1, 11, 0, 0, 0, ZoneOffset.UTC),
            ClanWarLeagueState.WAR,
            30,
            ZonedDateTime.of(2025, 1, 2, 12, 0, 0, 0, ZoneOffset.UTC));
    public static final War WAR = new War(
            2,
            WAR_CLAN,
            ZonedDateTime.of(2025, 1, 3, 12, 0, 0, 0, ZoneOffset.UTC),
            WAR_CLAN,
            ZonedDateTime.of(2025, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC),
            ZonedDateTime.of(2025, 1, 1, 11, 0, 0, 0, ZoneOffset.UTC),
            State.PREPARATION,
            45,
            ZonedDateTime.of(2025, 1, 2, 12, 0, 0, 0, ZoneOffset.UTC));
    public static final WarLogClan WAR_LOG_CLAN = new WarLogClan(
            90,
            BADGE_URL,
            22,
            100,
            450,
            CLAN_NAME,
            135,
            CLAN_TAG);
    public static final WarLogEntry WAR_LOG = new WarLogEntry(
            2,
            BattleModifier.NONE,
            WAR_LOG_CLAN,
            ZonedDateTime.of(2025, 1, 3, 12, 0, 0, 0, ZoneOffset.UTC),
            WAR_LOG_CLAN,
            Result.TIE,
            45);

    public static final HeroEquipment GIANT_GAUNTLET = new HeroEquipment(
            27,
            27,
            "Giant Gauntlet",
            Village.HOME);
    public static final Player PLAYER = new Player(
            List.of(new AchievementProgress(
                    "Highest Gold Storage level: 17",
                    "Upgrade a Gold Storage to level 10",
                    "Bigger Coffers",
                    3,
                    10,
                    17,
                    Village.HOME)),
            0,
            5093,
            5839,
            new BuilderBaseLeague(44000034, "Platinum League I"),
            4840,
            10,
            new PlayerClan(BADGE_URL, 22, CLAN_NAME, CLAN_TAG),
            2997889,
            0,
            0,
            0,
            241,
            List.of(new ItemLevel(
                    List.of(GIANT_GAUNTLET),
                    95,
                    100,
                    "Barbarian King",
                    false,
                    Village.HOME)),
            List.of(GIANT_GAUNTLET),
            List.of(
                    new Label(new IconURL(
                            getURL("https://api-assets.clashofclans.com/labels/clanwars/medium.png"),
                            getURL("https://api-assets.clashofclans.com/labels/clanwars/small.png"),
                            getURL("https://api-assets.clashofclans.com/labels/clanwars/tiny.png")),
                            57000000,
                            "Clan Wars"),
                    new Label(new IconURL(
                            getURL("https://api-assets.clashofclans.com/labels/clanwarleague/medium.png"),
                            getURL("https://api-assets.clashofclans.com/labels/clanwarleague/small.png"),
                            getURL("https://api-assets.clashofclans.com/labels/clanwarleague/tiny.png")),
                            57000001,
                            "Clan War League"),
                    new Label(new IconURL(
                            getURL("https://api-assets.clashofclans.com/labels/competitive/medium.png"),
                            getURL("https://api-assets.clashofclans.com/labels/competitive/small.png"),
                            getURL("https://api-assets.clashofclans.com/labels/competitive/tiny.png")),
                            57000014,
                            "Competitive")),
            null,
            new LegendStatistics(
                    null,
                    new LegendLeagueTournamentSeasonResult("2023-06", 281168, 5263),
                    new LegendLeagueTournamentSeasonResult(null, null, 2923),
                    690,
                    null,
                    null),
            "Ixirsii",
            new PlayerHouse(List.of(
                    new PlayerHouseElement(82000001, PlayerHouseElementType.GROUND),
                    new PlayerHouseElement(82000049, PlayerHouseElementType.WALLS),
                    new PlayerHouseElement(82000016, PlayerHouseElementType.ROOF),
                    new PlayerHouseElement(82000060, PlayerHouseElementType.DECORATION))),
            Role.MEMBER,
            List.of(new ItemLevel(null, 11, 12, "Lightning Spell", false, Village.HOME)),
            "#2Q09RPGL8",
            16,
            1,
            List.of(new ItemLevel(null, 12, 12, "Barbarian", false, Village.HOME)),
            2923,
            WarPreference.IN,
            1978);

    /**
     * Hide utility class constructor.
     */
    private TestData() {
    }

    private static URL getURL(@NonNull final String path) {
        try {
            return URI.create(path).toURL();
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
