package max.farmingmod.items;

import java.util.ArrayList;
import java.util.List;

import max.farmingmod.block.ModBlockRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItemRegistry {

	public static List<Item> items = new ArrayList<Item>();
	
	public static ItemSeedFood lettuce;
	
	public static ItemSeeds seeds_lettuce;
	
	public static Item wrench;
	
	public static void initItems() {
		lettuce = new ItemSeedFoodCustomBase("lettuce", 0, 0, ModBlockRegistry.testCrop, Blocks.FARMLAND);
		seeds_lettuce = new ItemSeedCustomBase("seed_lettuce", ModBlockRegistry.testCrop,Blocks.FARMLAND);
		wrench = new ItemWrench("item_wrench");
	}
	
	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e) {
		IForgeRegistry<Item> reg = e.getRegistry();
		reg.registerAll(items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		for (Item item : items) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	
}
