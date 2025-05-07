package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * Client deserialization exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class DeserializationException extends ClashAPIException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Cause.
     */
    public DeserializationException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
