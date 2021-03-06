package max.farmingmod.tile;

import net.minecraft.util.EnumFacing;

public interface IEnergyReceiver {
	
	/**
	 * Returns the amount of energy currently stored.
	 */
	int getEnergyStored(EnumFacing from);

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	int getMaxEnergyStored(EnumFacing from);
	
	/**
	 * Add energy to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
	 *
	 * @param from       Orientation the energy is received from.
	 * @param maxReceive Maximum amount of energy to receive.
	 * @param simulate   If TRUE, the charge will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) received.
	 */
	int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate);

}
