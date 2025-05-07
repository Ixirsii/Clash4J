package tech.ixirsii.clash.exception;

import lombok.NonNull;
import tech.ixirsii.clash.data.ClientError;

/**
 * HTTP 503 Service Unavailable exception.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public final class ServiceUnavailableException extends ClientException {
    /**
     * Constructor.
     *
     * @param message Error message.
     * @param error   Client error response.
     */
    public ServiceUnavailableException(@NonNull final String message, @NonNull final ClientError error) {
        super(message, error);
    }
}
