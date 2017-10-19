package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.handlers.ModGuiHandler;
import max.farmingmod.items.ModItemRegistry;
import max.farmingmod.tile.TileEntityGrinder;
import max.farmingmod.tile.TileEntityStirlingGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockStirlingGenerator extends BlockTileEntity<TileEntityStirlingGenerator> {

	public BlockStirlingGenerator(String name) {
		super(name);
	}

	@Override
	public Class<TileEntityStirlingGenerator> getTileEntityClass() {
		return TileEntityStirlingGenerator.class;
	}

	@Override
	public TileEntityStirlingGenerator createTileEntity(World world, IBlockState state) {
		return new TileEntityStirlingGenerator();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
		if(!world.isRemote) {
			
			
			player.openGui(FarmingMod.instance, ModGuiHandler.STIRLING_GENERATOR, world, pos.getX(), pos.getY(), pos.getZ());
			
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
