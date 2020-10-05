package mod.vemerion.madscientist.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ElixirOfLifeItem extends Item {

	public ElixirOfLifeItem() {
		super(new Item.Properties().group(ItemGroup.SEARCH).rarity(Rarity.EPIC).maxStackSize(1));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if (!worldIn.isRemote && entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLiving;
			player.heal(player.getMaxHealth());
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 60, 2));
			player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 20 * 60, 2));
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 60, 2));
			player.getFoodStats().setFoodLevel(20);
			player.getFoodStats().setFoodSaturationLevel(20);
		}
		return new ItemStack(Items.GLASS_BOTTLE);
	}

}
