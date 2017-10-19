package max.farmingmod.items;

import max.farmingmod.FarmingMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;

public class ItemSeedCustomBase extends ItemSeeds {

	public ItemSeedCustomBase(String name, Block crops, Block soil) {
		super(crops, soil);
		
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(FarmingMod.FarmingModTab);
		
		ModItemRegistry.items.add(this);
	}

}
