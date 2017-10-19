package max.farmingmod.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class CombinedInvWrapperCustom extends CombinedInvWrapper {
	
	public boolean ignoreSlotConditions = false;
	
	public CombinedInvWrapperCustom(ItemStackHandlerCustom... handler) {
		super(handler);
	}

	public ItemStack extractItemInternal(int slot, int amount, boolean simulate) {
        this.ignoreSlotConditions = true;
        ItemStack result = this.extractItem(slot, amount, simulate);
        this.ignoreSlotConditions = false;
        return result;
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		int index = getIndexForSlot(slot);
        ItemStackHandlerCustom handler = (ItemStackHandlerCustom) getHandlerFromIndex(index);
        slot = getSlotFromIndex(slot, index);
        return ignoreSlotConditions ? handler.extractItemInternal(slot, amount, simulate) : handler.extractItem(slot, amount, simulate);
	}

}
