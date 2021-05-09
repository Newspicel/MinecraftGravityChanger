package net.spaceblock.mc.gravity.gravitychanger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public final class GravityChanger extends JavaPlugin {

    private Injector injector;

    @Override
    public void onEnable() {
        try {
            injector = Guice.createInjector(new GravityGuiceModule(this));
            injector.injectMembers(this);
            injector.getInstance(GravityController.class).setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
    }

}
