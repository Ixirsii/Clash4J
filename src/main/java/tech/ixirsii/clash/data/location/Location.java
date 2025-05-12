package tech.ixirsii.clash.data.location;

/**
 * Clan location.
 *
 * @author Ryan Porterfield
 * @param countryCode   ISO country code.
 * @param id            Location ID.
 * @param isCountry     Is the location a country?
 * @param name          Location name.
 * @since 1.0.0
 */
public record Location(String countryCode, int id, boolean isCountry, String name) {
}
