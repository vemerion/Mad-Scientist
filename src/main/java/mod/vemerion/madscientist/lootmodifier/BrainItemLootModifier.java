package mod.vemerion.madscientist.lootmodifier;

import java.util.List;

import com.google.gson.JsonObject;

import mod.vemerion.madscientist.Main;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class BrainItemLootModifier extends LootModifier {

	protected BrainItemLootModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}
	
	// This is hardcoded
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (context.has(LootParameters.THIS_ENTITY) && context.get(LootParameters.THIS_ENTITY).getType() == EntityType.ZOMBIE) {
			generatedLoot.add(new ItemStack(Main.BRAIN_ITEM));
		}
		
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<BrainItemLootModifier> {

		@Override
		public BrainItemLootModifier read(ResourceLocation name, JsonObject object,
				ILootCondition[] conditionsIn) {
			return new BrainItemLootModifier(conditionsIn);
		}
	}
}