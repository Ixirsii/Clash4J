package tech.ixirsii.clash.internal;

import lombok.NonNull;

import java.io.IOException;

/**
 * Deserializer functional interface.
 *
 * @author Ryan Porterfield
 * @param <T> Type to deserialize.
 * @since 1.0.0
 */
@FunctionalInterface
public interface Deserializer<T> {
    /**
     * Deserialize the response body.
     *
     * @param body Response body.
     * @return Deserialized object.
     * @throws IOException On failure to deserialize.
     */
    T deserialize(@NonNull String body) throws IOException;
}
