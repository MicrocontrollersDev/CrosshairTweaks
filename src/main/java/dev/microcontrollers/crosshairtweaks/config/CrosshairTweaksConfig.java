package dev.microcontrollers.crosshairtweaks.config;


import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class CrosshairTweaksConfig {
    public static GsonConfigInstance<CrosshairTweaksConfig> configInstance = new GsonConfigInstance<>(CrosshairTweaksConfig.class, Path.of(FabricLoader.getInstance().getConfigDir().toString(), "crosshairtweaks.json"));

    @ConfigEntry
    public static boolean hideInContainers = true;

    @ConfigEntry
    public static boolean showInPerspective = false;

    @ConfigEntry
    public static boolean removeBlending = false;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(configInstance, ((defaults, config, builder) -> builder
                .title(Text.literal("Crosshair Tweaks"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Trader Notifications"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide in Containers"))
                                .description((OptionDescription) Text.literal("Hides crosshair when a container is opened. Great for containers with translucent backgrounds."))
                                .binding(defaults.hideInContainers, () -> config.hideInContainers, v -> config.hideInContainers = v)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Show in Third Person"))
                                .description((OptionDescription) Text.literal("Shows the crosshair when in third person."))
                                .binding(defaults.showInPerspective, () -> config.showInPerspective, v -> config.showInPerspective = v)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Remove Blending"))
                                .description((OptionDescription) Text.literal("Removes color blending on the crosshair, making it always white."))
                                .binding(defaults.removeBlending, () -> config.removeBlending, v -> config.removeBlending = v)
                                .build())
                        .build())

        )).generateScreen(parent);
    }
}
