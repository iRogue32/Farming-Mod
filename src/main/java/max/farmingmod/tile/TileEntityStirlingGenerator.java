package max.farmingmod.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityStirlingGenerator extends TileEntityEnergyProviderBase {

	public static final int FUEL_SLOT = 0;
	public static final int NUMBER_OF_SLOTS = 1;
	public static final int ENERGY_GEN = 40;
	public int currentBurnTime;
	public int maxBurnTime;
	
	public TileEntityStirlingGenerator() {
		super(1, 0, 100000, 80);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!this.world.isRemote) {
			this.sendEnergy();
			if(currentBurnTime > 0) {
				currentBurnTime--;
				this.storage.modifyEnergyStored(ENERGY_GEN);
			}
			if(currentBurnTime <= 0 && teInventory.getStackInSlot(FUEL_SLOT) != null && TileEntityFurnace.isItemFuel(teInventory.getStackInSlot(FUEL_SLOT))) {
				this.maxBurnTime = TileEntityFurnace.getItemBurnTime(teInventory.getStackInSlot(FUEL_SLOT));
				this.currentBurnTime = this.maxBurnTime;
				this.teInventory.getStackInSlot(FUEL_SLOT).setCount(this.teInventory.getStackInSlot(FUEL_SLOT).getCount() - 1);
			}
			this.sendUpdates();
		}
	}
	
	public void sendEnergy() {
		int energyStored = this.getEnergyStored(EnumFacing.NORTH);
		for(EnumFacing facing : EnumFacing.VALUES) {
			BlockPos pos = this.pos.offset(facing);
			TileEntity targetTE = this.world.getTileEntity(pos);
			if(targetTE != null && targetTE.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
				EnumFacing opp = facing.getOpposite();
				IEnergyStorage targetStorage = targetTE.getCapability(CapabilityEnergy.ENERGY, opp);
				int energyToInsert = this.getMaxEnergyExtract(facing) <= energyStored ? this.getMaxEnergyExtract(facing) : energyStored;
				int energyReceived = targetStorage.receiveEnergy(energyToInsert, false);
				this.storage.extractEnergy(energyReceived, false);
			}
		}
	}
	
	public int getBurningScaled(int i){
        return this.currentBurnTime * i / this.maxBurnTime;
    }
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("burn_time", this.currentBurnTime);
		compound.setInteger("max_burn_time", this.maxBurnTime);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.currentBurnTime = compound.getInteger("burn_time");
		this.maxBurnTime = compound.getInteger("max_burn_time");
		super.readFromNBT(compound);
	}

	protected boolean canExtractItem(int slot, ItemStack stack) {
		return true;
	}
	
	protected boolean isItemValidForSlot(int slot, ItemStack stack) {
		return TileEntityFurnace.isItemFuel(stack);
	}
	
	@Override
	public IEnergyStorage getEnergyStorage(EnumFacing facing) {
		return this.storage;
	}
	

}
