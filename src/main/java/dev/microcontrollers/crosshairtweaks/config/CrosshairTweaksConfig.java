package dev.microcontrollers.crosshairtweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CrosshairTweaksConfig {
    public static final ConfigInstance<CrosshairTweaksConfig> INSTANCE = GsonConfigInstance.createBuilder(CrosshairTweaksConfig.class)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve("crosshairtweaks.json"))
            .build();

    @ConfigEntry
    public boolean hideInContainers = true;

    @ConfigEntry
    public boolean showInPerspective = false;

    @ConfigEntry
    public boolean removeBlending = false;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, ((defaults, config, builder) -> builder
                .title(Text.literal("Crosshair Tweaks"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Crosshair Tweaks"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide in Containers"))
                                .description(OptionDescription.of(Text.of("Hides crosshair when a container is opened. Great for containers with translucent backgrounds.")))
                                .binding(defaults.hideInContainers, () -> config.hideInContainers, newVal -> config.hideInContainers = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Show in Third Person"))
                                .description(OptionDescription.of(Text.of("Shows the crosshair when in third person.")))
                                .binding(defaults.showInPerspective, () -> config.showInPerspective, newVal -> config.showInPerspective = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Remove Blending"))
                                .description(OptionDescription.of(Text.of("Removes color blending on the crosshair, making it always white.")))
                                .binding(defaults.removeBlending, () -> config.removeBlending, newVal -> config.removeBlending = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
