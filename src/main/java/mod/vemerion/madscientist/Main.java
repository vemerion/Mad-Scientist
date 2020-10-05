package mod.vemerion.madscientist;

import mod.vemerion.madscientist.container.ContainerMachineContainer;
import mod.vemerion.madscientist.item.MadScientistHairMaterial;
import mod.vemerion.madscientist.itemgroup.MadScientistItemGroup;
import mod.vemerion.madscientist.tileentity.BrainInAVatTileEntity;
import mod.vemerion.madscientist.tileentity.ChrysopoeiaTileEntity;
import mod.vemerion.madscientist.tileentity.PanaceumTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Main.MODID)
public class Main {
	public static final String MODID = "mad-scientist";
	
	@ObjectHolder(Main.MODID + ":elixir_of_life_item")
	public static final Item ELIXIR_OF_LIFE_ITEM = null;
	
	@ObjectHolder(Main.MODID + ":brain_item")
	public static final Item BRAIN_ITEM = null;
	
	@ObjectHolder(Main.MODID + ":brain_in_a_vat_block")
	public static final Block BRAIN_IN_A_VAT_BLOCK = null;
	
	@ObjectHolder(Main.MODID + ":chrysopoeia_block")
	public static final Block CHRYSOPOEIA_BLOCK = null;
	
	@ObjectHolder(Main.MODID + ":panaceum_block")
	public static final Block PANACEUM_BLOCK = null;

	
	@ObjectHolder(Main.MODID + ":transmutated_gold_item")
	public static final Item TRANSMUTATED_GOLD_ITEM = null;
	
	@ObjectHolder(Main.MODID + ":brain_in_a_vat_tile_entity_type")
	public static final TileEntityType<BrainInAVatTileEntity> BRAIN_IN_A_VAT_TILE_ENTITY_TYPE = null;

	@ObjectHolder(Main.MODID + ":chrysopoeia_tile_entity_type")
	public static final TileEntityType<ChrysopoeiaTileEntity> CHRYSOPOEIA_TILE_ENTITY_TYPE = null;
	
	@ObjectHolder(Main.MODID + ":panaceum_tile_entity_type")
	public static final TileEntityType<PanaceumTileEntity> PANACEUM_TILE_ENTITY_TYPE = null;

	@ObjectHolder(Main.MODID + ":chrysopoeia_container_type")
	public static final ContainerType<ContainerMachineContainer> CHRYSOPOEIA_CONTAINER_TYPE = null;
	
	@ObjectHolder(Main.MODID + ":panaceum_container_type")
	public static final ContainerType<ContainerMachineContainer> PANACEUM_CONTAINER_TYPE = null;

	
	MadScientistItemGroup madScientistItemGroup = new MadScientistItemGroup();
	
	public static final MadScientistHairMaterial MAD_SCIENTIST_HAIR_MATERIAL = new MadScientistHairMaterial();

}
