package mod.vemerion.madscientist.itemgroup;

import mod.vemerion.madscientist.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

public class MadScientistItemGroup extends ItemGroup {

	public MadScientistItemGroup() {
		super("mad_scientist_item_group");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Main.BRAIN_IN_A_VAT_BLOCK);
	}

	@Override
	public void fill(NonNullList<ItemStack> items) {
		for (Item item : ForgeRegistries.ITEMS) {
			if (item != null)
				if (item.getRegistryName().getNamespace().equals(Main.MODID)) {
					item.fillItemGroup(ItemGroup.SEARCH, items);
				}
		}
	}

}
