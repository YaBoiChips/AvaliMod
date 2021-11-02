package tombchips.avalimod.common.blocks.plants;


import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;


public class SnowPlantBlock extends FlowerBlock {
    public SnowPlantBlock(MobEffect effect, int effectDuration, Properties properties) {
        super(effect, effectDuration, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.is(BlockTags.BAMBOO_PLANTABLE_ON) || state.is(Blocks.FARMLAND) || state.is(Blocks.SNOW_BLOCK);
    }
}
