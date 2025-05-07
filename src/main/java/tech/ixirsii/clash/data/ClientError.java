package tech.ixirsii.clash.data;

/**
 * API client error response.
 *
 * @author Ryan Porterfield
 * @param reason  Error reason.
 * @param message Error message.
 * @param type    Error type.
 * @since 1.0.0
 */
public record ClientError(String reason, String message, String type) {
}
