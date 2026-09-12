package io.mainframe.siliconery.item;

import io.mainframe.siliconery.generated.ModOreable;
import io.mainframe.siliconery.generated.ModProcessable;
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
    public static final Item CROSS_HEAD_BLANK = registerItem(ModItemIds.CROSS_HEAD_BLANK, Item::new, new Item.Properties());
    public static final Item TREETAP = registerItem(ModItemIds.TREETAP, Item::new,
            new Item.Properties().durability(25).stacksTo(1));

    // Raw ore item only — ore/deepslate ore blocks live in ModBlockList, both keyed off ModOreable.
    public static final Map<ModOreable, Item> RAW_ORES = new EnumMap<>(ModOreable.class);
    static {
        for (ModOreable ore : ModOreable.values()) {
            RAW_ORES.put(ore, registerItem(ModItemIds.raw(ore.name), Item::new, new Item.Properties()));
        }
    }

    // Refined forms, keyed off ModProcessable — each map only gets an entry where the material's flag is set.
    public static final Map<ModProcessable, Item> INGOTS = new EnumMap<>(ModProcessable.class);
    public static final Map<ModProcessable, Item> PLATES = new EnumMap<>(ModProcessable.class);
    public static final Map<ModProcessable, Item> CASINGS = new EnumMap<>(ModProcessable.class);
    public static final Map<ModProcessable, Item> NUGGETS = new EnumMap<>(ModProcessable.class);
    public static final Map<ModProcessable, Item> DUSTS = new EnumMap<>(ModProcessable.class);
    static {
        for (ModProcessable mat : ModProcessable.values()) {
            if (mat.hasIngot) INGOTS.put(mat, registerItem(ModItemIds.ingot(mat.name), Item::new, new Item.Properties()));
            if (mat.hasPlate) PLATES.put(mat, registerItem(ModItemIds.plate(mat.name), Item::new, new Item.Properties()));
            if (mat.hasCasing) CASINGS.put(mat, registerItem(ModItemIds.casing(mat.name), Item::new, new Item.Properties()));
            if (mat.hasNugget) NUGGETS.put(mat, registerItem(ModItemIds.nugget(mat.name), Item::new, new Item.Properties()));
            if (mat.hasDust) DUSTS.put(mat, registerItem(ModItemIds.dust(mat.name), Item::new, new Item.Properties()));
        }
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {}
}