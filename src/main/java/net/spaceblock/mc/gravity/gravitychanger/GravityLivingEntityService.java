package net.spaceblock.mc.gravity.gravitychanger;

import com.google.inject.Singleton;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityMode;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Singleton
public class GravityLivingEntityService {

    public void setGravityModeToLivingEntity(LivingEntity livingEntity, GravityMode gravityMode) {
        int currentLevel = gravityMode.getCurrentLevel();
        if (livingEntity.hasMetadata("ignoreGravityChanger")) {
            livingEntity.setGravity(true);
            return;
        }

        if (gravityMode.getGravityType() != GravityType.DEFAULT_GRAVITATION) {
            livingEntity.setFallDistance(0.0f);
            livingEntity.removePotionEffect(PotionEffectType.SLOW_FALLING);
            livingEntity.removePotionEffect(PotionEffectType.SLOW);
            livingEntity.removePotionEffect(PotionEffectType.LEVITATION);
            livingEntity.removePotionEffect(PotionEffectType.JUMP);
        }

        switch (gravityMode.getGravityType()) {
            case DEFAULT_GRAVITATION:
                livingEntity.setGravity(true);
                break;
            case NO_GRAVITATION:
                livingEntity.setGravity(false);
                break;
            case LOWER_GRAVITATION:
                livingEntity.setGravity(true);
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, currentLevel, false, false, false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, currentLevel, false, false, false));
                break;
            case MORE_GRAVITATION:
                livingEntity.setGravity(true);
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, false, false, false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, (-currentLevel) - 1, false, false, false));
                break;
            case ANTI_GRAVITATION:
                livingEntity.setGravity(true);
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, currentLevel, false, false, false));
                break;
            case EXTREME_Y_GRAVITATION:
                livingEntity.setGravity(true);
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 255, false, false, false));
                break;
        }
    }
}
