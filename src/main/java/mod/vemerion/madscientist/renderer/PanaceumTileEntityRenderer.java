package mod.vemerion.madscientist.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.model.PanaceumModel;
import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

public class PanaceumTileEntityRenderer extends MachineTileEntityRenderer<PanaceumTileEntity> {
	private static final PanaceumModel MODEL = new PanaceumModel();

	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/panaceum.png");

	public PanaceumTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(PanaceumTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		applyMatrixTransformations(tileEntityIn, matrixStackIn);

		IVertexBuilder renderBuffer = bufferIn.getBuffer(MODEL.getRenderType(TEXTURE));
		
		matrixStackIn.push();
		matrixStackIn.translate(0, 18 / 16f, 0);
		matrixStackIn.scale(1f, tileEntityIn.getLeftFluidHeight(partialTicks), 1);
		matrixStackIn.translate(0, -18 / 16f, 0);
		MODEL.renderLeftFluid(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn);
		matrixStackIn.pop();
		matrixStackIn.push();
		matrixStackIn.translate(0, 18 / 16f, 0);
		matrixStackIn.scale(1, tileEntityIn.getRightFluidHeight(partialTicks), 1);
		matrixStackIn.translate(0, -18 / 16f, 0);
		MODEL.renderRightFluid(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn);
		matrixStackIn.pop();
		
		MODEL.render(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
		revertMatrixTransformations(matrixStackIn);

	}

}
