package max.farmingmod.tile;

import max.farmingmod.util.EnergyUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityCustomBase extends TileEntity implements ITickable {

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}
	 
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	protected void sendUpdates() {
		this.getWorld().markBlockRangeForRenderUpdate(pos, pos);
		this.getWorld().notifyBlockUpdate(pos, this.getWorld().getBlockState(pos), this.getWorld().getBlockState(pos), 3);
		this.getWorld().scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return getCapability(capability, facing) != null;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			IItemHandler handler = this.getItemHandler(facing);
			if(handler != null) {
				return (T)handler;
			}
		}
		else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			IFluidHandler handler = this.getFluidHandler(facing);
			if(handler != null) {
				return (T)handler;
			}
		}
		else if(capability == CapabilityEnergy.ENERGY) {
			IEnergyStorage handler = this.getEnergyStorage(facing);
			if(handler != null) {
				return (T)handler;
			}
		}
		return super.getCapability(capability, facing);
	}
	
	public IItemHandler getItemHandler(EnumFacing facing) {
		return null;
	}
	
	public IEnergyStorage getEnergyStorage(EnumFacing facing) {
		return null;
	}
	
	public IFluidHandler getFluidHandler(EnumFacing facing) {
		 return null;
	 }

	@Override
	public void update() {
		this.updateEntity();
	}

	public void updateEntity() {
		
	}
	
}
