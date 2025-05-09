package tech.ixirsii.clash.data.war;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * War clan member.
 *
 * @author Ryan Porterfield
 * @param attacks            War attacks.
 * @param bestOpponentAttack Best attack against the member.
 * @param mapPosition        Map position.
 * @param name               Player name.
 * @param opponentAttacks    Opponent attacks.
 * @param tag                Player tag.
 * @param townHallLevel      Town hall level.
 * @since 1.0.0
 */
public record WarClanMember(
        List<WarAttack> attacks,
        WarAttack bestOpponentAttack,
        int mapPosition,
        String name,
        int opponentAttacks,
        String tag,
        @JsonProperty("townhallLevel") int townHallLevel) {
}
