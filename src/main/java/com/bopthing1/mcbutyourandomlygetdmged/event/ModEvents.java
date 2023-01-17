package com.bopthing1.mcbutyourandomlygetdmged.event;

import com.bopthing1.mcbutyourandomlygetdmged.MCRDMod;
import com.bopthing1.mcbutyourandomlygetdmged.TickHandler;

import com.bopthing1.mcbutyourandomlygetdmged.commands.ToggleRandomDmgCommand;
import com.bopthing1.mcbutyourandomlygetdmged.config.MCRDCommonConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCRDMod.MOD_ID)
/**
 * events baby
 */
public class ModEvents {

    /***
     * generates a random int with a specified min and max
     * @param min
     * @param max
     * @return
     */
    private static int generateRandomInt(int min, int max) {
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        return rand;
    }



    private static int randomNum;
    public static boolean canDoServerTick = false;


    @SubscribeEvent
    public static void ServerTickEvent(TickEvent.ServerTickEvent event) {
//        System.out.println("TICK");
        randomNum =generateRandomInt(MCRDCommonConfigs.MIN_DELAY.get(), MCRDCommonConfigs.MAX_DELAY.get());
        if (canDoServerTick == true) {
            TickHandler.registerTick(event);
//        System.out.println(TickHandler.getTicks());
            if ((TickHandler.getTicks() % randomNum) == 0) {
//            System.out.println("DELAY REWACHED@");
                for (int i = 0; i < event.getServer().getPlayerList().getPlayers().size(); i++) {
                    Player player = event.getServer().getPlayerList().getPlayers().get(i);
                    if (!player.isCreative()) {
                        player.setHealth(player.getHealth() - generateRandomInt(MCRDCommonConfigs.MIN_DAMAGE.get(), MCRDCommonConfigs.MAX_DAMAGE.get()));
                        player.getLevel().playSound(null, new BlockPos(player.getX(), player.getY(), player.getZ()), SoundEvents.PLAYER_HURT, SoundSource.PLAYERS);
                    } else {
//                    System.out.println("creative!");
                    }
//                System.out.println(player.getName());
                }

            }
        } else {
//            event.getServer().sendSystemMessage(Component.literal("bro bro bro bro it no wok!"));
        }
    }

    @SubscribeEvent
    public static void RegisterCommandsEvent(RegisterCommandsEvent event) {
        System.out.println("registering ommands");

        new ToggleRandomDmgCommand(event.getDispatcher());
    }

}

