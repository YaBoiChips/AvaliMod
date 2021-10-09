package tombchips.avalimod.core.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import tombchips.avalimod.AvaliMod;

import java.util.ArrayList;
import java.util.List;

public class ASurfaceBuilders {

    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();



    public static <SBC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation tbid = AvaliMod.createResource(id);
        surfaceBuilder.setRegistryName(tbid);
        surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }

    public static void init() {
    }
}
