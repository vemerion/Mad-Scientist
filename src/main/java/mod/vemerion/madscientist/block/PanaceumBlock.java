package mod.vemerion.madscientist.block;

import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class PanaceumBlock extends ContainerMachineBlock {

	public PanaceumBlock() {
		super(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5f));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PanaceumTileEntity();
	}

}
