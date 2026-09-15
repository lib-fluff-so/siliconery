package io.mainframe.siliconery.generated;

/**
 * Every processable material: what refined forms (ingot/plate/casing/block/nugget/dust)
 * actually exist for it. Raw ore items stay on {@link ModOreable} — this is everything
 * downstream of the raw ore.
 */
public enum ModProcessable {
    IRON("iron", false, true, true, false, false, true, 0xB0B0B0),
    COPPER("copper", false, true, true, false, false, true, 0xD98F52),
    ZINC("zinc", true, true, true, true, true, true, 0x78b5FF),
    TIN("tin", true, true, true, true, true, true, 0xDAD7CE),
    SILVER("silver", true, true, true, true, true, true, 0xFFFFFF),
    LITHIUM("lithium", true, false, false, false, false, true, 0xD5DBA0),
    INDIUM("indium", true, false, false, false, false, true, 0x7272CF);

    public final String name;
    public final boolean hasIngot;
    public final boolean hasPlate;
    public final boolean hasCasing;
    public final boolean hasBlock;
    public final boolean hasNugget;
    public final boolean hasDust;
    public final int tintColor;

    ModProcessable(String name, boolean hasIngot, boolean hasPlate, boolean hasCasing,
                   boolean hasBlock, boolean hasNugget, boolean hasDust, int tintColor) {
        this.name = name;
        this.hasIngot = hasIngot;
        this.hasPlate = hasPlate;
        this.hasCasing = hasCasing;
        this.hasBlock = hasBlock;
        this.hasNugget = hasNugget;
        this.hasDust = hasDust;
        this.tintColor = tintColor;
    }
}
