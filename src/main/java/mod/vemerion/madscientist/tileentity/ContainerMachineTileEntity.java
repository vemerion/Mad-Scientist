package mod.vemerion.madscientist.tileentity;

import java.util.stream.IntStream;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ContainerMachineTileEntity extends MachineTileEntity
		implements ISidedInventory, INamedContainerProvider {

	// Data params
	public static final int PROCESS_TIME_INDEX = 0;
	public static final int TOTAL_PROCESS_TIME_INDEX = 1;
	public static final int DATA_SIZE = 2;

	protected ItemStackHandler inventory;
	protected int processTime;
	protected int totalProcessTime;

	protected final IIntArray data = new IIntArray() {

		@Override
		public int get(int index) {
			return index == PROCESS_TIME_INDEX ? processTime : totalProcessTime;
		}

		@Override
		public void set(int index, int value) {
			if (index == PROCESS_TIME_INDEX)
				processTime = value;
			else if (index == TOTAL_PROCESS_TIME_INDEX)
				totalProcessTime = value;
		}

		@Override
		public int size() {
			return DATA_SIZE;
		}

	};

	public abstract int[] getInputSlots();

	public abstract int[] getOutputSlots();

	public abstract boolean isValidInput(ItemStack stack, int index);

	public ContainerMachineTileEntity(TileEntityType<? extends ContainerMachineTileEntity> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		inventory = new ItemStackHandler(getSizeInventory());
	}

	protected boolean isNearBrain() {
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				for (int z = -1; z < 2; z++) {
					if ((Math.abs(x) + Math.abs(y) + Math.abs(z) == 1)
							&& world.getTileEntity(getPos().add(x, y, z)) instanceof BrainInAVatTileEntity) {
						BrainInAVatTileEntity brain = (BrainInAVatTileEntity) world
								.getTileEntity(getPos().add(x, y, z));
						if (!brain.isBroken())
							return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.put("inventory", inventory.serializeNBT());
		compound.putInt("processTime", processTime);
		compound.putInt("totalProcessTime", totalProcessTime);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);

		inventory.deserializeNBT(compound.getCompound("inventory"));
		processTime = compound.getInt("processTime");
		totalProcessTime = compound.getInt("totalProcessTime");
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (!getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return inventory.extractItem(index, count, false);
	}

	public ItemStack incrStackSize(int index, int count) {
		ItemStack stack = removeStackFromSlot(index);
		stack.grow(count);
		return inventory.insertItem(index, stack, false);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.extractItem(index, inventory.getSlotLimit(index), false);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setStackInSlot(index, stack);
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
					(double) pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); i++) {
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return side == Direction.DOWN ? getOutputSlots() : getInputSlots();
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		return IntStream.of(getInputSlots()).anyMatch(i -> i == index) && isValidInput(itemStackIn, index);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		return true;
	}

}
