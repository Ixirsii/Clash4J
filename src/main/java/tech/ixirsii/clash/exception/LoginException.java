package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * Exception type thrown when there is an error logging in to the developer portal.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class LoginException extends TokenException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Error cause.
     */
    public LoginException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
