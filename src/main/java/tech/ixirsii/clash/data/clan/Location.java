package tech.ixirsii.clash.data.clan;

/**
 * Clan location.
 *
 * @author Ryan Porterfield
 * @param countryCode   ISO country code.
 * @param id            Location ID.
 * @param isCountry     Is the location a country?
 * @param localizedName Localized location name.
 * @param name          Location name.
 * @since 1.0.0
 */
public record Location(String countryCode, int id, boolean isCountry, String localizedName, String name) {
}
