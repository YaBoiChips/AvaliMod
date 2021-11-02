package tombchips.avalimod.common.dimension.fluids;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.SetTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
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
    protected boolean canBeReplacedWith(FluidState p_76127_, BlockGetter p_76128_, BlockPos p_76129_, Fluid p_76130_, Direction p_76131_) {
        return false;
    }

    @Override
    public int getTickDelay(LevelReader p_76120_) {
        return 6;
    }

    @Override
    protected boolean canConvertToSource() {
        return true;
    }


    @Override
    protected int getSlopeFindDistance(LevelReader p_76074_) {
        return 4;
    }

    @Override
    protected int getDropOff(LevelReader p_76087_) {
        return 1;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
        BlockEntity tileentity = p_205580_3_.hasBlockEntity() ? p_205580_1_.getBlockEntity(p_205580_2_) : null;
        Block.dropResources(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
    }

    @Override
    public boolean isEntityInside(FluidState state, LevelReader world, BlockPos pos, Entity entity, double yToTest, SetTag<Fluid> tag, boolean testingHead) {
        return super.isEntityInside(state, world, pos, entity, yToTest, tag, testingHead);
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
        return ABlocks.AMMONIA_SOURCE.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
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
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> state) {
            super.createFluidStateDefinition(state);
            state.add(LEVEL);
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
