package max.farmingmod.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface IEnergyProvider {
	
	/**
	 * Returns the amount of energy currently stored.
	 */
	int getEnergyStored(EnumFacing from);

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	int getMaxEnergyStored(EnumFacing from);
	
	/**
	 * Remove energy from an IEnergyProvider, internal distribution is left entirely to the IEnergyProvider.
	 *
	 * @param from       Orientation the energy is extracted from.
	 * @param maxExtract Maximum amount of energy to extract.
	 * @param simulate   If TRUE, the extraction will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) extracted.
	 */
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate);
	
	public int getMaxEnergyExtract(EnumFacing from);

}
