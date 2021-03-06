package net.spaceblock.mc.gravity.gravitychanger;

import net.spaceblock.mc.gravity.gravitychanger.mode.GravityType;

public class GravityTypeNotAllowedException extends Exception {
    public GravityTypeNotAllowedException(GravityType gravityType, int currentLevel) {
        super(String.format("Gravity Type: %s not allowed with Level %s!", gravityType.name(), currentLevel));
    }
}
