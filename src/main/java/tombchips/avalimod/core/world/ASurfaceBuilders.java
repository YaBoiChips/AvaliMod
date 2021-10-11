package tombchips.avalimod.core.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.dimension.biomes.surfacebuilder.AvalonDesertSB;

import java.util.ArrayList;
import java.util.List;

public class ASurfaceBuilders {

    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

    public static final SurfaceBuilder<SurfaceBuilderConfig> AVALON_DESERT = createSurfaceBuilder("ice_valley", new AvalonDesertSB(SurfaceBuilderConfig.CODEC));



    public static <SBC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation tbid = AvaliMod.createResource(id);
        surfaceBuilder.setRegistryName(tbid);
        surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }

    public static void init() {
    }
}
