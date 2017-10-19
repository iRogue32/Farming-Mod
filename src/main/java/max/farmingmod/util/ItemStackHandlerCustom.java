package max.farmingmod.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerCustom extends ItemStackHandler {
	
	private boolean ignoreSlotConditions;
	
	public ItemStackHandlerCustom(int slots) {
		super(slots);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (stack.isEmpty())
            return ItemStack.EMPTY;

        this.validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;
        
        if (!this.canInsert(stack, slot)) {
        	return stack;
        }

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
		
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());
        
        if(!ignoreSlotConditions && !this.canExtract(this.getStackInSlot(slot), slot)) {
        	return ItemStack.EMPTY;
        }

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
            }
            return existing;
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
	}
	
	public ItemStack extractItemInternal(int slot, int amount, boolean simulate) {
        this.ignoreSlotConditions = true;
        ItemStack result = this.extractItem(slot, amount, simulate);
        this.ignoreSlotConditions = false;
        return result;
    }
	
	public ItemStack insertItemInternal(int slot, int amount, boolean simulate) {
		return null;
	}

	public boolean canInsert(ItemStack stack, int slot) {
		return true;
	}
	
	public boolean canExtract(ItemStack stack, int slot) {
		return true;
	}

}
