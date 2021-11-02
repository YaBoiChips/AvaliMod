package tombchips.avalimod.common.blocks;



import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class CeilingLightBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(3, 14, 3, 13, 16, 13);
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;


    public CeilingLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }



    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter iBlockReader, BlockPos blockPos, CollisionContext iSelectionContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult blockRayTraceResult) {
        BlockState blockState1 = blockState.cycle(LIT);
        world.setBlock(blockPos, blockState1, 2);
        world.playSound(null, blockPos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 9999f, 0.5f);
        return InteractionResult.SUCCESS;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LIT);
    }
}
