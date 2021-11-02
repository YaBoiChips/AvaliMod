package tombchips.avalimod.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import tombchips.avalimod.AvaliMod;

public class AmmoniaIceBlock extends Block {

    public AmmoniaIceBlock(BlockBehaviour.Properties p_i48440_1_) {
        super(p_i48440_1_);
    }


    @Override
    public void onPlace(BlockState p_220082_1_, Level world, BlockPos pos, BlockState p_220082_4_, boolean p_220082_5_) {
        if (world.dimension() != ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))){
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
            this.fizz(world, pos);
        }
    }

    private void fizz(LevelAccessor p_180688_1_, BlockPos p_180688_2_) {
        p_180688_1_.levelEvent(1501, p_180688_2_, 0);
    }
}