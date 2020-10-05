package mod.vemerion.madscientist.block;

import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ChrysopoeiaBlock extends ContainerMachineBlock {

	public ChrysopoeiaBlock() {
		super(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5f));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ChrysopoeiaTileEntity();
	}
}
