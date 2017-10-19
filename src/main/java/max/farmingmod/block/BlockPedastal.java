package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.handlers.ModGuiHandler;
import max.farmingmod.tile.TileEntityPedastal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockPedastal extends BlockTileEntity<TileEntityPedastal> {

	public BlockPedastal(String name) {
		super(name);
	}

	@Override
	public Class<TileEntityPedastal> getTileEntityClass() {
		return TileEntityPedastal.class;
	}

	@Override
	public TileEntityPedastal createTileEntity(World world, IBlockState state) {
		return new TileEntityPedastal();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			
			player.openGui(FarmingMod.instance, ModGuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());
			
//			TileEntityPedastal tile = getTileEntity(world, pos);
//			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
//			if(!player.isSneaking()) {
//				if(player.getHeldItem(hand).isEmpty()) {
//					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
//				}
//				else {
//					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
//				}
//				tile.markDirty();
//			}
//			else {
//				player.openGui(FarmingMod.class, ModGuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());
//			}
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityPedastal tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if(!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}

}
