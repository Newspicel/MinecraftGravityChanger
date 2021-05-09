package net.spaceblock.mc.gravity.gravitychanger.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.spaceblock.mc.gravity.gravitychanger.GravityController;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityMode;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Optional;


@Singleton
public class GravityAPI {

    private static GravityAPI instance;
    private GravityController gravityController;

    @Inject
    public GravityAPI(GravityController gravityController) {
        this.gravityController = gravityController;
    }


    public static Optional<GravityAPI> getInstance() {
        return Optional.ofNullable(instance);
    }

    public void setWorldGravity(World world, GravityMode gravityMode) {
        gravityController.getWorldGravity().put(world.getName(), gravityMode);
    }

    public HashMap<String, GravityMode> getWorldGravity() {
        return gravityController.getWorldGravity();
    }
}
