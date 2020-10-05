package mod.vemerion.madscientist.container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import mod.vemerion.madscientist.tileentity.ContainerMachineTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.MathHelper;

public abstract class ContainerMachineContainer extends Container {
	private static final int INPUT_SLOT_X = 56;
	private static final int OUTPUT_SLOT_X = 104;
	private static final int HEIGHT = 82;
	
	private ContainerMachineTileEntity inventory;
	private IIntArray data;
	private List<Point> slotPositions;

	public ContainerMachineContainer(ContainerType<ContainerMachineContainer> containerType, int id, PlayerInventory playerInventory, ContainerMachineTileEntity inventory,
			IIntArray data) {
		super(containerType, id);
		this.inventory = inventory;
		this.data = data;
		this.slotPositions = new ArrayList<>();

		trackIntArray(data);

		// Input slots
		int[] inputSlots = inventory.getInputSlots();
		for (int i = 0; i < inputSlots.length; i++) {
			int y = HEIGHT / 2 - inputSlots.length * 18 / 2 + 18 * i;
			addSlot(new InputSlot(inventory, inputSlots[i], INPUT_SLOT_X, y));
			slotPositions.add(new Point(INPUT_SLOT_X - 1, y - 1));
		}

		// Output slot
		int[] outputSlots = inventory.getOutputSlots();
		for (int i = 0; i < outputSlots.length; i++) {
			int y = HEIGHT / 2 - outputSlots.length * 18 / 2 + 18 * i;
			addSlot(new OutputSlot(inventory, outputSlots[i], OUTPUT_SLOT_X, y));
			slotPositions.add(new Point(OUTPUT_SLOT_X - 1, y - 1));
		}

		// Player inventory
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		// Player hotbar
		for (int x = 0; x < 9; ++x) {
			this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 142));
		}
	}
	
	public List<Point> getSlotPositions() {
		return slotPositions;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return inventory.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		int machineEnd = inventory.getSizeInventory();
		int playerInvStart = machineEnd;
		int playerInvEnd = playerInvStart + 3 * 9;
		int hotbarStart = playerInvEnd;
		int hotbarEnd = hotbarStart + 9;

		ItemStack copy = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			copy = stack.copy();

			if (index < machineEnd) { // From machine to player
				if (!mergeItemStack(stack, playerInvStart, hotbarEnd, false))
					return ItemStack.EMPTY;
			} else if (index >= hotbarStart && index < hotbarEnd) { // Hotbar
				if (!mergeItemStack(stack, 0, inventory.getInputSlots().length, false))
					if (!mergeItemStack(stack, playerInvStart, playerInvEnd, false))
						return ItemStack.EMPTY;
			} else if (index >= playerInvStart && index < playerInvEnd) { // Player Inventory
				if (!mergeItemStack(stack, 0, inventory.getInputSlots().length, false))
					if (!mergeItemStack(stack, hotbarStart, hotbarEnd, false))
						return ItemStack.EMPTY;
			}

			if (stack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (stack.getCount() == copy.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, stack);
		}

		return copy;
	}

	public double getProgressTime() {
		return MathHelper.clampedLerp(0, 1, (double) data.get(0) / data.get(1));
	}

	private static class InputSlot extends Slot {
		private ContainerMachineTileEntity tileEntity;
		private int index;

		public InputSlot(ContainerMachineTileEntity inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
			this.tileEntity = inventoryIn;
			this.index = index;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return tileEntity.isValidInput(stack, index);
		}

	}

	private static class OutputSlot extends Slot {

		public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}

	}

}
