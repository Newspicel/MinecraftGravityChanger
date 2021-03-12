package net.spaceblock.mc.gravity.gravitychanger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.Optional;

public class GravityRunnable implements Runnable {

    @Override
    public void run() {
        GravityAPI.getInstance().getWorldGravity().forEach((s, gravityMode) -> {
            Optional<World> optionalWorld = Optional.ofNullable(Bukkit.getWorld(s));
            optionalWorld.ifPresent(world -> world.getEntities().stream()
                    .filter(entity -> (entity instanceof LivingEntity))
                    .forEach(entity -> gravityMode.setGravityModeToLivingEntity((LivingEntity) entity)));

        });
    }
}
