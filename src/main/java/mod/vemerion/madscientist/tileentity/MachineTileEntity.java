package mod.vemerion.madscientist.tileentity;

import java.util.Random;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class MachineTileEntity extends TileEntity implements ITickableTileEntity {
	
	protected Random rand;

	public MachineTileEntity(TileEntityType<? extends MachineTileEntity> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.rand = new Random();
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		read(pkt.getNbtCompound());
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT compound = new CompoundNBT();
		write(compound);
		return compound;
	}

	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		read(tag);
	}
}
