package tombchips.avalimod.common.blocks.containers;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import tombchips.avalimod.common.te.SmallCanisterTE;
import tombchips.avalimod.core.ATileEntityTypes;

import java.util.stream.Stream;

public class SmallCanisterBlock extends BaseEntityBlock {

    protected static final VoxelShape SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(6, 7, 6, 10, 8, 10),
            Block.box(6.5, 1, 6.5, 9.5, 7, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SmallCanisterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter iBlockReader, BlockPos blockPos, CollisionContext iSelectionContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockRayTraceResult) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else {
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof SmallCanisterTE) {
                NetworkHooks.openGui((ServerPlayer) player, (SmallCanisterTE) tile, pos);
                player.awardStat(this.getOpenChestStat());
            }
        }
        return InteractionResult.CONSUME;
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (te instanceof SmallCanisterTE) {
                Containers.dropContents(worldIn, pos, ((SmallCanisterTE) te).getItems());
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ATileEntityTypes.SMALL_CANISTER.create(pos, state);
    }
    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }
}
