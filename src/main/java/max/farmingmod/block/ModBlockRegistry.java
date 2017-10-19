package max.farmingmod.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlockRegistry {
	
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static Block testCrop;
	
	public static CustomBlock testBlock;
	
	public static BlockPedastal blockPedastal;
	public static BlockLargerChest blockLargerChest;
	public static BlockGrinder blockGrinder;
	public static BlockStirlingGenerator blockStirlingGenerator;
	
	public static void initBlocks() {
		testCrop = new FarmBaseCrop("test_crop");
		testBlock = new CustomBlock("test_block");
		blockPedastal = new BlockPedastal("block_pedastal");
		blockLargerChest = new BlockLargerChest("block_larger_chest");
		blockGrinder = new BlockGrinder("block_grinder");
		blockStirlingGenerator = new BlockStirlingGenerator("block_stirling_generator");
	}
	
	@SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> e) {
        IForgeRegistry<Block> reg = e.getRegistry();
        reg.registerAll(blocks.toArray(new Block[0]));
        GameRegistry.registerTileEntity(blockPedastal.getTileEntityClass(), blockPedastal.getRegistryName().toString());
        GameRegistry.registerTileEntity(blockLargerChest.getTileEntityClass(), blockLargerChest.getRegistryName().toString());
        GameRegistry.registerTileEntity(blockGrinder.getTileEntityClass(), blockGrinder.getRegistryName().toString());
        GameRegistry.registerTileEntity(blockStirlingGenerator.getTileEntityClass(), blockStirlingGenerator.getRegistryName().toString());
    }
	
}


