package tech.ixirsii.clash.data.player;

import tech.ixirsii.clash.data.Village;

/**
 * Achievement progress.
 *
 * @author Ryan Porterfield
 * @param completionInfo Completion info.
 * @param info           Achievement info.
 * @param name           Name.
 * @param stars          Stars completed.
 * @param target         Target value.
 * @param value          Current value.
 * @param village        Village.
 * @since 1.0.0
 */
public record AchievementProgress(
        String completionInfo,
        String info,
        String name,
        int stars,
        int target,
        int value,
        Village village) {
}
