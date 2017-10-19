package max.farmingmod.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class MachineEnergyStorage extends EnergyStorage {

	public MachineEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}
	
	public void modifyEnergyStored(int energy) {

		this.energy += energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}
	
	public int ExtractEnergyFromMachine(int amount, boolean simulate) {
		int previousMaxExtract = this.maxExtract;
		this.maxExtract = Integer.MAX_VALUE;
		int energyExtracted = this.extractEnergy(amount, simulate);
		this.maxExtract = previousMaxExtract;
		return energyExtracted;
	}
	
	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("energy", this.getEnergyStored());
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		energy = compound.getInteger("energy");
	}
	
	public int getMaxExtract() {
		return this.maxExtract;
	}

}
