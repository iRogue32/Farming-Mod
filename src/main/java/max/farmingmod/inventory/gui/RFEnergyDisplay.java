package max.farmingmod.inventory.gui;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import max.farmingmod.Reference;
import max.farmingmod.tile.MachineEnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class RFEnergyDisplay extends Gui {
	
	private MachineEnergyStorage rfStorage;
	int x;
	int y;
	private static final ResourceLocation RF_BAR = new ResourceLocation(Reference.MODID, "textures/gui/gui_machine_energy_storage.png");
	
	public RFEnergyDisplay(int x, int y, MachineEnergyStorage rfStorage) {
		this.x = x;
        this.y = y;
        this.rfStorage = rfStorage;
	}
	
	public void drawRFStorageGUI() {
		Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(RF_BAR);

        this.drawTexturedModalRect(this.x, this.y, 0, 0, 18, 65);

        if(this.rfStorage.getEnergyStored() > 0){
            int i = this.rfStorage.getEnergyStored()*63/this.rfStorage.getMaxEnergyStored();
            this.drawTexturedModalRect(this.x+1, this.y+64-i, 18, 1, 16, i);
        }
	}
	
	public void drawOverlay(int mouseX, int mouseY){
        if(this.isMouseOver(mouseX, mouseY)){
            Minecraft mc = Minecraft.getMinecraft();

            List<String> text = new ArrayList<String>();
            text.add(this.getOverlayText());
            GuiUtils.drawHoveringText(text, mouseX, mouseY, mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);
        }
    }

    private boolean isMouseOver(int mouseX, int mouseY){
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x+18 && mouseY < this.y+65;
    }

    private String getOverlayText(){
        NumberFormat format = NumberFormat.getInstance();
        return String.format("%s/%s Redstone Flux", format.format(this.rfStorage.getEnergyStored()), format.format(this.rfStorage.getMaxEnergyStored()));
    }

}
