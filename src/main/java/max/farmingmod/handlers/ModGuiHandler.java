package max.farmingmod.handlers;

import max.farmingmod.inventory.ContainerGrinder;
import max.farmingmod.inventory.ContainerLargerChest;
import max.farmingmod.inventory.ContainerPedestal;
import max.farmingmod.inventory.ContainerStirlingGenerator;
import max.farmingmod.inventory.gui.GuiGrinder;
import max.farmingmod.inventory.gui.GuiLargerChest;
import max.farmingmod.inventory.gui.GuiPedastal;
import max.farmingmod.inventory.gui.GuiStirlingGenerator;
import max.farmingmod.tile.TileEntityGrinder;
import max.farmingmod.tile.TileEntityLargerChest;
import max.farmingmod.tile.TileEntityPedastal;
import max.farmingmod.tile.TileEntityStirlingGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int PEDESTAL = 0;
	public static final int LARGER_CHEST = 1;
	public static final int GRINDER = 2;
	public static final int STIRLING_GENERATOR = 3;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case PEDESTAL:
				return new ContainerPedestal(player.inventory, (TileEntityPedastal) world.getTileEntity(new BlockPos(x, y, z)));
			case LARGER_CHEST:
				return new ContainerLargerChest(player.inventory, (TileEntityLargerChest) world.getTileEntity(new BlockPos(x, y, z)));
			case GRINDER:
				return new ContainerGrinder(player.inventory, (TileEntityGrinder) world.getTileEntity(new BlockPos(x, y, z)));  
			case STIRLING_GENERATOR:
				return new ContainerStirlingGenerator(player.inventory, (TileEntityStirlingGenerator) world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case PEDESTAL:
			return new GuiPedastal(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case LARGER_CHEST:
			return new GuiLargerChest(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case GRINDER:
			return new GuiGrinder(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityGrinder) world.getTileEntity(new BlockPos(x, y, z)));
		case STIRLING_GENERATOR:
			return new GuiStirlingGenerator(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityStirlingGenerator) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
	}
	}

}
