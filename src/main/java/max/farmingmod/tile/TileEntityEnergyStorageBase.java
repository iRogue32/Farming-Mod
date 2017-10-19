package max.farmingmod.tile;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEnergyStorageBase extends TileEntityInventoryConfigurableBase {
	
	public MachineEnergyStorage storage;
	
	public TileEntityEnergyStorageBase(int inputSlots, int outputSlots, int capacity, int maxReceive, int maxExtract) {
        super(inputSlots, outputSlots);
		this.storage = new MachineEnergyStorage(capacity, maxReceive, maxExtract, 0);
    }
	
	public void modifyEnergyStored(int energy) {
		this.storage.modifyEnergyStored(energy);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.storage.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.storage.readFromNBT(compound);
	}
	
	public int getCurrentEnergyStored() {
		return this.storage.getEnergyStored();
	}
	
	
	
}
