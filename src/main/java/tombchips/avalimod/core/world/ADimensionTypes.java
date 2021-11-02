package tombchips.avalimod.core.world;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import tombchips.avalimod.AvaliMod;




public class ADimensionTypes {

    public static final ResourceLocation AVALON_ID = AvaliMod.createResource("avalon");

    public static final ResourceKey<Level> AVALON_WORLD_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, AVALON_ID);
}
