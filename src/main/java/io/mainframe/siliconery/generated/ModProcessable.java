package io.mainframe.siliconery.generated;

/**
 * Every processable material: what refined forms (ingot/plate/casing/block/nugget/dust)
 * actually exist for it. Raw ore items stay on {@link ModOreable} — this is everything
 * downstream of the raw ore.
 */
public enum ModProcessable {
    IRON("iron", false, true, true, false, false, true),
    COPPER("copper", false, true, true, false, false, true),
    ZINC("zinc", true, true, true, true, true, true),
    TIN("tin", true, true, true, true, true, true),
    SILVER("silver", true, true, true, true, true, true),
    LITHIUM("lithium", true, false, false, false, false, true),
    INDIUM("indium", true, false, false, false, false, true);

    public final String name;
    public final boolean hasIngot;
    public final boolean hasPlate;
    public final boolean hasCasing;
    public final boolean hasBlock;
    public final boolean hasNugget;
    public final boolean hasDust;

    ModProcessable(String name, boolean hasIngot, boolean hasPlate, boolean hasCasing,
                   boolean hasBlock, boolean hasNugget, boolean hasDust) {
        this.name = name;
        this.hasIngot = hasIngot;
        this.hasPlate = hasPlate;
        this.hasCasing = hasCasing;
        this.hasBlock = hasBlock;
        this.hasNugget = hasNugget;
        this.hasDust = hasDust;
    }
}
