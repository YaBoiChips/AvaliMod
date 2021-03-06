package tombchips.avalimod.common.dimension.biomes;

import net.minecraft.block.Blocks;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import tombchips.avalimod.core.world.AConfiguredSurfaceBuilders;

import java.awt.*;

public class AvalonOceanBiome extends BiomeBase {
    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = AConfiguredSurfaceBuilders.AVALON_OCEAN;
    static final Biome.RainType PRECIPATATION = Biome.RainType.SNOW;
    static final Biome.Category CATEGORY = Biome.Category.ICY;
    static final float DEPTH = -0.80F;
    static final float SCALE = -0.1F;
    static final float TEMPERATURE = -50.0F;
    static final float DOWNFALL = 0.9F;
    static final int WATER_COLOR = 31064;
    static final int WATER_FOG_COLOR = 38064;
    static final Biome.Climate WEATHER = new Biome.Climate(PRECIPATATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SURFACE_BUILDER);


    public AvalonOceanBiome() {
        super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeAmbience.Builder()).waterColor(WATER_COLOR).waterFogColor(WATER_FOG_COLOR)
                .waterColor(WATER_COLOR)
                .waterFogColor(WATER_FOG_COLOR)
                .fogColor(3158064)
                .skyColor(new Color(255, 90, 35).getRGB())
                .ambientParticle(new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.00428F))
                .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                .ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                .ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0010D))
                .backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_END)).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }

    static {
        GENERATION_SETTINGS.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 22)).range(46).squared().count(22));
        GENERATION_SETTINGS.addCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE);
        SPAWN_SETTINGS.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.SNOW_GOLEM, 102, 4, 4));
    }
}