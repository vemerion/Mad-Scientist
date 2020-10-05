package mod.vemerion.madscientist.container;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.tileentity.ContainerMachineTileEntity;
import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class PanaceumContainer extends ContainerMachineContainer {

	public PanaceumContainer(int id, PlayerInventory playerInventory, ContainerMachineTileEntity inventory,
			IIntArray data) {
		super(Main.PANACEUM_CONTAINER_TYPE, id, playerInventory, inventory, data);
	}
	
	// Client
	public PanaceumContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		super(Main.PANACEUM_CONTAINER_TYPE, id, playerInventory, new PanaceumTileEntity(), new IntArray(ContainerMachineTileEntity.DATA_SIZE));
	}
}
