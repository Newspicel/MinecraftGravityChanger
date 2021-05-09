package net.spaceblock.mc.gravity.gravitychanger;

import net.spaceblock.mc.gravity.gravitychanger.mode.GravityMode;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityType;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import javax.inject.Singleton;

@Singleton
public class GravityEntityService {

    public void setGravityModeToEntity(Entity entity, GravityMode gravityMode) {
        int currentLevel = gravityMode.getCurrentLevel();
        if (entity.hasMetadata("ignoreGravityChanger")) {
            entity.setGravity(true);
            return;
        }


        if (gravityMode.getGravityType() != GravityType.DEFAULT_GRAVITATION) {
            //entity.setFallDistance(0.0f);
        }

        switch (gravityMode.getGravityType()) {
            case DEFAULT_GRAVITATION:
            case LOWER_GRAVITATION:
            case MORE_GRAVITATION:
                entity.setGravity(true);
                entity.setVelocity(entity.getVelocity().multiply(currentLevel));
                break;
            case NO_GRAVITATION:
            case EXTREME_Y_GRAVITATION:
                entity.setGravity(false);
                entity.setVelocity(entity.getVelocity().multiply(currentLevel));
                break;
            case ANTI_GRAVITATION:
                entity.setGravity(false);
                entity.setVelocity(new Vector(0, currentLevel * 0.11, 0));
                break;
        }
    }
}
