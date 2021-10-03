package net.aerulion.nucleus.api.font;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for font related methods
 */
@UtilityClass
public final class FontUtils {

  /**
   * Converts the given string to the compact font
   *
   * @param s the string to convert
   * @return the converted string
   */
  public static @NotNull String convertToCompactFont(@NotNull String s) {
    @NotNull StringBuilder stringBuilder = new StringBuilder();
    for (char c : s.toCharArray()) {
      stringBuilder.append(convertToCompactFont(c));
    }
    return stringBuilder.toString();
  }

  /**
   * Converts a given char to the compact equivalent
   *
   * @param c the char to convert
   * @return the compact equivalent char
   */
  public static char convertToCompactFont(char c) {
    return switch (c) {
      case 'a', 'A' -> 'ᴀ';
      case 'b', 'B' -> 'ʙ';
      case 'c', 'C' -> 'ᴄ';
      case 'd', 'D' -> 'ᴅ';
      case 'e', 'E' -> 'ᴇ';
      case 'f', 'F' -> 'ꜰ';
      case 'g', 'G' -> 'ɢ';
      case 'h', 'H' -> 'ʜ';
      case 'i', 'I' -> 'ɪ';
      case 'j', 'J' -> 'ᴊ';
      case 'k', 'K' -> 'ᴋ';
      case 'l', 'L' -> 'ʟ';
      case 'm', 'M' -> 'ᴍ';
      case 'n', 'N' -> 'ɴ';
      case 'o', 'O' -> 'ᴏ';
      case 'p', 'P' -> 'ᴘ';
      case 'q', 'Q' -> 'ꞯ';
      case 'r', 'R' -> 'ʀ';
      case 's', 'S' -> 's';
      case 't', 'T' -> 'ᴛ';
      case 'u', 'U' -> 'ᴜ';
      case 'v', 'V' -> 'ᴠ';
      case 'w', 'W' -> 'ᴡ';
      case 'x', 'X' -> 'x';
      case 'y', 'Y' -> 'ʏ';
      case 'z', 'Z' -> 'ᴢ';
      default -> c;
    };
  }

}