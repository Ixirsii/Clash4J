package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * Exception type thrown when there is an error managing API keys.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class APIKeyException extends TokenException {
    /**
     * Constructor.
     *
     * @param message Error message.
     */
    public APIKeyException(@NonNull final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Error cause.
     */
    public APIKeyException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
