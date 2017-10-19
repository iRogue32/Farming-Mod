package max.farmingmod.inventory;

import max.farmingmod.inventory.slots.SlotItemHandlerCustom;
import max.farmingmod.tile.TileEntityStirlingGenerator;
import max.farmingmod.util.CombinedInvWrapperCustom;
import max.farmingmod.util.ItemStackHandlerCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;

public class ContainerStirlingGenerator extends Container {

	public ContainerStirlingGenerator(InventoryPlayer playerInv, final TileEntityStirlingGenerator stirlingGenerator) {
		CombinedInvWrapperCustom inventory = (CombinedInvWrapperCustom) stirlingGenerator.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		addSlotToContainer(new SlotItemHandlerCustom(inventory, 0, 80, 45) {
			@Override
			public void onSlotChanged() {
				stirlingGenerator.markDirty();
			}
		});
		
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; ++k) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		int inventoryStart = 1;
        int inventoryEnd = inventoryStart+26;
        int hotbarStart = inventoryEnd+1;
        int hotbarEnd = hotbarStart+8;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
        	ItemStack newStack = slot.getStack();
        	ItemStack currentStack = newStack.copy();
        	if(index >= inventoryStart) {
        		if(TileEntityFurnace.isItemFuel(newStack)) {
        			if(!this.mergeItemStack(newStack, 0, inventoryStart, false)) {
        				return ItemStack.EMPTY;
        			}
        		}
        	}
        	else if(!this.mergeItemStack(newStack, inventoryStart, hotbarEnd+1, true)) {
        		return ItemStack.EMPTY;
        	}
        	if(newStack.isEmpty()) {
        		slot.putStack(ItemStack.EMPTY);
        	}
        	else {
        		slot.onSlotChanged();
        	}
        	if(newStack.getCount() == currentStack.getCount()) {
        		return ItemStack.EMPTY;
        	}
        	slot.onTake(playerIn, newStack);
        	return currentStack;
        }
        return ItemStack.EMPTY;
        
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
