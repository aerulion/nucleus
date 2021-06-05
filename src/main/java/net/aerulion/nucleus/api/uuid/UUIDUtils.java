package net.aerulion.nucleus.api.uuid;

import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * A utility class for UUID related methods
 */
@UtilityClass
public final class UUIDUtils {
    /**
     * Converts a UUID to a byte array.
     *
     * @param uuid UUID to convert
     * @return the byte array
     */
    public static byte[] toByteArray(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    /**
     * Converts a byte array to a UUID
     *
     * @param bytes array to convert
     * @return the UUID
     */
    public static UUID fromByteArray(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}