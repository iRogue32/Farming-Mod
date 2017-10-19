package max.farmingmod.inventory.gui;

import max.farmingmod.Reference;
import max.farmingmod.block.ModBlockRegistry;
import max.farmingmod.tile.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGrinder extends GuiContainer {

	private InventoryPlayer playerInv;
	private final TileEntityGrinder grinder;
	private RFEnergyDisplay rfStorage;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/grinder2.0.png");
	
	public GuiGrinder(Container container, InventoryPlayer playerInv, TileEntityGrinder grinder) {
		super(container);
		this.playerInv = playerInv;
		this.grinder = grinder;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		rfStorage = new RFEnergyDisplay(this.guiLeft+10, this.guiTop+10, this.grinder.storage);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.rfStorage.drawOverlay(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		int time = this.grinder.currentGrindTime;
		if(this.grinder.currentGrindTime > 0) {
			int i = this.grinder.getTimeToScale(24);
			drawTexturedModalRect(this.guiLeft + 66, this.guiTop + 35 , 177, 14, i, 16);
		}
		rfStorage.drawRFStorageGUI();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//		String name = I18n.format(Integer.toString(this.grinder.rfStorage.getEnergyStored()));
//		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
//		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

}
