package mod.vemerion.madscientist.screen;

import java.awt.Color;
import java.awt.Point;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.container.ContainerMachineContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerMachineScreen extends ContainerScreen<ContainerMachineContainer> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/machine_screen.png");
		
	private ContainerMachineContainer container;

	public ContainerMachineScreen(ContainerMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
		blit((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize); // background
		blit(guiLeft + 76, guiTop + 36, 0, 166, (int) (25 * container.getProgressTime()), 8); // arrow
		
		// Slots
		for (Point pos : container.getSlotPositions()) {
			blit(guiLeft + pos.x, guiTop + pos.y, 0, 174, 18, 18);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String text = title.getFormattedText();
		int x = xSize / 2 - font.getStringWidth(text) / 2;
		font.drawString(title.getFormattedText(), x, 12, Color.DARK_GRAY.getRGB());
		font.drawString(playerInventory.getDisplayName().getFormattedText(), 6, 70, Color.DARK_GRAY.getRGB());
	}

}
