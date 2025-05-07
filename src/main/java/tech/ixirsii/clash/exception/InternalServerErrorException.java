package tech.ixirsii.clash.exception;

import lombok.NonNull;
import tech.ixirsii.clash.data.ClientError;

/**
 * HTTP 500 Internal Server Error exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class InternalServerErrorException extends ClientException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param error   Client error response.
     */
    public InternalServerErrorException(@NonNull final String message, @NonNull final ClientError error) {
        super(message, error);
    }
}
