package tech.ixirsii.clash;

import lombok.NonNull;
import tech.ixirsii.clash.data.BadgeURL;
import tech.ixirsii.clash.data.capital.CapitalRaidSeason;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttack;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttackLog;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonAttacker;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonClan;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonDefenseLog;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonDistrict;
import tech.ixirsii.clash.data.capital.CapitalRaidSeasonMember;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public final class TestData {
    public static final String CLAN_TAG = "2Q82UJVY";
    public static final int LOCATION_ID = 32000249;
    public static final String PLAYER_TAG = "2Q09RPGL8";

    public static final CapitalRaidSeason CAPITAL_RAID_SEASON = new CapitalRaidSeason(
            List.of(new CapitalRaidSeasonAttackLog(
                    19,
                    new CapitalRaidSeasonClan(
                            new BadgeURL(
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/small.png"),
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/medium.png"),
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/large.png")),
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
                            new BadgeURL(
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/small.png"),
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/medium.png"),
                                    getURL("https://api.clashofclans.com/clans/2Q82UJVY/large.png")),
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
