package mod.vemerion.madscientist.tileentity;

import com.google.common.collect.ImmutableSet;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.container.PanaceumContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class PanaceumTileEntity extends ContainerMachineTileEntity {

	public static final int INVENTORY_SIZE = 4;
	public static final int GOLD_INPUT_INDEX = 0;
	public static final int POTION_INPUT_INDEX = 1;
	public static final int PANACEA_OUTPUT_INDEX = 2;
	public static final int POTION_OUTPUT_INDEX = 3;
	private static final int[] INPUT_SLOTS = { 0, 1 };
	private static final int[] OUTPUT_SLOTS = { 2, 3 };

	private static final ImmutableSet<Potion> BLACKLISTED_POTIONS = ImmutableSet.of(Potions.EMPTY, Potions.WATER,
			Potions.AWKWARD, Potions.MUNDANE);
	
	private boolean canWork, prevCanWork;

	private float fluidHeight;

	public PanaceumTileEntity() {
		super(Main.PANACEUM_TILE_ENTITY_TYPE);
	}

	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Panaceum");
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
		return new PanaceumContainer(id, playerInventory, this, data);
	}

	@Override
	public void tick() {
		if (!world.isRemote) {			
			if (canWork() && isNearBrain()) {
				canWork = true;
				processTime++;
				totalProcessTime = 20 * 10;

				if (processTime >= totalProcessTime) {
					if (brew())
						processTime = 0;
					else
						processTime = totalProcessTime;
				}
			} else {
				processTime = 0;
				canWork = false;
			}
		} else {
			fluidHeight++;
		}
		
		if (canWork != prevCanWork)
			world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
		
		prevCanWork = canWork;
	}

	private boolean brew() {
		ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER);
		ItemStack panacea = new ItemStack(Main.ELIXIR_OF_LIFE_ITEM);
		if (canBrew(potion, panacea)) {
			decrStackSize(GOLD_INPUT_INDEX, 1);
			decrStackSize(POTION_INPUT_INDEX, 1);
			if (rand.nextDouble() < 0.1) {
				inventory.insertItem(PANACEA_OUTPUT_INDEX, panacea, false);
			} else {
				inventory.insertItem(POTION_OUTPUT_INDEX, potion, false);
			}
			return true;
		}

		return false;
	}

	private boolean canBrew(ItemStack potion, ItemStack panacea) {
		return inventory.insertItem(PANACEA_OUTPUT_INDEX, panacea, true).isEmpty()
				&& inventory.insertItem(POTION_OUTPUT_INDEX, potion, true).isEmpty();
	}

	private boolean canWork() {
		return !getStackInSlot(GOLD_INPUT_INDEX).isEmpty() && !getStackInSlot(POTION_INPUT_INDEX).isEmpty();
	}

	@Override
	public int[] getInputSlots() {
		return INPUT_SLOTS;
	}

	@Override
	public int[] getOutputSlots() {
		return OUTPUT_SLOTS;
	}

	@Override
	public boolean isValidInput(ItemStack stack, int index) {
		if (index == GOLD_INPUT_INDEX && stack.getItem() == Main.TRANSMUTATED_GOLD_ITEM)
			return true;
		else if (index == POTION_INPUT_INDEX && stack.getItem() instanceof PotionItem
				&& !BLACKLISTED_POTIONS.contains(PotionUtils.getPotionFromItem(stack)))
			return true;

		return false;
	}

	public float getLeftFluidHeight(float partialTicks) {
		return canWork() && isNearBrain()
				? Math.max(0, MathHelper.sin(((fluidHeight + partialTicks) / 240) * (float) Math.PI * 2) * 5)
				: 0;
	}

	public float getRightFluidHeight(float partialTicks) {
		return canWork() && isNearBrain()
				? Math.max(0, MathHelper.sin(((fluidHeight + partialTicks) / 240) * (float) Math.PI * 3) * 5)
				: 0;
	}

}
