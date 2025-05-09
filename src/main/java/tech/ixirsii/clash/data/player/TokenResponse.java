package tech.ixirsii.clash.data.player;

/**
 * Verify player token response.
 *
 * @author Ryan Porterfield
 * @param status Status of verification.
 * @param tag    User tag.
 * @param token  User token.
 * @since 1.0.0
 */
public record TokenResponse(String status, String tag, String token) {
}
