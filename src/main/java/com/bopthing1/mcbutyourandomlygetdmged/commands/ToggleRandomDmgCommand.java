package com.bopthing1.mcbutyourandomlygetdmged.commands;

import com.bopthing1.mcbutyourandomlygetdmged.event.ModEvents;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

public class ToggleRandomDmgCommand {
    public ToggleRandomDmgCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("randomdmg").then(
                        Commands.literal("toggle").executes((command) -> {
                            return toggleRandomDmg(command.getSource());
                        })
                )
        );
    }

    public static int toggleRandomDmg(CommandSourceStack sourceStack) {
        if (!sourceStack.hasPermission(2)) {
            return 0;
        }
        if (ModEvents.canDoServerTick) {
            ModEvents.canDoServerTick = false;
            sourceStack.getPlayer().sendSystemMessage(Component.literal("§4random damage turned off"));
        } else {
            sourceStack.getPlayer().sendSystemMessage(Component.literal("§2random damage turned on"));
            ModEvents.canDoServerTick = true;
        }
        
        return 1;
    }
}