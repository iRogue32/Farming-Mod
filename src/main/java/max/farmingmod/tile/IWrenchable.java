package max.farmingmod.tile;

import max.farmingmod.block.BlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IWrenchable {

	public void BreakBlock(World world, EntityPlayer player, BlockPos pos, IBlockState block, TileEntity te, ItemStack stack);
	
}
