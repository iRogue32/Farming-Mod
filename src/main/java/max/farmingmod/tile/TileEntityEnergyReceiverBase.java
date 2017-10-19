package max.farmingmod.tile;

import net.minecraft.util.EnumFacing;

public class TileEntityEnergyReceiverBase extends TileEntityEnergyStorageBase implements IEnergyReceiver {

	public TileEntityEnergyReceiverBase(int inputSlots, int outputSlots, int capacity, int maxReceive) {
		super(inputSlots, outputSlots, capacity, maxReceive, 0);
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return this.storage.receiveEnergy(maxReceive, simulate);
	}

}
