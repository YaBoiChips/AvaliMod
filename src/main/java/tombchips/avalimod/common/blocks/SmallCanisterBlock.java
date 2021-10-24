package tombchips.avalimod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tombchips.avalimod.core.ATileEntityTypes;

import java.util.stream.Stream;

public class SmallCanisterBlock  extends Block {

    public static boolean isUsed;

    protected static final VoxelShape SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(6, 7, 6, 10, 8, 10),
            Block.box(6.5, 1, 6.5, 9.5, 7, 9.5)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public SmallCanisterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext iSelectionContext) {
        return SHAPE;
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        isUsed = true;

        return super.use(blockState, world, blockPos, player, hand, blockRayTraceResult);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ATileEntityTypes.SMALL_CANISTER.create();
    }
    @Override
    public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
