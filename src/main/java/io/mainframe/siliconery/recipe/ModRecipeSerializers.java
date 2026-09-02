package io.mainframe.siliconery.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.mainframe.siliconery.Siliconery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {

    private static final MapCodec<ModRecipeItemTool> TOOL_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("tool").forGetter(r -> r.toolIngredient),
                    Ingredient.CODEC.fieldOf("input").forGetter(r -> r.inputIngredient),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result),
                    Codec.INT.optionalFieldOf("tool_damage", 1).forGetter(r -> r.toolDamage) // дефолт 1, если не указано
            ).apply(instance, ModRecipeItemTool::new)
    );

    private static final StreamCodec<RegistryFriendlyByteBuf, ModRecipeItemTool> TOOL_STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, r -> r.toolIngredient,
                    Ingredient.CONTENTS_STREAM_CODEC, r -> r.inputIngredient,
                    ItemStackTemplate.STREAM_CODEC, r -> r.result,
                    ByteBufCodecs.VAR_INT, r -> r.toolDamage,
                    ModRecipeItemTool::new
            );

    public static final RecipeSerializer<ModRecipeItemTool> TOOL_RECIPE_SERIALIZER =
            Registry.register(
                    BuiltInRegistries.RECIPE_SERIALIZER,
                    Siliconery.id("tool_recipe"),
                    new RecipeSerializer<>(TOOL_CODEC, TOOL_STREAM_CODEC)
            );

    public static void initialize() {
    }
}