package max.farmingmod.inventory.gui;

import max.farmingmod.Reference;
import max.farmingmod.tile.TileEntityStirlingGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStirlingGenerator extends GuiContainer {
	
	private InventoryPlayer playerInv;
	private final TileEntityStirlingGenerator stirlingGenerator;
	private RFEnergyDisplay rfStorage;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/stirling_generator.png");
	
	public GuiStirlingGenerator(Container container, InventoryPlayer playerInv, TileEntityStirlingGenerator stirlingGenerator) {
		super(container);
		this.playerInv = playerInv;
		this.stirlingGenerator = stirlingGenerator;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		rfStorage = new RFEnergyDisplay(this.guiLeft+10, this.guiTop+10, this.stirlingGenerator.storage);
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
		
		if(this.stirlingGenerator.currentBurnTime > 0){
            int i = this.stirlingGenerator.getBurningScaled(13);
            this.drawTexturedModalRect(this.guiLeft+80, this.guiTop+28+12-i, 176, 12-i, 14, i);
        }
		
		rfStorage.drawRFStorageGUI();
	}

}
