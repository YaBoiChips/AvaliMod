package tombchips.avalimod.common.contianers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import tombchips.avalimod.common.te.SmallCanisterTE;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.AContainers;

import javax.annotation.Nullable;
import java.util.Objects;

public class SmallCanisterContainer extends Container {

    public final SmallCanisterTE tileEntity;
    private final IWorldPosCallable canInteractWithCallable;


    public SmallCanisterContainer(final int windowId, final PlayerInventory playerInv, final SmallCanisterTE tileEntityIn) {
        super(AContainers.SMALL_CAN, windowId);
        this.tileEntity = tileEntityIn;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntityIn.getLevel(), tileEntityIn.getBlockPos());

        // Main Inventory
        int startX = 8;
        int startInvX = 62;
        int startY = 18;
        int slotSizePlus2 = 18;
        for (int row = 0; row < 1; ++row) {
            for (int column = 0; column < 3; ++column) {
                this.addSlot(new Slot(tileEntityIn, (row * 9) + column, startInvX + (column * slotSizePlus2),
                        startY + (row * slotSizePlus2)));
            }
        }

        // Main Player Inventory
        int startPlayerInvY = startY * 5 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
                        startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
        }
    }

    public SmallCanisterContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static SmallCanisterTE getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof SmallCanisterTE) {
            return (SmallCanisterTE) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(canInteractWithCallable, playerIn, ABlocks.SMALL_CANISTER);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.tileEntity.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.tileEntity.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.tileEntity.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

}
