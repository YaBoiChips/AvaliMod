package tombchips.avalimod.core.world;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import tombchips.avalimod.AvaliMod;

public class ADimensionTypes {

    public static final ResourceLocation AVALON_ID = AvaliMod.createResource("avalon");

    public static final RegistryKey<World> AVALON_WORLD_KEY = RegistryKey.create(Registry.DIMENSION_REGISTRY, AVALON_ID);
}
