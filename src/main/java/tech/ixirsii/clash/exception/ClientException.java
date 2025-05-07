package tech.ixirsii.clash.exception;

import lombok.Getter;
import lombok.NonNull;
import tech.ixirsii.clash.data.ClientError;

/**
 * Clash of Clans API exception (error response).
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
@Getter
public sealed class ClientException extends ClashAPIException
        permits BadRequestException, ForbiddenException, InternalServerErrorException, NotFoundException,
        ServiceUnavailableException, TooManyRequestException {
    /**
     * Client error response.
     */
    private final ClientError error;

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param error   Client error response.
     */
    public ClientException(@NonNull final String message, @NonNull final ClientError error) {
        super(message);

        this.error = error;
    }
}
