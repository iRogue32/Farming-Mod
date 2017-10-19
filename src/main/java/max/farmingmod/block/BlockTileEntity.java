package max.farmingmod.block;

import javax.annotation.Nullable;

import max.farmingmod.items.ModItemRegistry;
import max.farmingmod.tile.IWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends CustomBlock implements IWrenchable {

	public BlockTileEntity(String name) {
		super(name);
	}
	
	public abstract Class<TE> getTileEntityClass();
	
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE)world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);
	
	@Override
	public void BreakBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te,ItemStack stack) {
		this.harvestBlock(world, player, pos, state, te, stack);
		removedByPlayer(state, world, pos, player, false);
	}
}
