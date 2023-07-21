package dev.microcontrollers.crosshairtweaks;

import dev.microcontrollers.crosshairtweaks.config.CrosshairTweaksConfig;
import net.fabricmc.api.ModInitializer;

public class CrosshairTweaks implements ModInitializer {

	@Override
	public void onInitialize() {
		CrosshairTweaksConfig.INSTANCE.load();
	}
}