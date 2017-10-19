package max.farmingmod.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GrinderRecipeRegistry {
	
	public static final List<GrinderRecipe> CRUSHER_RECIPES = new ArrayList<GrinderRecipe>();
	
	public static void init() {
		addGrinderRecipes(OreDictionary.getOres("oreRedstone", false), new ItemStack(Items.REDSTONE), 10, ItemStack.EMPTY, 0, 0);
        addGrinderRecipes(OreDictionary.getOres("oreLapis", false), new ItemStack(Items.DYE, 1, 4), 12, ItemStack.EMPTY, 0, 0);
//        addCrusherRecipes(OreDictionary.getOres("coal", false), OreDictionary.getOres("dustCoal", false), 1, ItemStack.EMPTY, 0, 0);
        addGrinderRecipes(OreDictionary.getOres("oreCoal", false), new ItemStack(Items.COAL), 3, ItemStack.EMPTY, 0, 0);
        addGrinderRecipes(OreDictionary.getOres("blockCoal", false), new ItemStack(Items.COAL), 9, ItemStack.EMPTY, 0, 0);
        addGrinderRecipes(OreDictionary.getOres("oreQuartz", false), new ItemStack(Items.QUARTZ), 3, ItemStack.EMPTY, 0, 0);
        addGrinderRecipes(OreDictionary.getOres("cobblestone", false), new ItemStack(Blocks.GRAVEL), 1, new ItemStack(Blocks.SAND), 1, 10);
        addGrinderRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND), new ItemStack(Items.FLINT), 50);
//        addCrusherRecipes(OreDictionary.getOres("stone", false), OreDictionary.getOres("cobblestone", false), 1, ItemStack.EMPTY, 0, 0);
		
	}
	
	/**
     * Adds a Recipe to the Crusher Recipe Registry
     *
     * @param input           The input as an ItemStack
     * @param outputOne       The first output as an ItemStack
     * @param outputTwo       The second output as an ItemStack (can be null if there should be none)
     * @param outputTwoChance The chance of the second output (0 won't occur at all, 100 will all the time)
     */
    public static void addGrinderRecipe(ItemStack input, ItemStack outputOne, ItemStack outputTwo, int secondOutputChance){
        CRUSHER_RECIPES.add(new GrinderRecipe(input, outputOne, outputTwo.isEmpty() ? ItemStack.EMPTY : outputTwo, secondOutputChance <= 100 ? secondOutputChance : 100));
    }
    
    public static void addGrinderRecipes(List<ItemStack> inputs, ItemStack outputOne, int outputOneAmount, ItemStack outputTwo, int outputTwoAmount, int outputTwoChance){
        for(ItemStack input : inputs){
            if(!input.isEmpty() && GrinderRecipeRegistry.getRecipeFromInput(input) == null){
            	outputOne.setCount(outputOneAmount);
            	outputTwo.setCount(outputTwoAmount);
            	addGrinderRecipe(input, outputOne, outputTwo, outputTwoChance);
            }
        }
    }
    
    public static GrinderRecipe getRecipeFromInput(ItemStack input){
        for(GrinderRecipe recipe : CRUSHER_RECIPES){
            if(input.getItem() == recipe.input.getItem()){
                return recipe;
            }
        }
        return null;
    }

	public static ItemStack getFirstOutput(ItemStack input) {
		for(GrinderRecipe recipe : CRUSHER_RECIPES) {
			if(input.isItemEqual(recipe.input)) {
				return recipe.firstOutput;
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack getSecondOutput(ItemStack input) {
		for(GrinderRecipe recipe : CRUSHER_RECIPES) {
			if(input.isItemEqual(recipe.input)) {
				return recipe.secondOutput;
			}
		}
		return ItemStack.EMPTY;
	}

	public static int getSecondOutputChance(ItemStack input) {
		for(GrinderRecipe recipe : CRUSHER_RECIPES) {
			if(input.isItemEqual(recipe.input)) {
				return recipe.secondOutputChance;
			}
		}
		return 0;
	}

}
