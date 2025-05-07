package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * API HTTP exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public abstract sealed class ClashAPIException extends Exception
        permits ClientException, DeserializationException, RequestException, TokenException {
    /**
     * Constructor.
     *
     * @param message Error message.
     */
    protected ClashAPIException(@NonNull final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Cause.
     */
    protected ClashAPIException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
