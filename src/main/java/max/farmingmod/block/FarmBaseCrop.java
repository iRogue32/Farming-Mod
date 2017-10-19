package max.farmingmod.block;

import max.farmingmod.FarmingMod;
import max.farmingmod.items.ModItemRegistry;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.IPlantable;

public class FarmBaseCrop extends BlockCrops implements IGrowable, IPlantable {
	
	public Item seed;
	
	public FarmBaseCrop(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(FarmingMod.FarmingModTab);
		
		ModBlockRegistry.blocks.add(this);
		ModItemRegistry.items.add(new ItemBlock(this).setUnlocalizedName(name).setRegistryName(name));
	}
	
	@Override
	protected Item getSeed() {
		return ModItemRegistry.seeds_lettuce;
	}
	
	@Override
	protected Item getCrop() {
		return ModItemRegistry.lettuce;
	}

}
