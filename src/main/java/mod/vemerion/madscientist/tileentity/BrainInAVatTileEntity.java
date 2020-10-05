package mod.vemerion.madscientist.tileentity;

import mod.vemerion.madscientist.Main;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class BrainInAVatTileEntity extends MachineTileEntity {

	private static final int BROKEN_INTERVAL = 20 * 60 * 15;	

	private int ticksExisted;
	private int brokenStage;
	private float brainPosition;

	public BrainInAVatTileEntity() {
		super(Main.BRAIN_IN_A_VAT_TILE_ENTITY_TYPE);

		this.brainPosition = rand.nextFloat() * 60;
	}

	@Override
	public double getMaxRenderDistanceSquared() {
		return 32 * 32;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(-2, 0, -2).expand(0, -2, 0);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt("ticksExisted", ticksExisted);
		compound.putInt("brokenStage", brokenStage);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("ticksExisted"))
			ticksExisted = compound.getInt("ticksExisted");
		if (compound.contains("brokenStage"))
			brokenStage = compound.getInt("brokenStage");
	}

	@Override
	public void tick() {
		if (!hasWorld())
			return;
		if (!world.isRemote) {
			ticksExisted++;
			if (ticksExisted > BROKEN_INTERVAL * (brokenStage + 1) && rand.nextDouble() < 0.03) {
				brokenStage++;
				world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
				if (brokenStage == 3) {
					world.playSound(null, getPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
				}
			}
		} else {
			brainPosition += 1;
		}
	}

	public float getBrainPosition(float partialTicks) {
		return MathHelper.sin(((brainPosition + partialTicks) / 60) * (float) Math.PI * 2) * 0.07f;
	}

	public int getBrokenStage() {
		return brokenStage;
	}
	
	public void setBrokenStage(int brokenStage) {
		this.brokenStage = brokenStage;
	}
	
	public boolean isBroken() {
		return brokenStage > 2;
	}
}
