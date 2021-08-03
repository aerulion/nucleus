package net.aerulion.nucleus.api.color;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public final class Color {

  public static final @NotNull TextColor TEXT = TextColor.color(0xa9a9a9);
  public static final @NotNull TextColor ERROR = TextColor.color(0xe84545);
  public static final @NotNull TextColor INFO = TextColor.color(0x7efc20);
  public static final @NotNull TextColor NUCLEUS_PRIMARY = TextColor.color(0xff7a00);
  public static final @NotNull TextColor SUCCESS = TextColor.color(0x7efc20);
  public static final @NotNull TextColor WARNING = TextColor.color(0xffcc29);
}