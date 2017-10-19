package max.farmingmod.inventory.slots;

import max.farmingmod.util.CombinedInvWrapperCustom;
import max.farmingmod.util.ItemStackHandlerCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class SlotItemHandlerCustom extends SlotItemHandler {
	
	CombinedInvWrapperCustom handler;

	public SlotItemHandlerCustom(CombinedInvWrapperCustom itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.handler = itemHandler;
	}
	
	@Override
    public boolean canTakeStack(EntityPlayer playerIn){
        return !this.handler.extractItemInternal(this.getSlotIndex(), 1, true).isEmpty();
    }
	
	@Override
    public ItemStack decrStackSize(int amount){
        return this.handler.extractItemInternal(this.getSlotIndex(), amount, false);
    }

}
