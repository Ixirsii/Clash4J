package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * Exception type thrown when there was an error with the token manager.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public abstract sealed class TokenException extends ClashAPIException permits APIKeyException, LoginException {
    /**
     * Constructor.
     *
     * @param message Error message.
     */
    protected TokenException(@NonNull final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Error cause.
     */
    protected TokenException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
