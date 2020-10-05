package mod.vemerion.madscientist.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.model.ChrysopoeiaModel;
import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

@SuppressWarnings("deprecation")
public class ChrysopoeiaTileEntityRenderer extends MachineTileEntityRenderer<ChrysopoeiaTileEntity> {
	private static final ChrysopoeiaModel CHRYSOPOEIA_MODEL = new ChrysopoeiaModel();

	private static final ResourceLocation CHRYSOPOEIA_TEXTURE = new ResourceLocation(Main.MODID,
			"textures/entity/chrysopoeia.png");

	public ChrysopoeiaTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(ChrysopoeiaTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		// Item
		matrixStackIn.push();
		matrixStackIn.translate(0.5D, 0.9D, 0.5D);
		matrixStackIn.rotate(new Quaternion(0, tileEntityIn.getItemRotation(partialTicks), 0, true));
		matrixStackIn.scale(0.3f, 0.3f, 0.3f);
		ItemStack input = tileEntityIn.getStackInSlot(ChrysopoeiaTileEntity.INPUT_INDEX);
		Minecraft.getInstance().getItemRenderer().renderItem(input, TransformType.GUI, combinedLightIn,
				combinedOverlayIn, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		
		applyMatrixTransformations(tileEntityIn, matrixStackIn);

		// Lightning
		if (tileEntityIn.isLightningVisible()) {
			CHRYSOPOEIA_MODEL.lightning.showModel = true;
			Vec3d lightningRot = tileEntityIn.getLightningRotation();
			CHRYSOPOEIA_MODEL.setRotateAngle(CHRYSOPOEIA_MODEL.lightning, (float) lightningRot.x,
					(float) lightningRot.y, (float) lightningRot.z);
		} else {
			CHRYSOPOEIA_MODEL.lightning.showModel = false;
		}

		IVertexBuilder renderBuffer = bufferIn.getBuffer(CHRYSOPOEIA_MODEL.getRenderType(CHRYSOPOEIA_TEXTURE));
		CHRYSOPOEIA_MODEL.render(matrixStackIn, renderBuffer, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
		
		revertMatrixTransformations(matrixStackIn);
	}
}
