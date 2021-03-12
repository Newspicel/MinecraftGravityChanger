package net.spaceblock.mc.gravity.gravitychanger;

import org.bukkit.World;

import java.util.HashMap;


public class GravityAPI {

    private static final GravityAPI instance = new GravityAPI();

    private final HashMap<String, GravityMode> worldGravity = new HashMap<>();

    public static GravityAPI getInstance() {
        return instance;
    }

    public void setWorldGravity(World world, GravityMode gravityMode) {
        worldGravity.put(world.getName(), gravityMode);
    }

    public HashMap<String, GravityMode> getWorldGravity() {
        return worldGravity;
    }
}
