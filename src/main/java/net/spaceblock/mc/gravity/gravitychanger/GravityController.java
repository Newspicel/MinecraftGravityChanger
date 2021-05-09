package net.spaceblock.mc.gravity.gravitychanger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityMode;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class GravityController {

    private final HashMap<String, GravityMode> worldGravity = new HashMap<>();

    private final GravityEntityService gravityEntityService;
    private final GravityLivingEntityService gravityLivingEntityService;

    @Inject
    public GravityController(GravityChanger gravityChanger, GravityEntityService gravityEntityService, GravityLivingEntityService gravityLivingEntityService, GravityCommand gravityCommand) {
        this.gravityEntityService = gravityEntityService;
        this.gravityLivingEntityService = gravityLivingEntityService;

        initScheduler(gravityChanger);
        initCommand(gravityChanger, gravityCommand);
    }

    public void setup() {
    }

    private void initCommand(GravityChanger gravityChanger, GravityCommand gravityCommand) {
        PluginCommand gravity = gravityChanger.getCommand("gravity");
        Objects.requireNonNull(gravity).setExecutor(gravityCommand);
        gravity.setTabCompleter(gravityCommand);
    }

    private void initScheduler(GravityChanger gravityChanger) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(gravityChanger, () -> worldGravity.forEach((s, gravityMode) -> {
            Optional<World> optionalWorld = Optional.ofNullable(Bukkit.getWorld(s));
            optionalWorld.ifPresent(world -> world.getEntities().stream()
                    .filter(entity -> (entity instanceof LivingEntity))
                    .forEach(entity -> gravityLivingEntityService.setGravityModeToLivingEntity((LivingEntity) entity, gravityMode)));
            optionalWorld.ifPresent(world -> world.getEntities().stream()
                    .filter(entity -> !(entity instanceof LivingEntity))
                    .forEach(entity -> gravityEntityService.setGravityModeToEntity(entity, gravityMode)));
        }), 20L, 20L);
    }

    public HashMap<String, GravityMode> getWorldGravity() {
        return worldGravity;
    }
}
