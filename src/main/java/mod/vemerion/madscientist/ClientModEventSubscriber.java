package mod.vemerion.madscientist;

import mod.vemerion.madscientist.renderer.BrainInAVatTileEntityRenderer;
import mod.vemerion.madscientist.renderer.ChrysopoeiaTileEntityRenderer;
import mod.vemerion.madscientist.renderer.PanaceumTileEntityRenderer;
import mod.vemerion.madscientist.screen.ContainerMachineScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(Main.BRAIN_IN_A_VAT_BLOCK, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(Main.CHRYSOPOEIA_BLOCK, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(Main.PANACEUM_BLOCK, RenderType.getCutout());

		ClientRegistry.bindTileEntityRenderer(Main.BRAIN_IN_A_VAT_TILE_ENTITY_TYPE, BrainInAVatTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(Main.CHRYSOPOEIA_TILE_ENTITY_TYPE, ChrysopoeiaTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(Main.PANACEUM_TILE_ENTITY_TYPE, PanaceumTileEntityRenderer::new);


		ScreenManager.registerFactory(Main.CHRYSOPOEIA_CONTAINER_TYPE, ContainerMachineScreen::new);
		ScreenManager.registerFactory(Main.PANACEUM_CONTAINER_TYPE, ContainerMachineScreen::new);

	}
}
