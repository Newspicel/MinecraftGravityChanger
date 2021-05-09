package net.spaceblock.mc.gravity.gravitychanger.mode;

public enum GravityType {
    DEFAULT_GRAVITATION(0,0,0),
    NO_GRAVITATION(0,0,0),
    LOWER_GRAVITATION(0,95,0),
    MORE_GRAVITATION(0,10,0),
    ANTI_GRAVITATION(0,125,0),
    EXTREME_Y_GRAVITATION(255,255,255);

    public static GravityType DEFAULT = GravityType.DEFAULT_GRAVITATION;

    //Level Area is from minLevel to maxLevel
    private final int minLevel;
    private final int maxLevel;
    private final int defaultLevel;


    GravityType(int minLevel, int maxLevel, int defaultLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.defaultLevel = defaultLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getDefaultLevel() {
        return defaultLevel;
    }
}
