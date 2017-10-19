package max.farmingmod.tile;

import max.farmingmod.recipe.GrinderRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityGrinder extends TileEntityEnergyReceiverBase {

	public static final int SLOT_INPUT = 0;
	public static final int SLOT_OUTPUT_1 = 1;
	public static final int SLOT_OUTPUT_2 = 2;
	public static final int NUMBER_OF_SLOTS = 3;
	public static final int MAX_GRIND_TIME = 100;
	public static final int ENERGY_USE = 40;
	public int currentGrindTime;
	
	public TileEntityGrinder() {
		super(1, 2, 100000, 100);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!world.isRemote) {
			boolean canGrind = canGrind();
			if(canGrind) {
				if(this.storage.getEnergyStored() >= ENERGY_USE)
				currentGrindTime++;
				if(currentGrindTime >= MAX_GRIND_TIME) {
					finishGrinding();
					currentGrindTime = 0;
				}
				this.storage.ExtractEnergyFromMachine(ENERGY_USE, false);
			}
			else {
				currentGrindTime = 0;
			}
			this.sendUpdates();
		}
	}

	private boolean canGrind() {
		if(!this.teInventory.getStackInSlot(SLOT_INPUT).isEmpty()) {
			ItemStack firstOutput = GrinderRecipeRegistry.getFirstOutput(this.teInventory.getStackInSlot(SLOT_INPUT));
			ItemStack secondOutput = GrinderRecipeRegistry.getSecondOutput(this.teInventory.getStackInSlot(SLOT_INPUT));
			ItemStack inputSlot = this.teInventory.getStackInSlot(SLOT_INPUT);
			ItemStack outputOneSlot = this.teInventory.getStackInSlot(SLOT_OUTPUT_1);
			ItemStack outputTwoSlot = this.teInventory.getStackInSlot(SLOT_OUTPUT_2);
			if(!firstOutput.isEmpty()) {
				if((outputOneSlot.isEmpty() || (outputOneSlot.getItem() == firstOutput.getItem() && outputOneSlot.getCount() <= (firstOutput.getMaxStackSize() - firstOutput.getCount()))) && (outputTwoSlot.isEmpty() || (outputTwoSlot.getItem() == secondOutput.getItem() && outputTwoSlot.getCount() <= (secondOutput.getMaxStackSize() - secondOutput.getCount())))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void finishGrinding() {
		ItemStack firstOutput = GrinderRecipeRegistry.getFirstOutput(this.teInventory.getStackInSlot(SLOT_INPUT));
		ItemStack secondOutput = GrinderRecipeRegistry.getSecondOutput(this.teInventory.getStackInSlot(SLOT_INPUT));
		if(!firstOutput.isEmpty()) {
			if(this.teInventory.getStackInSlot(SLOT_OUTPUT_1).isEmpty()) {
				this.teInventory.setStackInSlot(SLOT_OUTPUT_1, firstOutput.copy());
			}
			else if(this.teInventory.getStackInSlot(SLOT_OUTPUT_1).getItem() == firstOutput.getItem()) {
				this.teInventory.getStackInSlot(SLOT_OUTPUT_1).setCount(this.teInventory.getStackInSlot(SLOT_OUTPUT_1).getCount() + firstOutput.getCount());
			}
			this.teInventory.getStackInSlot(SLOT_INPUT).setCount(this.teInventory.getStackInSlot(SLOT_INPUT).getCount() - 1);
		}
		if(!secondOutput.isEmpty()) {
			int rand = this.world.rand.nextInt(99) + 1;
			if(rand <= GrinderRecipeRegistry.getSecondOutputChance(this.teInventory.getStackInSlot(SLOT_INPUT))) {
				if(this.teInventory.getStackInSlot(SLOT_OUTPUT_2).isEmpty()) {
					this.teInventory.setStackInSlot(SLOT_OUTPUT_2, secondOutput.copy());
				}
				else if(this.teInventory.getStackInSlot(SLOT_OUTPUT_2).getItem() == secondOutput.getItem()) {
					this.teInventory.getStackInSlot(SLOT_OUTPUT_2).setCount(this.teInventory.getStackInSlot(SLOT_OUTPUT_2).getCount() + secondOutput.getCount());
				}
			}
		}
	}
	
	@Override
	protected boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == SLOT_INPUT && GrinderRecipeRegistry.getRecipeFromInput(stack) != null;
	}
	
	@Override
	protected boolean canExtractItem(int slot, ItemStack stack) {
		return slot == SLOT_OUTPUT_1 || slot == SLOT_OUTPUT_2;
	}
	
	public int getTimeToScale(int scale) {
		return this.currentGrindTime * scale / this.MAX_GRIND_TIME;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("grind_time", this.currentGrindTime);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.currentGrindTime = compound.getInteger("grind_time");
		super.readFromNBT(compound);
	}
	
	@Override
	public IEnergyStorage getEnergyStorage(EnumFacing facing) {
		return storage;
	}

}
