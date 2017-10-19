package max.farmingmod.tile;

import net.minecraft.util.EnumFacing;

public class TileEntityEnergyProviderBase extends TileEntityEnergyStorageBase implements IEnergyProvider {

	public TileEntityEnergyProviderBase(int inputSlots, int outputSlots, int capacity, int maxExtract) {
		super(inputSlots, outputSlots, capacity, 0, maxExtract);
	}
	
	@Override
	public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
		return this.storage.extractEnergy(maxExtract, simulate);
	}
	
	@Override
	public int getEnergyStored(EnumFacing facing) {
		return this.storage.getEnergyStored();
	}
	
	@Override
	public int getMaxEnergyStored(EnumFacing facing) {
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int getMaxEnergyExtract(EnumFacing from) {
		return this.storage.getMaxExtract();
	}
	
	
	
}
