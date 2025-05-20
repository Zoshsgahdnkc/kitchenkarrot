package io.github.tt432.kitchenkarrot.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModClientConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec CLIENT;

    public static ModConfigSpec.ConfigValue<Boolean> SHOW_WELCOME_MESSAGE;

    static {
        BUILDER.comment("Client Configs for KitchenKarrot");

        BUILDER.push("Client Settings");
        SHOW_WELCOME_MESSAGE =
                BUILDER.comment(
                                "Whether to display the welcome message when entering game.(Most likely an alpha warning)",
                                "Preferably, leave it as how it was.")
                        .define("Show Welcome Message", Boolean.FALSE);
        BUILDER.pop();
        CLIENT = BUILDER.build();
    }
}
