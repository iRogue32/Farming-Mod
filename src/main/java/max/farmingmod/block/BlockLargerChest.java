package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.handlers.ModGuiHandler;
import max.farmingmod.items.ModItemRegistry;
import max.farmingmod.tile.TileEntityInventoryConfigurableBase;
import max.farmingmod.tile.TileEntityLargerChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLMessage.OpenGui;

public class BlockLargerChest extends BlockTileEntity<TileEntityLargerChest> {

	public BlockLargerChest(String name) {
		super(name);
	}

	@Override
	public Class<TileEntityLargerChest> getTileEntityClass() {
		return TileEntityLargerChest.class;
	}

	@Override
	public TileEntityLargerChest createTileEntity(World world, IBlockState state) {
		return new TileEntityLargerChest();
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			
			if(player.getHeldItemMainhand().isItemEqual(new ItemStack(ModItemRegistry.wrench))) {
				if(!player.isSneaking()) {
					((TileEntityInventoryConfigurableBase) world.getTileEntity(pos)).onClick(facing);
					return true;
				}
			}
			
			player.openGui(FarmingMod.instance, ModGuiHandler.LARGER_CHEST, world, pos.getX(), pos.getY(), pos.getZ());
			
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

}
