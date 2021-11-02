package tombchips.avalimod.core.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.dimension.biomes.surfacebuilder.AvalonDesertSB;
import tombchips.avalimod.common.dimension.biomes.surfacebuilder.AvalonFrozenSB;
import tombchips.avalimod.common.dimension.biomes.surfacebuilder.AvalonOceanSB;

import java.util.ArrayList;
import java.util.List;

public class ASurfaceBuilders {

    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_DESERT = createSurfaceBuilder("avalon_desert", new AvalonDesertSB(SurfaceBuilderBaseConfiguration.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_FROZEN = createSurfaceBuilder("avalon_frozen", new AvalonFrozenSB(SurfaceBuilderBaseConfiguration.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> AVALON_OCEAN = createSurfaceBuilder("avalon_ocean", new AvalonOceanSB(SurfaceBuilderBaseConfiguration.CODEC));



    public static <SBC extends SurfaceBuilderConfiguration, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation tbid = AvaliMod.createResource(id);
        surfaceBuilder.setRegistryName(tbid);
        surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }

    public static void init() {
    }
}
