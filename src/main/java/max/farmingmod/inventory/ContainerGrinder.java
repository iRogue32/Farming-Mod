package max.farmingmod.inventory;

import max.farmingmod.inventory.slots.SlotItemHandlerCustom;
import max.farmingmod.tile.TileEntityGrinder;
import max.farmingmod.tile.TileEntityPedastal;
import max.farmingmod.util.CombinedInvWrapperCustom;
import max.farmingmod.util.ItemStackHandlerCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class ContainerGrinder extends Container {

	public ContainerGrinder(InventoryPlayer playerInv, final TileEntityGrinder grinder) {
		CombinedInvWrapperCustom inventory = (CombinedInvWrapperCustom) grinder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandlerCustom(inventory, 0, 42, 35) {
			@Override
			public void onSlotChanged() {
				grinder.markDirty();
			}
		});
		
		addSlotToContainer(new SlotItemHandlerCustom(inventory, 1, 97, 35) {
			@Override
			public void onSlotChanged() {
				grinder.markDirty();
			}
		});
		
		addSlotToContainer(new SlotItemHandlerCustom(inventory, 2, 118, 36) {
			@Override
			public void onSlotChanged() {
				grinder.markDirty();
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
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			int containerSlots  = inventorySlots.size() - playerIn.inventory.mainInventory.size();
			
			if(index < containerSlots) {
				if(!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, TileEntityGrinder.SLOT_OUTPUT_1, false)) {
				return ItemStack.EMPTY;
			}
			if(itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
			if(itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

}

