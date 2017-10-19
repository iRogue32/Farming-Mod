package max.farmingmod.items;

import max.farmingmod.FarmingMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeedFood;

public class ItemSeedFoodCustomBase extends ItemSeedFood {

	public ItemSeedFoodCustomBase(String name, int healAmount, float saturation, Block crops, Block soil) {
		super(healAmount, saturation, crops, soil);
		
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(FarmingMod.FarmingModTab);
		
		ModItemRegistry.items.add(this);
	}

}
