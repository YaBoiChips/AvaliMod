package tombchips.avalimod.core.world;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import tombchips.avalimod.core.ABlocks;

public class AConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> AVALON_DESERT = ASurfaceBuilders.AVALON_DESERT.configured(new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState(), Blocks.DIRT.defaultBlockState()));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> AVALON_FROZEN = ASurfaceBuilders.AVALON_FROZEN.configured(new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState()));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> AVALON_OCEAN = ASurfaceBuilders.AVALON_OCEAN.configured(new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState()));


    public static void register() {
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_desert", AConfiguredSurfaceBuilders.AVALON_DESERT);
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_frozen", AConfiguredSurfaceBuilders.AVALON_FROZEN);
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_ocean", AConfiguredSurfaceBuilders.AVALON_OCEAN);

    }
}
