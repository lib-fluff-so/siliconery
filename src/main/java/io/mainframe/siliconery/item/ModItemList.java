package io.mainframe.siliconery.item;

import io.mainframe.siliconery.misc.ModPlateable;

import java.util.EnumMap;
import java.util.Map;

import static io.mainframe.siliconery.item.ModItemTools.registerItem;

public class ModItemList {
    public static final net.minecraft.world.item.Item MGSI = registerItem(ModItemIds.MGSI, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item MGSI_POWDER = registerItem(ModItemIds.MGSI_POWDER, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item SLAG = registerItem(ModItemIds.SLAG, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item FORGE_HAMMER = registerItem(ModItemIds.FORGE_HAMMER, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(80).stacksTo(1));
    public static final net.minecraft.world.item.Item CUTTER = registerItem(ModItemIds.CUTTER, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(80).stacksTo(1));
    public static final net.minecraft.world.item.Item LATEX = registerItem(ModItemIds.LATEX, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item RUBBER = registerItem(ModItemIds.RUBBER, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item TREETAP = registerItem(ModItemIds.TREETAP, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(25).stacksTo(1));

    public static final Map<ModPlateable, net.minecraft.world.item.Item> PLATES = new EnumMap<>(ModPlateable.class);
    public static final Map<ModPlateable, net.minecraft.world.item.Item> CASINGS = new EnumMap<>(ModPlateable.class);
    
    static {
        for (ModPlateable mat : ModPlateable.values()) {
            net.minecraft.world.item.Item plate = registerItem(
                    ModItemIds.plate(mat.name),
                    net.minecraft.world.item.Item::new,
                    new net.minecraft.world.item.Item.Properties()
            );
            PLATES.put(mat, plate);
            net.minecraft.world.item.Item casing = registerItem(
                    ModItemIds.casing(mat.name),
                    net.minecraft.world.item.Item::new,
                    new net.minecraft.world.item.Item.Properties()
            );
            CASINGS.put(mat, casing);
        }
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {}
}