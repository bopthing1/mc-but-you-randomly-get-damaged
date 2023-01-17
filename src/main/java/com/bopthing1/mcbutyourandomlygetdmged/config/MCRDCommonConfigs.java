package com.bopthing1.mcbutyourandomlygetdmged.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MCRDCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MIN_DELAY;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_DELAY;
    public static final ForgeConfigSpec.ConfigValue<Integer> MIN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_DAMAGE;

    static {
        BUILDER.push("MCRD configs");

        MIN_DELAY = BUILDER.comment("minimum delay for the random damage.\nNOTE: can not be higher than max delay or the mod could stop working")
                .define("min delay", 490);
        MAX_DELAY = BUILDER.comment("maximum delay for the random damage.\nNOTE: can not be lower than min delay or the mod could stop working")
                .define("max delay", 2100);
        MIN_DAMAGE = BUILDER.comment("minimum damage for the random damage.\nNOTE: can not be higher than max damage or the mod could stop working")
                .define("min damage", 2);
        MAX_DAMAGE = BUILDER.comment("maximum damage for the random damage.\nNOTE: can not be higher than min damage or the mod could stop working")
                .define("max damage", 2);

        BUILDER.pop();
        SPEC = BUILDER.build();

        System.out.println("finished building config");
    }
}
