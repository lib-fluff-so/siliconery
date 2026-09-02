package io.mainframe.siliconery;

import io.mainframe.siliconery.block.Block;
import io.mainframe.siliconery.block.Blocks;
import io.mainframe.siliconery.item.Item;
import io.mainframe.siliconery.item.Items;
import io.mainframe.siliconery.misc.CreativeTab;
import io.mainframe.siliconery.misc.SiliconeryTags;
import io.mainframe.siliconery.recipe.RecipeSerializers;
import io.mainframe.siliconery.world.SiliconeryFoliagePlacerTypes;
import io.mainframe.siliconery.world.SiliconeryTreeDecoratorTypes;
import io.mainframe.siliconery.world.SiliconeryWorldGeneration;
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
		Item.initialize();
		Items.initialize();
		Block.initialize();
		Blocks.initialize();
		SiliconeryFoliagePlacerTypes.initialize();
		CreativeTab.initialize();
		RecipeSerializers.initialize();
		SiliconeryWorldGeneration.register();
		SiliconeryTreeDecoratorTypes.register();
		LOGGER.info("Done!");
	}

	public static Identifier id(String path) { return Identifier.fromNamespaceAndPath(MOD_ID, path); }
}
