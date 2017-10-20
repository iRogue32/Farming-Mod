package max.farmingmod.tile;

import java.util.HashMap;
import max.farmingmod.util.CombinedInvWrapperCustom;
import max.farmingmod.util.ItemStackHandlerCustom;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileEntityInventoryConfigurableBase extends TileEntityCustomBase {
	
	public ItemStackHandlerCustom teInventoryInput;
	public ItemStackHandlerCustom teInventoryOutput;
	public CombinedInvWrapperCustom teInventory;
	protected HashMap<EnumFacing, EnumSideState> sideStateMap = new HashMap<>(6);
	public enum EnumSideState {
		DEFAULT,
		AUTO_OUTPUT,
		AUTO_INPUT
	};
	
	public TileEntityInventoryConfigurableBase(int inputSlots, int outputSlots) {
		super();
		teInventoryInput = new ItemStackHandlerCustom(inputSlots) {
			public boolean canInsert(ItemStack stack, int slot) {
				return TileEntityInventoryConfigurableBase.this.isItemValidForSlot(slot, stack);
			}
			public boolean canExtract(ItemStack stack, int slot) {
				return TileEntityInventoryConfigurableBase.this.canExtractItem(slot, stack);
			}
		};
		teInventoryOutput = new ItemStackHandlerCustom(outputSlots) {
			public boolean canInsert(ItemStack stack, int slot) {
				return false;
			}
		};
		teInventory = new CombinedInvWrapperCustom(teInventoryInput, teInventoryOutput);
		for(EnumFacing facing : EnumFacing.VALUES) {
			sideStateMap.put(facing, EnumSideState.DEFAULT);
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!this.world.isRemote) {
			this.autoOutput();
			this.autoInput();
		}
	}
	
	protected void autoOutput() {
		for(EnumFacing facing : EnumFacing.VALUES) {
			if(this.getSideState(facing) == EnumSideState.AUTO_OUTPUT) {
				ItemStackHandlerCustom inventory = (ItemStackHandlerCustom) this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
				for(int i = 0; i < inventory.getSlots(); ++i) {
					if(!inventory.getStackInSlot(i).isEmpty()) {
						ItemStack initialStack = inventory.getStackInSlot(i).copy();
						BlockPos targetPos = this.pos.offset(facing);
						TileEntity targetEntity = this.world.getTileEntity(targetPos);
						if(targetEntity != null && targetEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
							IItemHandler handler = targetEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
							int itemsToInput = 4 <= initialStack.getCount() ? 4 : initialStack.getCount();
							initialStack.setCount(itemsToInput);
							ItemStack itemsReceived = ItemHandlerHelper.insertItemStacked(handler, initialStack, false);
							inventory.getStackInSlot(i).setCount(inventory.getStackInSlot(i).getCount() - (itemsToInput - itemsReceived.getCount()));
							if(inventory.getStackInSlot(i).getCount() == 0) {
								inventory.setStackInSlot(i, ItemStack.EMPTY);
							}
						}
					}
				}
			}
		}
	}
	
	protected void autoInput() {
		for(EnumFacing facing : EnumFacing.VALUES) {
			if(this.getSideState(facing) == EnumSideState.AUTO_INPUT) {
				ItemStackHandlerCustom inventory = (ItemStackHandlerCustom) this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
				TileEntity targetEntity = this.world.getTileEntity(this.pos.offset(facing));
				if(targetEntity != null && targetEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
					IItemHandler targetInventory = targetEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
					for(int i = 0; i < targetInventory.getSlots(); ++i) {
						ItemStack initialStack = targetInventory.getStackInSlot(i).copy();
						int itemsToInput = 4 <= initialStack.getCount() ? 4 : initialStack.getCount();
						initialStack.setCount(itemsToInput);
						ItemStack itemsReceived = ItemHandlerHelper.insertItemStacked(inventory, initialStack, false);
						targetInventory.getStackInSlot(i).setCount(targetInventory.getStackInSlot(i).getCount() - (itemsToInput - itemsReceived.getCount()));
					}
					
				}
			}
		}
	}

	protected boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	protected boolean canExtractItem(int slot, ItemStack stack) {
		return true;
	}
	
	public EnumSideState getSideState(EnumFacing facing) {
		if(sideStateMap.containsKey(facing)) {
			return sideStateMap.get(facing);
		}
		return EnumSideState.DEFAULT;
	}
	
	public void changeSideState(EnumFacing facing) {
		if(this.sideStateMap.get(facing) == EnumSideState.DEFAULT) {
			this.sideStateMap.put(facing, EnumSideState.AUTO_INPUT);
		}
		else if(this.sideStateMap.get(facing) == EnumSideState.AUTO_INPUT) {
			this.sideStateMap.put(facing, EnumSideState.AUTO_OUTPUT);
		}
		else if(this.sideStateMap.get(facing) == EnumSideState.AUTO_OUTPUT) {
			this.sideStateMap.put(facing, EnumSideState.DEFAULT);
		}
		System.out.println("Changed "+facing.toString()+" to "+this.sideStateMap.get(facing).toString());
	}
	
	public void onClick(EnumFacing facing) {
		this.changeSideState(facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory_input", teInventoryInput.serializeNBT());
		compound.setTag("inventory_output", teInventoryOutput.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.teInventoryInput.deserializeNBT(compound.getCompoundTag("inventory_input"));
		this.teInventoryOutput.deserializeNBT(compound.getCompoundTag("inventory_output"));
		super.readFromNBT(compound);
	}
	
	@Override
	public IItemHandler getItemHandler(EnumFacing facing) {
		if(this.getSideState(facing) == EnumSideState.AUTO_OUTPUT) {
			return teInventoryOutput;
		}
		else if(this.getSideState(facing) == EnumSideState.AUTO_INPUT) {
			return teInventoryInput;
		}
		return teInventory;
	}

}
