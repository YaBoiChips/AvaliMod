package tombchips.avalimod.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import tombchips.avalimod.core.ATileEntityTypes;

import javax.annotation.Nullable;
import java.util.Map;

public class WallTapestryBlock extends DirectionalBlock {
    public WallTapestryBlock(Properties properties) {
        super(properties);
    }
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final Map<Direction, VoxelShape> DIRECTIONS = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH,
            makeNorth(), Direction.SOUTH, makeSouth(), Direction.WEST, makeWest(), Direction.EAST, makeEast()));

    public static VoxelShape makeNorth(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 1.921875, 0.9375, 0.875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 1.921875, 0.9375, 0.1875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.09375, 1.921875, 0.875, 0.90625, 1.984375, 0.9375), IBooleanFunction.OR);

        return shape;
    }

    public static VoxelShape makeSouth(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 1.921875, 0.9375, 0.875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 1.921875, 0.9375, 0.1875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.09375, 1.921875, 0.875, 0.90625, 1.984375, 0.9375), IBooleanFunction.OR);

        return shape;
    }

    public static VoxelShape makeWest(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 1.921875, 0.9375, 0.875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 1.921875, 0.9375, 0.1875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.09375, 1.921875, 0.875, 0.90625, 1.984375, 0.9375), IBooleanFunction.OR);

        return shape;
    }

    public static VoxelShape makeEast(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.8125, 1.921875, 0.9375, 0.875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 1.921875, 0.9375, 0.1875, 1.984375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.09375, 1.921875, 0.875, 0.90625, 1.984375, 0.9375), IBooleanFunction.OR);

        return shape;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext) {

        return DIRECTIONS.get(state.getValue(FACING));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ATileEntityTypes.WALL_TAPESTRY.create();
    }
    @Override
    public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }



    protected void createBlockStateDefinition (StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockstate = this.defaultBlockState();
        IWorldReader iworldreader = p_196258_1_.getLevel();
        BlockPos blockpos = p_196258_1_.getClickedPos();
        Direction[] adirection = p_196258_1_.getNearestLookingDirections();
        for(Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }
}
