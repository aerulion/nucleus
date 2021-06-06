package net.aerulion.nucleus.api.uuid;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * A utility class for uuid related methods
 */
@UtilityClass
public final class UUIDUtils {
    /**
     * Converts a uuid to a byte array.
     *
     * @param uuid the uuid to convert
     * @return the byte array
     */
    public static @NotNull byte[] toByteArray(@NotNull UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    /**
     * Converts a byte array to a uuid
     *
     * @param bytes the array to convert
     * @return the uuid
     */
    public static @NotNull UUID fromByteArray(@NotNull byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

    /**
     * Converts a uuid to a int array.
     *
     * @param uuid the uuid to convert
     * @return the int array
     */
    public static @NotNull int[] toIntArray(@NotNull UUID uuid) {
        long high = uuid.getMostSignificantBits();
        long low = uuid.getLeastSignificantBits();
        return new int[]{(int) (high >> 32L), (int) high, (int) (low >> 32L), (int) low};
    }

    /**
     * Converts a int array to a uuid.
     *
     * @param ints the array to convert
     * @return the uuid
     */
    public static @NotNull UUID fromIntArray(@NotNull int[] ints) {
        return new UUID((long) ints[0] << 32L | ints[1] & 0xFFFFFFFFL,
                (long) ints[2] << 32L | ints[3] & 0xFFFFFFFFL);
    }
}