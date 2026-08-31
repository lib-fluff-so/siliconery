package io.mainframe;

import io.mainframe.types.SiliconeryBlock;
import io.mainframe.types.SiliconeryCreativeTab;
import io.mainframe.types.SiliconeryItems;
import io.mainframe.recipes.SiliconeryRecipeSerializers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Siliconery implements ModInitializer {
	// Mod name
	public static final String MOD_ID = "siliconery";
	// My logger
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Siliconery started!");
		LOGGER.info("Initializing...");
		SiliconeryItems.initialize();
		SiliconeryBlock.initialize();
		SiliconeryCreativeTab.initialize();
		SiliconeryRecipeSerializers.initialize();
		LOGGER.info("Done!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
