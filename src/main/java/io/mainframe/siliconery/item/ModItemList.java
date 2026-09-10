package io.mainframe.siliconery.item;

import io.mainframe.siliconery.misc.ModPlateable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.EnumMap;
import java.util.Map;

import static io.mainframe.siliconery.item.ModItemTools.registerItem;

public class ModItemList {
    public static final Item MGSI = registerItem(ModItemIds.MGSI, Item::new, new Item.Properties());
    public static final Item MGSI_POWDER = registerItem(ModItemIds.MGSI_POWDER, Item::new, new Item.Properties());
    public static final Item SLAG = registerItem(ModItemIds.SLAG, Item::new, new Item.Properties());
    public static final Item FORGE_HAMMER = registerItem(ModItemIds.FORGE_HAMMER, Item::new,
            new Item.Properties().durability(80).stacksTo(1));
    public static final Item CUTTER = registerItem(ModItemIds.CUTTER, Item::new,
            new Item.Properties().durability(80).stacksTo(1));
    public static final Item LATEX = registerItem(ModItemIds.LATEX, Item::new, new Item.Properties());
    public static final Item RUBBER = registerItem(ModItemIds.RUBBER, Item::new, new Item.Properties());
    public static final Item CHEWING_GUM = registerItem(ModItemIds.CHEWING_GUM, Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(0).saturationModifier(0.0F).alwaysEdible().build()));
    public static final Item TREETAP = registerItem(ModItemIds.TREETAP, Item::new,
            new Item.Properties().durability(25).stacksTo(1));
    public static final Item RAW_ZINC = registerItem(ModItemIds.RAW_ZINC, Item::new, new Item.Properties());
    public static final Item ZINC_INGOT = registerItem(ModItemIds.ZINC_INGOT, Item::new, new Item.Properties());

    public static final Map<ModPlateable, Item> PLATES = new EnumMap<>(ModPlateable.class);
    public static final Map<ModPlateable, Item> CASINGS = new EnumMap<>(ModPlateable.class);
    static {
        for (ModPlateable mat : ModPlateable.values()) {
            Item plate = registerItem(ModItemIds.plate(mat.name), Item::new, new Item.Properties());
            PLATES.put(mat, plate);
            Item casing = registerItem(ModItemIds.casing(mat.name), Item::new, new Item.Properties());
            CASINGS.put(mat, casing);
        }
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {}
}