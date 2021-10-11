package tombchips.avalimod.common.dimension.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.AFluids;
import tombchips.avalimod.core.AItems;

import java.awt.*;

public class AmmoniaFluid extends FlowingFluid {
    public static final ResourceLocation AMMONIA_STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation AMMONIA_FLOWING_TEXTURE = new ResourceLocation("block/water_flow");



    @Override
    public Fluid getFlowing() {
        return AFluids.FLOWING_AMMONIA;
    }

    @Override
    public Fluid getSource() {
        return AFluids.AMMONIA;
    }

    @Override
    public Item getBucket() {
        return AItems.AMMONIA_BUCKET;
    }

    @Override
    protected boolean canConvertToSource() {
        return true;
    }

    @Override
    protected void beforeDestroyingBlock(IWorld p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
        TileEntity tileentity = p_205580_3_.hasTileEntity() ? p_205580_1_.getBlockEntity(p_205580_2_) : null;
        Block.dropResources(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader p_185698_1_) {
        return 4;
    }

    @Override
    protected int getDropOff(IWorldReader p_204528_1_) {
        return 1;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
        return false;
    }

    @Override
    public int getTickDelay(IWorldReader p_205569_1_) {
        return 6;
    }

    @Override
    protected float getExplosionResistance() {
        return 100;
    }

    @Override
    public boolean isSame(Fluid p_207187_1_) {
        return p_207187_1_ == AFluids.AMMONIA || p_207187_1_ == AFluids.FLOWING_AMMONIA;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(AMMONIA_STILL_TEXTURE, AMMONIA_FLOWING_TEXTURE).color(new Color(195, 255, 255).getRGB()).luminosity(0).density(0).viscosity(0).build(this);
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return ABlocks.AMMONIA_SOURCE.defaultBlockState().setValue(FlowingFluidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public boolean isSource(FluidState p_207193_1_) {
        return false;
    }


    @Override
    public int getAmount(FluidState p_207192_1_) {
        return 0;
    }

    public static class Flowing extends AmmoniaFluid {
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> p_207184_1_) {
            super.createFluidStateDefinition(p_207184_1_);
            p_207184_1_.add(LEVEL);
        }

        public int getAmount(FluidState p_207192_1_) {
            return p_207192_1_.getValue(LEVEL);
        }

        public boolean isSource(FluidState p_207193_1_) {
            return false;
        }
    }

    public static class Source extends AmmoniaFluid {
        public int getAmount(FluidState p_207192_1_) {
            return 8;
        }

        public boolean isSource(FluidState p_207193_1_) {
            return true;
        }
    }
}
