package tombchips.avalimod.common.blocks.plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SnowPlantBlock extends FlowerBlock {
    public SnowPlantBlock(Effect effect, int effectDuration, Properties properties) {
        super(effect, effectDuration, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(BlockTags.BAMBOO_PLANTABLE_ON) || state.is(Blocks.FARMLAND) || state.is(Blocks.SNOW_BLOCK);
    }
}
