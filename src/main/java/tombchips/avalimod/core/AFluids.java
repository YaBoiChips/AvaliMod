package tombchips.avalimod.core;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import tombchips.avalimod.AvaliMod;
import tombchips.avalimod.common.dimension.fluids.AmmoniaFluid;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AFluids {

    public static List<FlowingFluid> fluids = new ArrayList<>();

    public static final ResourceLocation AMMONIA_STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation AMMONIA_FLOWING_TEXTURE = new ResourceLocation("block/water_flow");


    public static final FlowingFluid FLOWING_AMMONIA = registerfluid("flowing_ammonia", new AmmoniaFluid.Flowing());
    public static final FlowingFluid AMMONIA = registerfluid("ammonia", new AmmoniaFluid.Source());

//    public static final ForgeFlowingFluid.Properties AMMONIA_PROPERTIES = new ForgeFlowingFluid.Properties(() -> AMMONIA.getFluid(), ()-> FLOWING_AMMONIA.getFluid(),




    static @Nonnull
    <T extends FlowingFluid> T registerfluid(String id, @Nonnull T fluid) {
        fluid.setRegistryName(AvaliMod.createResource(id));

        fluids.add(fluid);

        return fluid;
    }


    public static void init() {
    }
}
