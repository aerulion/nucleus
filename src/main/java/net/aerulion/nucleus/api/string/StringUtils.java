package net.aerulion.nucleus.api.string;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for string functions
 */
@UtilityClass
public final class StringUtils {

  private static final @NotNull DecimalFormat decimalFormat = (DecimalFormat) (NumberFormat.getInstance(
      Locale.GERMAN));
  private static final @NotNull TreeMap<Integer, String> romanNumbers = new TreeMap<>();
  private static final @NotNull TreeMap<Integer, String> centerSpaces = new TreeMap<>();

  static {
    romanNumbers.put(1000, "M");
    romanNumbers.put(900, "CM");
    romanNumbers.put(500, "D");
    romanNumbers.put(400, "CD");
    romanNumbers.put(100, "C");
    romanNumbers.put(90, "XC");
    romanNumbers.put(50, "L");
    romanNumbers.put(40, "XL");
    romanNumbers.put(10, "X");
    romanNumbers.put(9, "IX");
    romanNumbers.put(5, "V");
    romanNumbers.put(4, "IV");
    romanNumbers.put(1, "I");

    centerSpaces.put(4, " ");
    centerSpaces.put(3, " ");
    centerSpaces.put(2, " ");
    centerSpaces.put(1, " ");
    centerSpaces.put(0, "");
  }

  @Deprecated
  public static String getSpaces(int width) {
    int l = centerSpaces.floorKey(width);
    if (width == l) {
      return centerSpaces.get(width);
    }
    return centerSpaces.get(l) + getSpaces(width - l);
  }

  @Deprecated
  public static int getPixelLength(@Nullable String string) {
    if (string == null) {
      return 0;
    }
    final @NotNull String validColorCodes = "0123456789aAbBcCdDeEfFkKlLmMnNoOrRxX";
    final @NotNull String formattingCodes = "kKmMnNoO";
    int stringPixelLength = 0;
    boolean colorCode = false;
    boolean isBold = false;
    string = string.replaceAll(
        "§[xX]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]", "§x");
    for (char c : string.toCharArray()) {
      if (c == '§') {
        colorCode = true;
      } else if (colorCode && (validColorCodes.indexOf(c) != -1)) {
        colorCode = false;
        if (c == 'l' || c == 'L') {
          isBold = true;
        } else if (formattingCodes.indexOf(c) == -1) {
          isBold = false;
        }
      } else {
        colorCode = false;
        @NotNull FontInfo fontInfo = FontInfo.getFontInfo(c);
        stringPixelLength += fontInfo.getLength(isBold);
        stringPixelLength++;
      }
    }
    return stringPixelLength;
  }

  @Deprecated
  public static String generateCenteredString(String string, int centerPixel) {
    int halvedMessageSize = (int) Math.round(getPixelLength(string) / 2D);
    int toCompensate = centerPixel - halvedMessageSize;
    if (toCompensate < 5) {
      return string;
    }
    return getSpaces(toCompensate) + string;
  }

  @Deprecated
  public static String generateCenteredString(String string, @NotNull CenterPixel centerPixel) {
    return generateCenteredString(string, centerPixel.getCenterPx());
  }

  @Deprecated
  public static @NotNull String generateFullWidthLine(@NotNull CenterPixel centerPixel) {
    return generateLine(centerPixel.getSpaceWidth());
  }

  @Deprecated
  public static @NotNull String generateLine(int length) {
    return "§m" + org.apache.commons.lang.StringUtils.repeat(" ", length);
  }

  /**
   * Wraps a string after a given amount of characters
   *
   * @param input The string to be wrapped
   * @param width The maximum width
   * @return The wrapped string as a list
   */
  public static @NotNull List<String> wrapString(String input, int width) {
    @NotNull List<String> wrappedString = new ArrayList<>();
    String wrapped = WordUtils.wrap(input, width, "\n", true);
    Collections.addAll(wrappedString, wrapped.split("\n"));
    return wrappedString;
  }

  /**
   * Converts an integer to a roman number string
   *
   * @param number The integer to be converted
   * @return The roman number string
   */
  public static String getRomanNumber(int number) {
    int l = romanNumbers.floorKey(number);
    if (number == l) {
      return romanNumbers.get(number);
    }
    return romanNumbers.get(l) + getRomanNumber(number - l);
  }

  /**
   * Formats a given number as a readable string
   *
   * @param number The number to be formatted
   * @return The formatted string
   */
  public static @NotNull String formatNumber(int number) {
    return decimalFormat.format(number);
  }

  /**
   * Formats a given number as a readable string
   *
   * @param number The number to be formatted
   * @return The formatted string
   */
  public static @NotNull String formatNumber(long number) {
    return decimalFormat.format(number);
  }

  /**
   * Formats a given number as a readable string
   *
   * @param number The number to be formatted
   * @return The formatted string
   */
  public static String formatNumber(float number) {
    return decimalFormat.format(number);
  }

  /**
   * Formats a given number as a readable string
   *
   * @param number The number to be formatted
   * @return The formatted string
   */
  public static String formatNumber(double number) {
    return decimalFormat.format(number);
  }
}