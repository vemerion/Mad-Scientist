package mod.vemerion.madscientist.tileentity;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.container.ChrysopoeiaContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ChrysopoeiaTileEntity extends ContainerMachineTileEntity {

	// Tags for the base metals that can be used for input
	private static final ResourceLocation[] BASE_METALS = new ResourceLocation[] {
			new ResourceLocation("forge", "ingots/iron"), new ResourceLocation("forge", "ingots/nickel"),
			new ResourceLocation("forge", "ingots/lead"), new ResourceLocation("forge", "ingots/zinc"),
			new ResourceLocation("forge", "ingots/copper"), new ResourceLocation("forge", "ingots/tin") };

	public static final int INPUT_INDEX = 0;
	public static final int OUTPUT_INDEX = 1;
	public static final int INVENTORY_SIZE = 2;
	private static final int[] INPUT_SLOTS = new int[] { INPUT_INDEX };
	private static final int[] OUTPUT_SLOTS = new int[] { OUTPUT_INDEX };

	private ItemStack prevInput = ItemStack.EMPTY;

	// Animation
	private int itemRotation, prevItemRotation;
	private int lightningTimer;
	private Vec3d lightningRotation;

	public ChrysopoeiaTileEntity() {
		super(Main.CHRYSOPOEIA_TILE_ENTITY_TYPE);
		itemRotation = rand.nextInt(360);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
		return new ChrysopoeiaContainer(id, playerInventory, this, data);
	}

	public boolean isValidInput(ItemStack stack, int index) {
		return isBaseMetal(stack.getItem());
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			syncClient();

			if (!getStackInSlot(INPUT_INDEX).isEmpty() && isNearBrain()) {
				processTime++;
				totalProcessTime = 20 * 10;

				if (processTime >= totalProcessTime) {
					transmutate();
					processTime = 0;
				}
			} else {
				processTime = 0;
			}
		} else {
			prevItemRotation = itemRotation;
			itemRotation += 2;

			if (lightningTimer-- == 0) {
				lightningRotation = new Vec3d(0, rand.nextDouble() * 0.8 - 0.4, rand.nextDouble() * 0.8 - 0.4);
			} else if (lightningTimer < -20) {
				lightningTimer = 20;
			}
		}
	}

	private void syncClient() {
		ItemStack input = getStackInSlot(INPUT_INDEX);
		if (!ItemStack.areItemStacksEqual(input, prevInput)) {
			world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
		}

		prevInput = input.copy();
	}

	private void transmutate() {
		if (canTransmutate()) {
			decrStackSize(INPUT_INDEX, 1);
			if (getStackInSlot(OUTPUT_INDEX).isEmpty())
				setInventorySlotContents(OUTPUT_INDEX, new ItemStack(Main.TRANSMUTATED_GOLD_ITEM));
			else
				incrStackSize(OUTPUT_INDEX, 1);
		}
	}

	private boolean canTransmutate() {
		ItemStack output = getStackInSlot(OUTPUT_INDEX);
		return output.getCount() < inventory.getSlotLimit(OUTPUT_INDEX)
				&& (output.isEmpty() || output.getItem() == Main.TRANSMUTATED_GOLD_ITEM);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Chrysopoeia");
	}

	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}

	public float getItemRotation(float partialTicks) {
		return MathHelper.lerp(partialTicks, prevItemRotation, itemRotation);
	}

	public Vec3d getLightningRotation() {
		return lightningRotation;
	}

	public boolean isLightningVisible() {
		return lightningTimer < 0 && !getStackInSlot(INPUT_INDEX).isEmpty() && isNearBrain();
	}

	@Override
	public int[] getInputSlots() {
		return INPUT_SLOTS;
	}

	@Override
	public int[] getOutputSlots() {
		return OUTPUT_SLOTS;
	}

	private static boolean isBaseMetal(Item item) {
		for (ResourceLocation baseMetal : BASE_METALS) {
			if (ItemTags.getCollection().getOrCreate(baseMetal).contains(item))
				return true;
		}
		return false;
	}

}
