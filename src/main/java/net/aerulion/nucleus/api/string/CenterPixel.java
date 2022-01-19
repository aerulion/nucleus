package net.aerulion.nucleus.api.string;

public enum CenterPixel {
  CHAT(160, 80),
  MOTD(134, 68),
  INVENTORY_TITLE(80, 40);

  private final int centerPixel;
  private final int spaceWidth;

  CenterPixel(final int centerPixel, final int spaceWidth) {
    this.centerPixel = centerPixel;
    this.spaceWidth = spaceWidth;
  }

  public int getCenterPx() {
    return centerPixel;
  }

  public int getSpaceWidth() {
    return spaceWidth;
  }
}