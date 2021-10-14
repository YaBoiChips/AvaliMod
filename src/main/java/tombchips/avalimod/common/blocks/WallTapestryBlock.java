package tombchips.avalimod.common.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import tombchips.avalimod.core.ATileEntityTypes;

import javax.annotation.Nullable;

public class WallTapestryBlock extends DirectionalBlock {
    public WallTapestryBlock(Properties properties) {
        super(properties);
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
}
