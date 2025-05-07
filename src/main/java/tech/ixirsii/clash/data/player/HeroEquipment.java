package tech.ixirsii.clash.data.player;

import tech.ixirsii.clash.data.Village;

/**
 * Hero equipment.
 *
 * @author Ryan Porterfield
 * @param level    Equipment level.
 * @param maxLevel Maximum equipment level.
 * @param name     Equipment name.
 * @param village  Equipment village.
 * @since 1.0.0
 */
public record HeroEquipment(int level, int maxLevel, String name, Village village) {
}
