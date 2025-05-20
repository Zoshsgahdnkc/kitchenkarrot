package io.github.tt432.kitchenkarrot.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec CLIENT;

    public static ForgeConfigSpec.ConfigValue<Boolean> SHOW_WELCOME_MESSAGE;

    static {
        BUILDER.comment("Client Configs for KitchenKarrot");

        BUILDER.push("Client Settings");
        SHOW_WELCOME_MESSAGE = BUILDER.comment("Whether to display the welcome message when entering game.(Most likely an alpha warning)", "Preferably, leave it as how it was.")
                .define("Show Welcome Message", Boolean.FALSE);
        BUILDER.pop();
        CLIENT = BUILDER.build();
    }
}
