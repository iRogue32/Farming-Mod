package max.farmingmod.recipe;

import net.minecraft.item.ItemStack;

public class GrinderRecipe {
	
	public ItemStack input;
	public ItemStack firstOutput;
	public ItemStack secondOutput;
	public int secondOutputChance;
	
	public GrinderRecipe(ItemStack input, ItemStack firstOutput, ItemStack secondOutput, int secondOutputChance) {
		this.input = input;
		this.firstOutput = firstOutput;
		this.secondOutput = secondOutput;
		this.secondOutputChance = secondOutputChance;
	}

}
