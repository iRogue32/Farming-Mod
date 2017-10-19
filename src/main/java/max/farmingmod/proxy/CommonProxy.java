package max.farmingmod.proxy;

import max.farmingmod.FarmingMod;
import max.farmingmod.block.ModBlockRegistry;
import max.farmingmod.handlers.ModGuiHandler;
import max.farmingmod.items.ModItemRegistry;
import max.farmingmod.recipe.GrinderRecipeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		ModBlockRegistry.initBlocks();
		ModItemRegistry.initItems();
		MinecraftForge.EVENT_BUS.register(new ModBlockRegistry());
		MinecraftForge.EVENT_BUS.register(new ModItemRegistry());

	}
	
	public void Init(FMLInitializationEvent event) {
				
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		GrinderRecipeRegistry.init();
	}

}
