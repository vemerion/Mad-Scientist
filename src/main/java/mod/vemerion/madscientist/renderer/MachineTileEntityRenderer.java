package mod.vemerion.madscientist.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.madscientist.block.ContainerMachineBlock;
import mod.vemerion.madscientist.tileentity.MachineTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;

public abstract class MachineTileEntityRenderer<T extends MachineTileEntity> extends TileEntityRenderer<T> {

	public MachineTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
	}
	
	protected void applyMatrixTransformations(T tileEntityIn, MatrixStack matrixStackIn) {
		float rotation = Direction.SOUTH.getHorizontalAngle();
		if (tileEntityIn.hasWorld()) {
			BlockState blockstate = tileEntityIn.getBlockState();
			if (blockstate.has(ContainerMachineBlock.FACING))
				rotation = blockstate.get(ContainerMachineBlock.FACING).getHorizontalAngle();
		}

		matrixStackIn.push();
		matrixStackIn.translate(0.5D, 0.5D, 0.5D);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-rotation + 180));
		matrixStackIn.translate(0, -0.5D, 0);
		
		matrixStackIn.scale(-1, -1, 1);
		matrixStackIn.translate(0.0D, (double) -1.501F, 0.0D);
	}
	
	protected void revertMatrixTransformations(MatrixStack matrixStackIn) {
		matrixStackIn.pop();
	}
}
