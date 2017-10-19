package max.farmingmod;

import max.farmingmod.block.ModBlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FarmingModTab extends CreativeTabs {

	public FarmingModTab(String label) {
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlockRegistry.testCrop);
	}

}
