package tombchips.avalimod.common.te;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import tombchips.avalimod.common.blocks.SmallCanisterBlock;
import tombchips.avalimod.common.contianers.SmallCanisterContainer;
import tombchips.avalimod.core.ATileEntityTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SmallCanisterTE extends LockableLootTileEntity implements IAnimatable {

    private NonNullList<ItemStack> chestContents = NonNullList.withSize(3, ItemStack.EMPTY);
    protected int numPlayersUsing;
    private final IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public static boolean isUsed;


    public SmallCanisterTE(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public SmallCanisterTE() {
        this(ATileEntityTypes.SMALL_CANISTER);
    }

    private final AnimationFactory manager = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;

        if(isUsed){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.smallcanister.open", false));
            return PlayState.CONTINUE;
        }
        else {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.smallcanister.close", false));
            return PlayState.CONTINUE;
        }

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return super.createMenu(p_createMenu_1_, p_createMenu_2_, p_createMenu_3_);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestContents = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.small_canister");
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.chestContents);
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(PlayerEntity player) {
        isUsed = true;
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void stopOpen(PlayerEntity player) {
        isUsed = false;
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof SmallCanisterBlock) {
            this.level.blockEvent(this.getBlockPos(), block, 1, this.numPlayersUsing);
            this.level.updateNeighborsAt(this.getBlockPos(), block);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new SmallCanisterContainer(id, player, this);
    }

    @Override
    public int getContainerSize() {
        return 3;
    }

    @Override
    public void clearCache() {
        super.clearCache();
        if (this.itemHandler != null) {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }


    @Override
    public void setRemoved() {
        super.setRemoved();
        if (itemHandler != null) {
            itemHandler.invalidate();
        }
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }
}
