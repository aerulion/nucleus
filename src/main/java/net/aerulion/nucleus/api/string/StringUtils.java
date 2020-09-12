package net.aerulion.nucleus.api.string;

import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class StringUtils {

    private final static TreeMap<Integer, String> romanNumbers = new TreeMap<>();
    private final static TreeMap<Integer, String> centerSpaces = new TreeMap<>();

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

        centerSpaces.put(5, "§l §r");
        centerSpaces.put(4, " ");
        centerSpaces.put(3, " ");
        centerSpaces.put(2, " ");
        centerSpaces.put(1, " ");
        centerSpaces.put(0, "");
    }

    private static String getSpaces(int width) {
        int l = centerSpaces.floorKey(width);
        if (width == l) {
            return centerSpaces.get(width);
        }
        return centerSpaces.get(l) + getSpaces(width - l);
    }

    public static int getPixelLength(String string) {
        final String validColorCodes = "0123456789aAbBcCdDeEfFkKlLmMnNoOrRxX";
        final String formattingCodes = "kKmMnNoO";
        int stringPixelLength = 0;
        boolean colorCode = false;
        boolean isBold = false;
        string = string.replaceAll("§[xX]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]§[A-Fa-f0-9]", "§x");
        for (char c : string.toCharArray()) {
            if (c == '§') {
                colorCode = true;
            } else if (colorCode && (validColorCodes.indexOf(c) != -1)) {
                colorCode = false;
                if (c == 'l' || c == 'L')
                    isBold = true;
                else if (formattingCodes.indexOf(c) == -1)
                    isBold = false;
            } else {
                colorCode = false;
                FontInfo fontInfo = FontInfo.getFontInfo(c);
                stringPixelLength += fontInfo.getLength(isBold);
                stringPixelLength++;
            }
        }
        return stringPixelLength;
    }

    public static String generateCenteredString(String string, int centerPixel) {
        int halvedMessageSize = (int) Math.round(getPixelLength(string) / 2D);
        int toCompensate = centerPixel - halvedMessageSize;
        return getSpaces(toCompensate) + string;
    }

    public static String generateCenteredString(String string, CenterPixel centerPixel) {
        return generateCenteredString(string, centerPixel.getCenterPx());
    }

    public static String generateFullWidthLine(CenterPixel centerPixel) {
        return generateLine(centerPixel.getSpaceWidth());
    }

    public static String generateLine(int length) {
        return "§m" + org.apache.commons.lang.StringUtils.repeat(" ", length);
    }

    public static List<String> wrapString(String input, int width) {
        List<String> wrappedString = new ArrayList<>();
        String wrapped = WordUtils.wrap(input, width, "\n", true);
        Collections.addAll(wrappedString, wrapped.split("\n"));
        return wrappedString;
    }

    public static String getRomanNumber(int number) {
        int l = romanNumbers.floorKey(number);
        if (number == l) {
            return romanNumbers.get(number);
        }
        return romanNumbers.get(l) + getRomanNumber(number - l);
    }
}