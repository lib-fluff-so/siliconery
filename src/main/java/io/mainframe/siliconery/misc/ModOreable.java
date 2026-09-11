package io.mainframe.siliconery.misc;

/**
 * EVERY ORE HERE
 */
public enum ModOreable {
    ZINC("zinc", 16, 8, -24, 128, 2.0F, 4.5F, 200, 100),
    TIN("tin", 1, 16, -32, 64, 3.0F, 4.5F, 140, 70),
    SILVER("silver", 2, 6, -48, 32, 3.0F, 4.5F, 200, 100),
    LITHIUM("lithium", 6, 6, -16, 100, 1.5F, 3.5F, 220, 110),
    INDIUM("indium", 2, 4, -48, 16, 3.5F, 5.0F, 260, 130);

    public final String name;
    public final int veinsPerChunk;
    public final int veinSize;
    public final int minY;
    public final int maxY;
    public final float hardness;
    public final float deepslateHardness;
    public final int smeltingCookTime;
    public final int blastingCookTime;

    ModOreable(String name, int veinsPerChunk, int veinSize, int minY, int maxY,
               float hardness, float deepslateHardness, int smeltingCookTime, int blastingCookTime) {
        this.name = name;
        this.veinsPerChunk = veinsPerChunk;
        this.veinSize = veinSize;
        this.minY = minY;
        this.maxY = maxY;
        this.hardness = hardness;
        this.deepslateHardness = deepslateHardness;
        this.smeltingCookTime = smeltingCookTime;
        this.blastingCookTime = blastingCookTime;
    }
}