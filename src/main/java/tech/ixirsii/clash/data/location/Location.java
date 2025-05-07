package tech.ixirsii.clash.data.location;

/**
 * Location.
 *
 * @author Ryan Porterfield
 * @param countryCode   ISO country code.
 * @param id            Location ID.
 * @param isCountry     Is the location a country?
 * @param localizedName Localized name.
 * @param name          Name.
 * @since 1.0.0
 */
public record Location(
        String countryCode,
        int id,
        boolean isCountry,
        String localizedName,
        String name) {
}
