package io.mainframe.types;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class SiliconeryItems {
    public static final Item MGSI = registerItem(SiliconeryItemIds.MGSI, Item::new, new Item.Properties());
    public static final Item MGSI_POWDER = registerItem(SiliconeryItemIds.MGSI_POWDER, Item::new, new Item.Properties());
    public static final Item SLAG = registerItem(SiliconeryItemIds.SLAG, Item::new, new Item.Properties());
    public static final Item FORGE_HAMMER = registerItem(SiliconeryItemIds.FORGE_HAMMER, Item::new,
            new Item.Properties().durability(256).stacksTo(1));
    public static final Item CUTTER = registerItem(SiliconeryItemIds.CUTTER, Item::new,
            new Item.Properties().durability(256).stacksTo(1));
    public static final Item LATEX = registerItem(SiliconeryItemIds.LATEX, Item::new, new Item.Properties());

    public static final Map<Plateable, Item> PLATES = new EnumMap<>(Plateable.class);
    public static final Map<Plateable, Item> CASINGS = new EnumMap<>(Plateable.class);
    
    static {
        for (Plateable mat : Plateable.values()) {
            Item plate = registerItem(
                    SiliconeryItemIds.plate(mat.name),
                    Item::new,
                    new Item.Properties()
            );
            PLATES.put(mat, plate);
        }
    }
    
    static {
        for (Plateable mat : Plateable.values()) {
            Item casing = registerItem(
                    SiliconeryItemIds.casing(mat.name),
                    Item::new,
                    new Item.Properties()
            );
            CASINGS.put(mat, casing);
        }
    }

    public static Item registerItem(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
    }
}