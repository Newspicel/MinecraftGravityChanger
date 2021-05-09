package net.spaceblock.mc.gravity.gravitychanger;

import com.google.inject.AbstractModule;

public class GravityGuiceModule extends AbstractModule {

    private final GravityChanger gravityChanger;

    public GravityGuiceModule(GravityChanger gravityChanger) {
        this.gravityChanger = gravityChanger;
    }

    @Override
    protected void configure() {
        bind(GravityChanger.class).toInstance(gravityChanger);
    }
}
