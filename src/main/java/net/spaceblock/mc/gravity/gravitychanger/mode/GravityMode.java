package net.spaceblock.mc.gravity.gravitychanger.mode;

import net.spaceblock.mc.gravity.gravitychanger.GravityTypeNotAllowedException;

public class GravityMode {

    private GravityType gravityType = GravityType.DEFAULT;
    private int currentLevel = 0;

    public GravityMode setGravity(GravityType gravityType) throws GravityTypeNotAllowedException {
        return setGravity(gravityType, gravityType.getDefaultLevel());
    }


    public GravityMode setGravity(GravityType gravityType, int currentLevel) throws GravityTypeNotAllowedException {
        if (currentLevel > gravityType.getMaxLevel() || currentLevel < gravityType.getMinLevel()) {
            throw new GravityTypeNotAllowedException(gravityType, currentLevel);
        }

        this.gravityType = gravityType;
        this.currentLevel = currentLevel;

        return this;
    }

    public GravityType getGravityType() {
        return gravityType;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public String toString() {
        return "GravityMode{" +
                "gravityType=" + gravityType +
                ", currentLevel=" + currentLevel +
                '}';
    }
}
