package net.aerulion.nucleus.api.uuid;

import java.nio.ByteBuffer;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for uuid related methods
 */
@UtilityClass
public final class UUIDUtils {

  /**
   * Converts an uuid to a byte array.
   *
   * @param uuid the uuid to convert
   * @return the byte array
   */
  public static @NotNull byte @NotNull [] toByteArray(@NotNull UUID uuid) {
    @NotNull ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
    byteBuffer.putLong(uuid.getMostSignificantBits());
    byteBuffer.putLong(uuid.getLeastSignificantBits());
    return byteBuffer.array();
  }

  /**
   * Converts a byte array to an uuid
   *
   * @param bytes the array to convert
   * @return the uuid
   */
  public static @NotNull UUID fromByteArray(@NotNull byte @NotNull [] bytes) {
    @NotNull ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    long high = byteBuffer.getLong();
    long low = byteBuffer.getLong();
    return new UUID(high, low);
  }

  /**
   * Converts an uuid to an int array.
   *
   * @param uuid the uuid to convert
   * @return the int array
   */
  public static @NotNull int @NotNull [] toIntArray(@NotNull UUID uuid) {
    long high = uuid.getMostSignificantBits();
    long low = uuid.getLeastSignificantBits();
    return new int[]{(int) (high >> 32L), (int) high, (int) (low >> 32L), (int) low};
  }

  /**
   * Converts an int array to an uuid.
   *
   * @param ints the array to convert
   * @return the uuid
   */
  public static @NotNull UUID fromIntArray(@NotNull int[] ints) {
    return new UUID((long) ints[0] << 32L | ints[1] & 0xFFFFFFFFL,
        (long) ints[2] << 32L | ints[3] & 0xFFFFFFFFL);
  }
}