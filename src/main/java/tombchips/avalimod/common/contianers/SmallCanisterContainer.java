package tombchips.avalimod.common.contianers;


import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import tombchips.avalimod.common.te.SmallCanisterTE;
import tombchips.avalimod.core.ABlocks;
import tombchips.avalimod.core.AContainers;

import java.util.Objects;

public class SmallCanisterContainer extends AbstractContainerMenu {

    private final Container container;

    public SmallCanisterContainer(int windowId, Inventory playerInv) {
        this(windowId, playerInv, new SimpleContainer(3));
    }

    public SmallCanisterContainer(int slot, Inventory playerInv, Container container) {
        super(AContainers.SMALL_CAN, slot);
        checkContainerSize(container, 3);
        this.container = container;
        container.startOpen(playerInv.player);

        // Main Inventory
        int startX = 8;
        int startInvX = 62;
        int startY = 18;
        int slotSizePlus2 = 18;
        for (int row = 0; row < 1; ++row) {
            for (int column = 0; column < 3; ++column) {
                this.addSlot(new Slot(container, (row * 9) + column, startInvX + (column * slotSizePlus2),
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




    @Override
    public boolean stillValid(Player playerIn) {
        return this.container.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false)) {
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
