package io.mainframe.siliconery.item;

import io.mainframe.siliconery.misc.Plateable;

import java.util.EnumMap;
import java.util.Map;

import static io.mainframe.siliconery.item.Item.registerItem;

public class Items {
    public static final net.minecraft.world.item.Item MGSI = registerItem(io.mainframe.siliconery.item.ItemIds.MGSI, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item MGSI_POWDER = registerItem(io.mainframe.siliconery.item.ItemIds.MGSI_POWDER, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item SLAG = registerItem(io.mainframe.siliconery.item.ItemIds.SLAG, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item FORGE_HAMMER = registerItem(io.mainframe.siliconery.item.ItemIds.FORGE_HAMMER, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(80).stacksTo(1));
    public static final net.minecraft.world.item.Item CUTTER = registerItem(io.mainframe.siliconery.item.ItemIds.CUTTER, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(80).stacksTo(1));
    public static final net.minecraft.world.item.Item LATEX = registerItem(io.mainframe.siliconery.item.ItemIds.LATEX, net.minecraft.world.item.Item::new, new net.minecraft.world.item.Item.Properties());
    public static final net.minecraft.world.item.Item TREETAP = registerItem(ItemIds.TREETAP, net.minecraft.world.item.Item::new,
            new net.minecraft.world.item.Item.Properties().durability(60).stacksTo(1));

    public static final Map<io.mainframe.siliconery.misc.Plateable, net.minecraft.world.item.Item> PLATES = new EnumMap<>(io.mainframe.siliconery.misc.Plateable.class);
    public static final Map<io.mainframe.siliconery.misc.Plateable, net.minecraft.world.item.Item> CASINGS = new EnumMap<>(io.mainframe.siliconery.misc.Plateable.class);
    
    static {
        for (Plateable mat : io.mainframe.siliconery.misc.Plateable.values()) {
            net.minecraft.world.item.Item plate = registerItem(
                    io.mainframe.siliconery.item.ItemIds.plate(mat.name),
                    net.minecraft.world.item.Item::new,
                    new net.minecraft.world.item.Item.Properties()
            );
            PLATES.put(mat, plate);
        }
    }
    
    static {
        for (io.mainframe.siliconery.misc.Plateable mat : io.mainframe.siliconery.misc.Plateable.values()) {
            net.minecraft.world.item.Item casing = registerItem(
                    io.mainframe.siliconery.item.ItemIds.casing(mat.name),
                    net.minecraft.world.item.Item::new,
                    new net.minecraft.world.item.Item.Properties()
            );
            CASINGS.put(mat, casing);
        }
    }

    public static void initialize() { }
}