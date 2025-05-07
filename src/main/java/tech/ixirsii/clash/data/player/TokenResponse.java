package tech.ixirsii.clash.data.player;

/**
 * Verify player token response.
 *
 * @author Ryan Porterfield
 * @param tag    User tag.
 * @param token  User token.
 * @param status Status of verification.
 * @since 1.0.0
 */
public record TokenResponse(String tag, String token, String status) {
}
