package tech.ixirsii.clash.exception;

import lombok.NonNull;

/**
 * HTTP request exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class RequestException extends ClashAPIException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Cause.
     */
    public RequestException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }
}
