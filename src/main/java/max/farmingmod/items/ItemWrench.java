package max.farmingmod.items;

import max.farmingmod.FarmingMod;
import max.farmingmod.block.BlockTileEntity;
import max.farmingmod.tile.IWrenchable;
import max.farmingmod.tile.TileEntityInventoryConfigurableBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends Item {

	public ItemWrench(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(FarmingMod.FarmingModTab);
		
		ModItemRegistry.items.add(this);
	}
}
