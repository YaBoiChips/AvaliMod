package tombchips.avalimod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tombchips.avalimod.AvaliMod;

import java.util.Random;

public class AmmoniaIceBlock extends Block {

    public AmmoniaIceBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public void onPlace(BlockState p_220082_1_, World world, BlockPos pos, BlockState p_220082_4_, boolean p_220082_5_) {
        if (world.dimension() != RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AvaliMod.MOD_ID, "avalon"))){
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);
            this.fizz(world, pos);
        }
    }

    private void fizz(IWorld p_180688_1_, BlockPos p_180688_2_) {
        p_180688_1_.levelEvent(1501, p_180688_2_, 0);
    }
}
