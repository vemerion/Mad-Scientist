package mod.vemerion.madscientist;

import mod.vemerion.madscientist.block.BrainInAVatBlock;
import mod.vemerion.madscientist.block.ChrysopoeiaBlock;
import mod.vemerion.madscientist.block.PanaceumBlock;
import mod.vemerion.madscientist.container.ChrysopoeiaContainer;
import mod.vemerion.madscientist.container.PanaceumContainer;
import mod.vemerion.madscientist.item.ElixirOfLifeItem;
import mod.vemerion.madscientist.item.MadScientistHairItem;
import mod.vemerion.madscientist.item.TransmutatedGoldItem;
import mod.vemerion.madscientist.lootmodifier.BrainItemLootModifier;
import mod.vemerion.madscientist.renderer.MadScientistItemStackTileEntityRenderer;
import mod.vemerion.madscientist.tileentity.BrainInAVatTileEntity;
import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(setup(new TransmutatedGoldItem(), "transmutated_gold_item"));
		event.getRegistry().register(setup(new ElixirOfLifeItem(), "elixir_of_life_item"));
		event.getRegistry().register(setup(new MadScientistHairItem(EquipmentSlotType.HEAD), "mad_scientist_hair_item"));
		event.getRegistry().register(setup(new Item(new Item.Properties().group(ItemGroup.SEARCH)), "brain_item"));



		event.getRegistry()
				.register(setup(
						new BlockItem(Main.BRAIN_IN_A_VAT_BLOCK, new Item.Properties()
								.setISTER(() -> MadScientistItemStackTileEntityRenderer::new).group(ItemGroup.SEARCH)),
						"brain_in_a_vat_block_item"));
		event.getRegistry()
				.register(setup(
						new BlockItem(Main.CHRYSOPOEIA_BLOCK, new Item.Properties()
								.setISTER(() -> MadScientistItemStackTileEntityRenderer::new).group(ItemGroup.SEARCH)),
						"chrysopoeia_block_item"));

		event.getRegistry()
				.register(setup(
						new BlockItem(Main.PANACEUM_BLOCK, new Item.Properties()
								.setISTER(() -> MadScientistItemStackTileEntityRenderer::new).group(ItemGroup.SEARCH)),
						"panaceum_block_item"));

	}

	@SubscribeEvent
	public static void onRegisterBlock(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(setup(new BrainInAVatBlock(), "brain_in_a_vat_block"));
		event.getRegistry().register(setup(new ChrysopoeiaBlock(), "chrysopoeia_block"));
		event.getRegistry().register(setup(new PanaceumBlock(), "panaceum_block"));

	}

	@SubscribeEvent
	public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
		TileEntityType<BrainInAVatTileEntity> brainInAVatTileEntity = TileEntityType.Builder
				.create(BrainInAVatTileEntity::new, Main.BRAIN_IN_A_VAT_BLOCK).build(null);

		TileEntityType<ChrysopoeiaTileEntity> chrysopoeiaTileEntity = TileEntityType.Builder
				.create(ChrysopoeiaTileEntity::new, Main.CHRYSOPOEIA_BLOCK).build(null);

		TileEntityType<PanaceumTileEntity> panaceumTileEntity = TileEntityType.Builder
				.create(PanaceumTileEntity::new, Main.PANACEUM_BLOCK).build(null);

		event.getRegistry().register(setup(brainInAVatTileEntity, "brain_in_a_vat_tile_entity_type"));
		event.getRegistry().register(setup(chrysopoeiaTileEntity, "chrysopoeia_tile_entity_type"));
		event.getRegistry().register(setup(panaceumTileEntity, "panaceum_tile_entity_type"));

	}

	@SubscribeEvent
	public static void onRegisterContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry()
				.register(setup(IForgeContainerType.create(ChrysopoeiaContainer::new), "chrysopoeia_container_type"));
		event.getRegistry()
				.register(setup(IForgeContainerType.create(PanaceumContainer::new), "panaceum_container_type"));

	}
	
	@SubscribeEvent
	public static void onRegisterLootModifier(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		event.getRegistry().register(setup(new BrainItemLootModifier.Serializer(), "brain_item_loot_modifier"));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}
