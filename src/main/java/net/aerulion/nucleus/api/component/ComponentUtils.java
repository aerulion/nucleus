package net.aerulion.nucleus.api.component;

import lombok.experimental.UtilityClass;
import net.aerulion.nucleus.api.string.CenterPixel;
import net.aerulion.nucleus.api.string.FontInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class for component functions
 */
@UtilityClass
public final class ComponentUtils {

  /**
   * Generates a centered component given a certain center pixel
   *
   * @param component   The component to be centered
   * @param centerPixel The center pixel
   * @return The centered string
   */
  public static @NotNull Component generateCenteredComponent(@NotNull Component component,
      int centerPixel) {
    int halvedMessageSize = (int) Math.round(getPixelLength(component) / 2D);
    int toCompensate = centerPixel - halvedMessageSize;
    if (toCompensate < 4) {
      return component;
    }
    if (component.decoration(TextDecoration.BOLD).equals(TextDecoration.State.NOT_SET)) {
      component = component.decoration(TextDecoration.BOLD, TextDecoration.State.FALSE);
    }
    return generateSpace(toCompensate).append(component);
  }

  /**
   * Gets the pixel length of a given component
   *
   * @param component Component to check
   * @return The pixel length of the String
   */
  public static int getPixelLength(@Nullable Component component) {
    return getPixelLength(component, false);
  }

  private static int getPixelLength(@Nullable Component component, boolean boldBefore) {
    if (component == null) {
      return 0;
    }
    int length = 0;
    boolean bold = false;
    if (component instanceof TextComponent textComponent) {
      bold = textComponent.decoration(TextDecoration.BOLD).equals(TextDecoration.State.TRUE)
          || (textComponent.decoration(TextDecoration.BOLD).equals(TextDecoration.State.NOT_SET)
          && boldBefore);
      for (char c : textComponent.content().toCharArray()) {
        @NotNull FontInfo fontInfo = FontInfo.getFontInfo(c);
        length += fontInfo.getLength(bold);
        length++;
      }
    }
    for (Component child : component.children()) {
      length += getPixelLength(child, bold);
    }
    return length;
  }

  /**
   * Generates a component of spaces of the given pixel width. May end BOLD!
   *
   * @param pixelWidth the desired width
   * @return the generated component
   */
  public static @NotNull Component generateSpace(int pixelWidth) {
    if (pixelWidth < 3) {
      return Component.empty();
    }
    if (pixelWidth < 5) {
      return Component.space();
    }
    if (pixelWidth == 5) {
      return Component.space().decorate(TextDecoration.BOLD);
    }
    int boldSpaces = Math.floorDiv(pixelWidth, 5);
    int spaces = 0;
    pixelWidth -= boldSpaces * 5;
    if (pixelWidth == 4) {
      spaces++;
    } else if (pixelWidth == 3) {
      if (boldSpaces >= 1) {
        boldSpaces--;
        spaces += 2;
      } else {
        spaces++;
      }
    } else if (pixelWidth == 2) {
      if (boldSpaces >= 2) {
        boldSpaces -= 2;
        spaces += 3;
      }
    } else if (pixelWidth == 1 && boldSpaces >= 3) {
      boldSpaces -= 3;
      spaces += 4;
    }
    @NotNull Component component = Component.empty();
    if (boldSpaces > 0) {
      component = Component.text(StringUtils.repeat(" ", boldSpaces))
          .decoration(TextDecoration.BOLD, TextDecoration.State.TRUE);
    }
    if (spaces > 0) {
      @NotNull Component spacesComponent = Component.text(StringUtils.repeat(" ", spaces))
          .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE);
      if (boldSpaces == 0) {
        component = spacesComponent;
      } else {
        component = component.append(spacesComponent);
      }
    }
    return component;
  }

  /**
   * Generates a centered component given a certain center pixel
   *
   * @param component   The component to be centered
   * @param centerPixel The center pixel
   * @return The centered string
   */
  public static @NotNull Component generateCenteredComponent(@NotNull Component component,
      @NotNull CenterPixel centerPixel) {
    return generateCenteredComponent(component, centerPixel.getCenterPx());
  }

  /**
   * Generate a progress bar component using a given char
   *
   * @param pixelWidth      the desired pixel width of the generated progress bar
   * @param character       the character that should be used to display the progress bar
   * @param progress        the percentage that should be filled
   * @param fillColor       the color for the filled part of the progress bar
   * @param backgroundColor the color for the non-filled part of the progress bar
   * @return the generated progress bar component
   */
  public static @NotNull Component generateProgressBar(int pixelWidth, char character,
      float progress, TextColor fillColor, TextColor backgroundColor) {
    int width = pixelWidth / FontInfo.getFontInfo(character).getLength(false);
    int filledAmount = Math.round(width * progress);
    return Component.text(StringUtils.repeat(Character.toString(character), filledAmount))
        .color(fillColor)
        .append(
            Component.text(StringUtils.repeat(Character.toString(character), width - filledAmount))
                .color(backgroundColor));
  }

  /**
   * Generates a line of a given pixel width
   *
   * @param pixelWidth the desired pixel width
   * @return the generated line
   */
  public static @NotNull Component generateLine(int pixelWidth) {
    return generateSpace(pixelWidth).decorate(TextDecoration.STRIKETHROUGH);
  }

  /**
   * Generates a fullwidth line given a certain center pixel
   *
   * @param centerPixel The center pixel
   * @return The fullwidth line
   */
  public static @NotNull Component generateFullWidthLine(@NotNull CenterPixel centerPixel) {
    return generateLine(centerPixel.getCenterPx() * 2);
  }
}