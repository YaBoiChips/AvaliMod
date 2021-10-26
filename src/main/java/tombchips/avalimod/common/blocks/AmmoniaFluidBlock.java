package tombchips.avalimod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tombchips.avalimod.AvaliMod;

import java.util.function.Supplier;

public class AmmoniaFluidBlock extends FlowingFluidBlock {

    public AmmoniaFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);

    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state2, boolean someBool) {
        super.onPlace(state, world, pos, state2, someBool);
        if (world.dimension() != RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))){
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
            this.fizz(world, pos);
        }
    }


    private void fizz(IWorld p_180688_1_, BlockPos p_180688_2_) {
        p_180688_1_.levelEvent(1501, p_180688_2_, 0);
    }
}
