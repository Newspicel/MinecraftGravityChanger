package net.spaceblock.mc.gravity.gravitychanger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityMode;
import net.spaceblock.mc.gravity.gravitychanger.mode.GravityType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton
public class GravityCommand implements CommandExecutor, TabCompleter {

    @Inject
    private Provider<GravityController> gravityController;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1 || args.length == 2) {
                Optional.of(GravityType.valueOf(args[0])).ifPresent(gravityType -> {
                    try {
                        GravityMode gravityMode = new GravityMode().setGravity(gravityType, args.length == 1 ? gravityType.getDefaultLevel() : Integer.parseInt(args[1]));
                        String name = ((Player) sender).getWorld().getName();
                        gravityController.get().getWorldGravity().put(name, gravityMode);
                        sender.sendMessage("Set Gravity in World: " + name + " to " + gravityMode.toString());
                    } catch (GravityTypeNotAllowedException e) {
                        sender.sendMessage(e.getMessage());
                    }
                });
                return true;
            }
        } else {
            sender.sendMessage("This Command can only Run as Player");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.stream(GravityType.values()).map(Enum::name).collect(Collectors.toList());
        } else if ((args.length == 2)) {
            List<String> list = new ArrayList<>();
            Optional.of(GravityType.valueOf(args[0])).ifPresent(gravityType -> list.addAll(IntStream.rangeClosed(gravityType.getMinLevel(), gravityType.getMaxLevel()).boxed().map(String::valueOf).collect(Collectors.toList())));
            return list;
        } else {
            return Collections.emptyList();
        }
    }
}
