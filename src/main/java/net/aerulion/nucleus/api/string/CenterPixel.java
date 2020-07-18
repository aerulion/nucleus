package net.aerulion.nucleus.api.string;

public enum CenterPixel {
    CHAT(154, 80),
    MOTD(134, 68),
    INVENTORY_TITLE(80, 80);

    private final int centerPixel;
    private final int spaceWidth;

    CenterPixel(int centerPixel, int spaceWidth) {
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