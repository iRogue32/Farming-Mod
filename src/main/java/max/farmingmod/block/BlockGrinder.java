package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.handlers.ModGuiHandler;
import max.farmingmod.items.ModItemRegistry;
import max.farmingmod.tile.TileEntityGrinder;
import max.farmingmod.tile.TileEntityInventoryConfigurableBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrinder extends BlockTileEntity<TileEntityGrinder> {

	public BlockGrinder(String name) {
		super(name);
	}

	@Override
	public Class<TileEntityGrinder> getTileEntityClass() {
		return TileEntityGrinder.class;
	}

	@Override
	public TileEntityGrinder createTileEntity(World world, IBlockState state) {
		return new TileEntityGrinder();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			if(player.getHeldItemMainhand().isItemEqual(new ItemStack(ModItemRegistry.wrench))) {
				if(!player.isSneaking()) {
					((TileEntityInventoryConfigurableBase) world.getTileEntity(pos)).onClick(facing);
					return true;
				}
				if(player.isSneaking()) {
					this.harvestBlock(world, player, pos, state, world.getTileEntity(pos), ItemStack.EMPTY);
					this.removedByPlayer(state, world, pos, player, false);
					return true;
				}
			}
			player.openGui(FarmingMod.instance, ModGuiHandler.GRINDER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
