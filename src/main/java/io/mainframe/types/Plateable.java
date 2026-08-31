package io.mainframe.types;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum Plateable {
    IRON("iron", Items.IRON_INGOT),
    COPPER("copper", Items.COPPER_INGOT);

    public final String name;
    public final Item ingotInput;

    Plateable(String name, Item ingotInput) {
        this.name = name;
        this.ingotInput = ingotInput;
    }
}
