package tombchips.avalimod.core.world;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class AConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> AVALON_DESERT = ASurfaceBuilders.AVALON_DESERT.configured(new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.DIRT.defaultBlockState()));


    public static void register() {
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "avalon_desert", AConfiguredSurfaceBuilders.AVALON_DESERT);
    }
}
