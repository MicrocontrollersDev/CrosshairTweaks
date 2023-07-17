package dev.microcontrollers.crosshairtweaks.config;


import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
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
                        .name(Text.literal("Crosshair Tweaks"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide in Containers"))
                                .description(OptionDescription.of(Text.of("Hides crosshair when a container is opened. Great for containers with translucent backgrounds.")))
                                .binding(true, () -> hideInContainers, newVal -> hideInContainers = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Show in Third Person"))
                                .description(OptionDescription.of(Text.of("Shows the crosshair when in third person.")))
                                .binding(true, () -> showInPerspective, newVal -> showInPerspective = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Remove Blending"))
                                .description(OptionDescription.of(Text.of("Removes color blending on the crosshair, making it always white.")))
                                .binding(false, () -> removeBlending, newVal -> removeBlending = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
