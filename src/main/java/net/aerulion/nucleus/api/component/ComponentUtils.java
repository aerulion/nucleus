package net.aerulion.nucleus.api.component;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.StringUtils;
import net.kyori.adventure.text.Component;
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
}