package mod.vemerion.madscientist.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.madscientist.block.BrainInAVatBlock;
import mod.vemerion.madscientist.block.ChrysopoeiaBlock;
import mod.vemerion.madscientist.block.PanaceumBlock;
import mod.vemerion.madscientist.tileentity.BrainInAVatTileEntity;
import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class MadScientistItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
	private BrainInAVatTileEntity brainInAVatTileEntity;
	private ChrysopoeiaTileEntity chrysopoeiaTileEntity;
	private PanaceumTileEntity panaceumTileEntity;

	@Override
	public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {

		if (itemStackIn.getItem() instanceof BlockItem) {
			Block block = ((BlockItem) itemStackIn.getItem()).getBlock();

			TileEntity tileEntity;
			if (block instanceof BrainInAVatBlock) {
				if (brainInAVatTileEntity == null)
					brainInAVatTileEntity = new BrainInAVatTileEntity();
				tileEntity = brainInAVatTileEntity;
				CompoundNBT compound = itemStackIn.getOrCreateChildTag("BlockEntityTag");
				brainInAVatTileEntity
						.setBrokenStage(compound.contains("brokenStage") ? compound.getInt("brokenStage") : 0);
			} else if (block instanceof ChrysopoeiaBlock) {
				if (chrysopoeiaTileEntity == null)
					chrysopoeiaTileEntity = new ChrysopoeiaTileEntity();
				tileEntity = chrysopoeiaTileEntity;
			} else if (block instanceof PanaceumBlock) {
				if (panaceumTileEntity == null)
					panaceumTileEntity = new PanaceumTileEntity();
				tileEntity = panaceumTileEntity;
			} else {
				return;
			}
			TileEntityRendererDispatcher.instance.renderItem(tileEntity, matrixStackIn, bufferIn, combinedLightIn,
					combinedOverlayIn);
		}
	}
}
