package tech.ixirsii.clash.data.player;

import tech.ixirsii.clash.data.Village;

import java.util.List;

/**
 * Item (troop, spell, hero, etc.) level.
 *
 * @author Ryan Porterfield
 * @param equipment          List of hero equipment.
 * @param level              Item level.
 * @param maxLevel           Maximum item level.
 * @param name               Item name.
 * @param superTroopIsActive Is super troop active.
 * @param village            Village.
 * @since 1.0.0
 */
public record ItemLevel(
        List<HeroEquipment> equipment,
        int level,
        int maxLevel,
        String name,
        boolean superTroopIsActive,
        Village village) {
}
