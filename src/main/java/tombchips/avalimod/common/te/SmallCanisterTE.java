package tombchips.avalimod.common.te;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
import tombchips.avalimod.common.blocks.containers.SmallCanisterBlock;
import tombchips.avalimod.common.contianers.SmallCanisterContainer;
import tombchips.avalimod.core.ATileEntityTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SmallCanisterTE extends RandomizableContainerBlockEntity implements IAnimatable {

    private NonNullList<ItemStack> chestContents = NonNullList.withSize(3, ItemStack.EMPTY);
    protected int numPlayersUsing;
    private final IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);



    public SmallCanisterTE(BlockPos pos, BlockState state) {
        super(ATileEntityTypes.SMALL_CANISTER, pos, state);
    }


    private final AnimationFactory manager = new AnimationFactory(this);

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;

        if(numPlayersUsing >= 1){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.smallcanister.open", false));
        }
        else {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.smallcanister.close", false));
        }
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player) {
        return new SmallCanisterContainer(id, player, this);
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
    protected Component getDefaultName() {
        return new TranslatableComponent("container.small_canister");
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.chestContents);
        }
        return compound;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.chestContents);
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
    public void startOpen(Player player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void stopOpen(Player player) {
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
    public int getContainerSize() {
        return 3;
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
