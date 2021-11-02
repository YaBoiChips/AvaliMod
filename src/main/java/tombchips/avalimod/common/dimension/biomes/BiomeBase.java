package tombchips.avalimod.common.dimension.biomes;


import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import tombchips.avalimod.mixin.BiomeAccess;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public abstract class BiomeBase {

    public final Biome biome;

    public BiomeBase(Biome.ClimateSettings climate, Biome.BiomeCategory category, float depth, float scale, BiomeSpecialEffects effects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnSettings mobSpawnInfo) {
        biome = BiomeAccess.create(climate, category, depth, scale, effects, biomeGenerationSettings, mobSpawnInfo);
    }

    public BiomeBase(Biome.BiomeBuilder builder) {
        this.biome = builder.build();
    }

    public BiomeBase(Biome biome) {
        this.biome = biome;
    }

    public Biome getBiome() {
        return this.biome;
    }

    @Nullable
    public List<ResourceLocation> getHills() {
        return null;
    }

    @Nullable
    public ResourceLocation getEdge() {
        return null;
    }

    public int getWeight() {
        return 5;
    }

    @Nullable
    public ResourceLocation getBeach() {
        return null;
    }

    public BiomeDictionary.Type[] getBiomeDictionary() {
        return new BiomeDictionary.Type[]{};
    }

    public ResourceKey<Biome> getKey() {
        return ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(BuiltinRegistries.BIOME.getKey(this.biome)));
    }

    public ResourceLocation getID(Registry<Biome> biomeRegistry) {
        return biomeRegistry.getKey(this.biome);
    }
}

