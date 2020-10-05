package mod.vemerion.madscientist.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.model.BrainModel;
import mod.vemerion.madscientist.model.VatModel;
import mod.vemerion.madscientist.tileentity.BrainInAVatTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class BrainInAVatTileEntityRenderer extends MachineTileEntityRenderer<BrainInAVatTileEntity> {
	private static final Model VAT_MODEL = new VatModel();
	private static final Model BRAIN_MODEL = new BrainModel();

	private static final ResourceLocation[] VAT_TEXTURES = {
			new ResourceLocation(Main.MODID, "textures/entity/vat.png"),
			new ResourceLocation(Main.MODID, "textures/entity/vat_broken1.png"),
			new ResourceLocation(Main.MODID, "textures/entity/vat_broken2.png"),
			new ResourceLocation(Main.MODID, "textures/entity/vat_broken3.png") };

	private static final ResourceLocation BRAIN_TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/brain.png");

	public BrainInAVatTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(BrainInAVatTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		applyMatrixTransformations(tileEntityIn, matrixStackIn);
		matrixStackIn.push();
		if (tileEntityIn.getBrokenStage() < 3) {
			matrixStackIn.translate(0, 0.5 + tileEntityIn.getBrainPosition(partialTicks), 0);
		} else {
			matrixStackIn.translate(0, 0.65, 0);
			matrixStackIn.rotate(new Quaternion(15, 15, 15, true));
		}

		IVertexBuilder renderBuffer = bufferIn.getBuffer(BRAIN_MODEL.getRenderType(BRAIN_TEXTURE));
		BRAIN_MODEL.render(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
		matrixStackIn.pop();

		renderBuffer = bufferIn.getBuffer(VAT_MODEL.getRenderType(getVatTexture(tileEntityIn)));
		VAT_MODEL.render(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
		revertMatrixTransformations(matrixStackIn);
	}

	private ResourceLocation getVatTexture(BrainInAVatTileEntity tileEntityIn) {
		return VAT_TEXTURES[MathHelper.clamp(tileEntityIn.getBrokenStage(), 0, VAT_TEXTURES.length - 1)];
	}

}
