package tombchips.avalimod.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import tombchips.avalimod.AvaliMod;

import java.util.Random;
import java.util.function.Supplier;

public class AmmoniaFluidBlock extends FlowingFluidBlock {
    public AmmoniaFluidBlock(FlowingFluid p_i49014_1_, Properties p_i49014_2_) {
        super(p_i49014_1_, p_i49014_2_);
    }

    public AmmoniaFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random rand) {
            if (serverWorld.dimension() != RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))) {
                serverWorld.removeBlock(pos,true);
                this.fizz(serverWorld, pos);
            }
            else super.randomTick(state, serverWorld, pos, rand);
    }

    private void fizz(IWorld p_180688_1_, BlockPos p_180688_2_) {
        p_180688_1_.levelEvent(1501, p_180688_2_, 0);
    }

}
