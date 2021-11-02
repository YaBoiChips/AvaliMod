package tombchips.avalimod.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import tombchips.avalimod.AvaliMod;

import java.util.function.Supplier;

public class AmmoniaFluidBlock extends LiquidBlock {

    public AmmoniaFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);

    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state2, boolean someBool) {
        super.onPlace(state, world, pos, state2, someBool);
        if (world.dimension() != ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))){
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
            this.fizz(world, pos);
        }
    }


    private void fizz(LevelAccessor p_180688_1_, BlockPos p_180688_2_) {
        p_180688_1_.levelEvent(1501, p_180688_2_, 0);
    }
}
