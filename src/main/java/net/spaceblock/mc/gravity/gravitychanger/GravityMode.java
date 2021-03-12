package net.spaceblock.mc.gravity.gravitychanger;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    public void setGravityModeToLivingEntity(LivingEntity livingEntity) {
        livingEntity.setFallDistance(0.0f);

        livingEntity.removePotionEffect(PotionEffectType.SLOW_FALLING);
        livingEntity.removePotionEffect(PotionEffectType.SLOW);
        livingEntity.removePotionEffect(PotionEffectType.LEVITATION);
        livingEntity.removePotionEffect(PotionEffectType.JUMP);

        if (livingEntity.hasMetadata("ignoreGravityChanger")) {
            livingEntity.setGravity(true);
            return;
        }

        if (gravityType != GravityType.NO_GRAVITATION){
            livingEntity.setGravity(true);
        }

        switch (gravityType) {
            case DEFAULT_GRAVITATION:
                break;
            case NO_GRAVITATION:
                livingEntity.setGravity(false);
                break;
            case LOWER_GRAVITATION:
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, currentLevel, false, false, false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, currentLevel, false, false, false));
                break;
            case MORE_GRAVITATION:
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false, false, false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, (-currentLevel) - 1, false, false, false));
                break;
            case ANTI_GRAVITATION:
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, currentLevel, false, false, false));
                break;
            case EXTREME_Y_GRAVITATION:
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 255, false, false, false));
                break;
        }
    }
}
