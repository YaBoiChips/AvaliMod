package tombchips.avalimod.common.dimension.biomes;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Objects;

public abstract class BiomeBase {

    public final Biome biome;

    public BiomeBase(Biome.Climate climate, Biome.Category category, float depth, float scale, BiomeAmbience effects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnInfo mobSpawnInfo) {
        biome = new Biome(climate, category, depth, scale, effects, biomeGenerationSettings, mobSpawnInfo);
    }

    public BiomeBase(Biome.Builder builder) {
        this.biome = builder.build();
    }

    public BiomeBase(Biome biome) {
        this.biome = biome;
    }

    public Biome getBiome() {
        return this.biome;
    }

    @Nullable
    public WeightedList<ResourceLocation> getHills() {
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

    public RegistryKey<Biome> getKey() {
        return RegistryKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(WorldGenRegistries.BIOME.getKey(this.biome)));
    }

    public ResourceLocation getID(Registry<Biome> biomeRegistry) {
        return biomeRegistry.getKey(this.biome);
    }
}

