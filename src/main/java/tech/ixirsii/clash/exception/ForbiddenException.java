package tech.ixirsii.clash.exception;

import lombok.NonNull;
import tech.ixirsii.clash.data.ClientError;

/**
 * HTTP 403 Forbidden exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class ForbiddenException extends ClientException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param error   Client error response.
     */
    public ForbiddenException(@NonNull final String message, @NonNull final ClientError error) {
        super(message, error);
    }
}
