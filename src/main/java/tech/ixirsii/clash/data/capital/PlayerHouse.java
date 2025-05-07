package tech.ixirsii.clash.data.capital;

import java.util.List;

/**
 * Player house (clan capital).
 *
 * @author Ryan Porterfield
 * @param elements House elements.
 * @since 1.0.0
 */
public record PlayerHouse(List<PlayerHouseElement> elements) {
}
