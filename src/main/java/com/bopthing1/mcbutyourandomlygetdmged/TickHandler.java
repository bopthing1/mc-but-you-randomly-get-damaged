package com.bopthing1.mcbutyourandomlygetdmged;

import net.minecraftforge.event.TickEvent;

public class TickHandler {
    public static int ticks = 0;

    /**
     * registers a tick so the tick counter can go up
     * @param event
     */
    public static void registerTick(TickEvent.ServerTickEvent event) {
        ticks++;
//        System.out.println(ticks);
    }

    /**
     * gets the tick count
     * @return
     */
    public static int getTicks() {
        return ticks;
    }
}
