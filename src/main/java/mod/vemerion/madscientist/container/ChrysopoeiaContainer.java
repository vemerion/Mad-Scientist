package mod.vemerion.madscientist.container;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import mod.vemerion.madscientist.tileentity.ContainerMachineTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class ChrysopoeiaContainer extends ContainerMachineContainer {

	public ChrysopoeiaContainer(int id, PlayerInventory playerInventory, ContainerMachineTileEntity inventory,
			IIntArray data) {
		super(Main.CHRYSOPOEIA_CONTAINER_TYPE, id, playerInventory, inventory, data);
	}
	
	// Client
	public ChrysopoeiaContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		super(Main.CHRYSOPOEIA_CONTAINER_TYPE, id, playerInventory, new ChrysopoeiaTileEntity(), new IntArray(ChrysopoeiaTileEntity.DATA_SIZE));
	}
}
