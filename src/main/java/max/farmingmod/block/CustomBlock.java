package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.items.ModItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomBlock extends Block {

	public CustomBlock(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(FarmingMod.FarmingModTab);
		
		ModBlockRegistry.blocks.add(this);
		ModItemRegistry.items.add(new ItemBlock(this).setUnlocalizedName(this.getUnlocalizedName()).setRegistryName(this.getRegistryName()));
	}
	
}


