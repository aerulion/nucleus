package net.aerulion.nucleus.api.component;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.FontInfo;
import net.aerulion.nucleus.api.string.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * A utility class for component functions
 */
@UtilityClass
public final class ComponentUtils {

    /**
     * Generates an centered component given a certain center pixel
     *
     * @param component   The component to be centered
     * @param centerPixel The center pixel
     * @return The centered string
     */
    public static Component generateCenteredComponent(Component component, int centerPixel) {
        int halvedMessageSize = (int) Math.round(StringUtils.getPixelLength(LegacyComponentSerializer.legacySection().serialize(component)) / 2D);
        int toCompensate = centerPixel - halvedMessageSize;
        if (toCompensate < 5)
            return component;
        return Component.text(StringUtils.getSpaces(toCompensate)).append(component);
    }

    /**
     * Generates an centered component given a certain center pixel
     *
     * @param component   The component to be centered
     * @param centerPixel The center pixel
     * @return The centered string
     */
    public static Component generateCenteredComponent(Component component, CenterPixel centerPixel) {
        return generateCenteredComponent(component, centerPixel.getCenterPx());
    }

    /**
     * Generate a progress bar component using the pipe '|' char
     *
     * @param pixelWidth      the desired pixel width of the generated progress bar
     * @param progress        the percentage that should be filled
     * @param fillColor       the color for the filled part of the progress bar
     * @param backgroundColor the color for the non-filled part of the progress bar
     * @return the generated progress bar component
     */
    public static Component generateProgressBar(int pixelWidth, float progress, TextColor fillColor, TextColor backgroundColor) {
        int width = pixelWidth / FontInfo.getFontInfo('|').getLength(false);
        int filledAmount = Math.round(width * progress);
        return Component.text(org.apache.commons.lang.StringUtils.repeat("|", filledAmount))
                .color(fillColor)
                .append(Component.text(org.apache.commons.lang.StringUtils.repeat("|", width - filledAmount))
                        .color(backgroundColor));
    }
}