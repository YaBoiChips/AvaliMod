package tombchips.avalimod.common.dimension.biomes;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.world.AConfiguredSurfaceBuilders;

import java.awt.*;

public class AvalonOceanBiome extends BiomeBase {
    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = AConfiguredSurfaceBuilders.AVALON_OCEAN;
    static final Biome.Precipitation PRECIPATATION = Biome.Precipitation.SNOW;
    static final Biome.BiomeCategory CATEGORY = Biome.BiomeCategory.ICY;
    static final float DEPTH = -0.80F;
    static final float SCALE = -0.1F;
    static final float TEMPERATURE = -50.0F;
    static final float DOWNFALL = 0.9F;
    static final int WATER_COLOR = 31064;
    static final int WATER_FOG_COLOR = 38064;
    static final Biome.ClimateSettings WEATHER = new Biome.ClimateSettings(PRECIPATATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
    static final MobSpawnSettings.Builder SPAWN_SETTINGS = new MobSpawnSettings.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SURFACE_BUILDER);


    public AvalonOceanBiome() {
        super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeSpecialEffects.Builder())
                .waterColor(WATER_COLOR)
                .waterFogColor(WATER_FOG_COLOR)
                .fogColor(3158064)
                .skyColor(new Color(255, 90, 35).getRGB())
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.WARPED_SPORE, 0.00428F))
                .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0010D))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_END)).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }

    static {
        GENERATION_SETTINGS.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        GENERATION_SETTINGS.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 10)).rangeTriangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)).squared().count(22));
        GENERATION_SETTINGS.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, ABlocks.FROZEN_AVALON_STONE.defaultBlockState(), 23)).rangeTriangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)).squared().count(22));
        GENERATION_SETTINGS.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, ABlocks.AVALON_STONE.defaultBlockState(), 33)).rangeTriangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)).squared().count(22));

    }
}