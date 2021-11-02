package tombchips.avalimod.core.world;


import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import tombchips.avalimod.core.ABlocks;

public class AConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_DESERT = ASurfaceBuilders.AVALON_DESERT.configured(new SurfaceBuilderBaseConfiguration(ABlocks.FROZEN_AVALON_STONE.defaultBlockState(), ABlocks.FROZEN_AVALON_STONE.defaultBlockState(), ABlocks.FROZEN_AVALON_STONE.defaultBlockState()));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_FROZEN = ASurfaceBuilders.AVALON_FROZEN.configured(new SurfaceBuilderBaseConfiguration(Blocks.SNOW_BLOCK.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState()));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_OCEAN = ASurfaceBuilders.AVALON_OCEAN.configured(new SurfaceBuilderBaseConfiguration(Blocks.SNOW_BLOCK.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState(), ABlocks.AMMONIA_ICE.defaultBlockState()));


    public static void register() {
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_desert", AConfiguredSurfaceBuilders.AVALON_DESERT);
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_frozen", AConfiguredSurfaceBuilders.AVALON_FROZEN);
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_ocean", AConfiguredSurfaceBuilders.AVALON_OCEAN);

    }
}
