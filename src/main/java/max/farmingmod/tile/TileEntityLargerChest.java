package max.farmingmod.tile;

import max.farmingmod.tile.TileEntityInventoryConfigurableBase.EnumSideState;
import max.farmingmod.util.CombinedInvWrapperCustom;
import max.farmingmod.util.ItemStackHandlerCustom;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityLargerChest extends TileEntityInventoryConfigurableBase {

	private final int NUMBER_OF_SLOTS = 54;
	
	public TileEntityLargerChest() {
		super(54, 0);
	}
	
//	@Override
//	protected void autoOutput() {
//		for(EnumFacing facing : EnumFacing.VALUES) {
//			if(this.getSideState(facing) == EnumSideState.AUTO_OUTPUT) {
//				for(int i = 0; i < teInventoryInput.getSlots(); ++i) {
//					if(!teInventoryInput.getStackInSlot(i).isEmpty()) {
//						ItemStack initialStack = teInventoryInput.getStackInSlot(i).copy();
//						BlockPos targetPos = this.pos.offset(facing);
//						TileEntity targetEntity = this.world.getTileEntity(targetPos);
//						if(targetEntity != null && targetEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
//							IItemHandler handler = targetEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
//							int itemsToInput = 4 <= initialStack.getCount() ? 4 : initialStack.getCount();
//							initialStack.setCount(itemsToInput);
//							ItemStack itemsReceived = ItemHandlerHelper.insertItemStacked(handler, initialStack, false);
//							this.teInventoryInput.getStackInSlot(i).setCount(this.teInventoryInput.getStackInSlot(i).getCount() - (itemsToInput - itemsReceived.getCount()));
//							if(this.teInventoryInput.getStackInSlot(i).getCount() == 0) {
//								this.teInventoryInput.setStackInSlot(i, ItemStack.EMPTY);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
	
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return true;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}
	
	@Override
	public IItemHandler getItemHandler(EnumFacing facing) {
		if(this.getSideState(facing) == EnumSideState.AUTO_OUTPUT) {
			return teInventoryInput;
		}
		return teInventory;
	}
	
}
