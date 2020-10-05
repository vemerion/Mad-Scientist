package mod.vemerion.madscientist.item;

import mod.vemerion.madscientist.Main;
import mod.vemerion.madscientist.model.MadScientistHairModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MadScientistHairItem extends ArmorItem {
	@OnlyIn(Dist.CLIENT)
	MadScientistHairModel<?> model;

	public MadScientistHairItem(EquipmentSlotType slot) {
		super(mod.vemerion.madscientist.Main.MAD_SCIENTIST_HAIR_MATERIAL, slot, new Item.Properties().maxStackSize(1).group(ItemGroup.SEARCH));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return Main.MODID + ":textures/armor/mad_scientist_hair.png";
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		if (model == null) {
			model = new MadScientistHairModel<>(0.3f);
		}

		model.isSitting = _default.isSitting;
		model.isSneak = _default.isSneak;
		model.isChild = _default.isChild;
		model.rightArmPose = _default.rightArmPose;
		model.leftArmPose = _default.leftArmPose;

		return (A) model;
	}
}
