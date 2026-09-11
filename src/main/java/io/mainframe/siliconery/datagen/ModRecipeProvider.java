package io.mainframe.siliconery.datagen;

import io.mainframe.siliconery.Siliconery;
import io.mainframe.siliconery.block.ModBlockList;
import io.mainframe.siliconery.misc.ModOreable;
import io.mainframe.siliconery.misc.ModProcessable;
import io.mainframe.siliconery.item.ModItemList;
import io.mainframe.siliconery.item.ModItemTags;
import io.mainframe.siliconery.recipe.ModRecipeItemTool;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    // Ingredient.of(TagKey<Item>) doesn't exist in this mapping set — only Ingredient.of(HolderSet<Item>) —
    // so tags need resolving against the registry lookup first.
    private static Ingredient tagIngredient(HolderLookup.@NonNull Provider registries, TagKey<net.minecraft.world.item.Item> tag) {
        return Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(tag));
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                SimpleCookingRecipeBuilder.smelting(
                                Ingredient.of(ModItemList.LATEX),
                                RecipeCategory.MISC,
                                CookingBookCategory.MISC,
                                ModItemList.RUBBER,
                                0.1F,
                                200
                        ).unlockedBy(getHasName(ModItemList.LATEX), has(ModItemList.LATEX))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("rubber_from_smelting")));

                for (ModOreable ore : ModOreable.values()) {
                    // Every ore has a matching ModProcessable entry of the same name (see ModProcessable).
                    ModProcessable processable = ModProcessable.valueOf(ore.name());
                    net.minecraft.world.item.Item raw = ModItemList.RAW_ORES.get(ore);
                    net.minecraft.world.item.Item ingot = ModItemList.INGOTS.get(processable);

                    SimpleCookingRecipeBuilder.smelting(
                                    tagIngredient(registries, ModItemTags.rawMaterials(ore.name)),
                                    RecipeCategory.MISC,
                                    CookingBookCategory.MISC,
                                    ingot,
                                    0.7F,
                                    ore.smeltingCookTime
                            ).unlockedBy(getHasName(raw), has(raw))
                            .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(ore.name + "_ingot_from_smelting")));

                    SimpleCookingRecipeBuilder.blasting(
                                    tagIngredient(registries, ModItemTags.rawMaterials(ore.name)),
                                    RecipeCategory.MISC,
                                    CookingBookCategory.MISC,
                                    ingot,
                                    0.7F,
                                    ore.blastingCookTime
                            ).unlockedBy(getHasName(raw), has(raw))
                            .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(ore.name + "_ingot_from_blasting")));
                }

                ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.FOOD, new ItemStackTemplate(ModItemList.CHEWING_GUM, 4))
                        .requires(ModItemList.RUBBER)
                        .requires(Items.SUGAR)
                        .requires(Items.DYE.pink())
                        .unlockedBy(getHasName(ModItemList.RUBBER), has(ModItemList.RUBBER))
                        .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id("chewing_gum_from_rubber")));

                for (ModProcessable mat : ModProcessable.values()) {
                    if (!mat.hasPlate) continue;
                    Item plate = ModItemList.PLATES.get(mat);
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_plate_from_hammer")),
                            new ModRecipeItemTool(
                                    Ingredient.of(ModItemList.FORGE_HAMMER),
                                    tagIngredient(registries, ModItemTags.ingots(mat.name)),
                                    new ItemStackTemplate(plate),
                                    1
                            ),
                            null
                    );

                    if (!mat.hasCasing) continue;
                    Item casing = ModItemList.CASINGS.get(mat);
                    output.accept(
                            ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_casing_from_hammer")),
                            new ModRecipeItemTool(
                                    Ingredient.of(ModItemList.FORGE_HAMMER),
                                    tagIngredient(registries, ModItemTags.plates(mat.name)),
                                    new ItemStackTemplate(casing, 2),
                                    2
                            ),
                            null
                    );
                }

                for (ModProcessable mat : ModProcessable.values()) {
                    if (mat.hasNugget) {
                        Item nugget = ModItemList.NUGGETS.get(mat);
                        Item ingot = ModItemList.INGOTS.get(mat);

                        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(nugget, 9))
                                .requires(tagIngredient(registries, ModItemTags.ingots(mat.name)))
                                .unlockedBy(getHasName(ingot), has(ingot))
                                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_nugget_from_ingot")));

                        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ingot, 1)
                                .pattern("III")
                                .pattern("III")
                                .pattern("III")
                                .define('I', tagIngredient(registries, ModItemTags.nuggets(mat.name)))
                                .unlockedBy(getHasName(nugget), has(nugget))
                                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_ingot_from_nugget")));
                    }

                    if (mat.hasBlock) {
                        Block block = ModBlockList.BLOCKS.get(mat);
                        Item ingot = ModItemList.INGOTS.get(mat);

                        // NOTE: there may be a better way to do this
                        // NOTE: block tags for blocks?
                        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, block.asItem(), 1)
                                .pattern("III")
                                .pattern("III")
                                .pattern("III")
                                .define('I', tagIngredient(registries, ModItemTags.ingots(mat.name)))
                                .unlockedBy(getHasName(ingot), has(ingot))
                                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_block_from_ingot")));

                        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStackTemplate(ingot, 9))
                                .requires(block.asItem())
                                .unlockedBy(getHasName(block.asItem()), has(block.asItem()))
                                .save(output, ResourceKey.create(Registries.RECIPE, Siliconery.id(mat.name + "_ingot_from_block")));
                    }
                }
            }
        };
    }

    @Override
    public @NonNull String getName() { return "Recipes"; }
}