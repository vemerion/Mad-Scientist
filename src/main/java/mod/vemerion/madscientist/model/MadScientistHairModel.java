package mod.vemerion.madscientist.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class MadScientistHairModel<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer hair1;
    public ModelRenderer hair2;
    public ModelRenderer hair3;
    public ModelRenderer hair4;
    public ModelRenderer hair5;

    public MadScientistHairModel(float modelSize) {
    	super(modelSize, 0, 128, 128);
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.hair3 = new ModelRenderer(this, 0, 82);
        this.hair3.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.hair3.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 1.0F, modelSize);
        this.hair4 = new ModelRenderer(this, 34, 82);
        this.hair4.setRotationPoint(0.0F, -4.0F, 1.0F);
        this.hair4.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 1.0F, modelSize);
        this.setRotateAngle(hair4, -0.17453292519943295F, 0.0F, 0.0F);
        this.hair5 = new ModelRenderer(this, 0, 100);
        this.hair5.setRotationPoint(0.0F, -4.0F, 2.0F);
        this.hair5.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 1.0F, modelSize);
        this.setRotateAngle(hair5, -0.3490658503988659F, 0.0F, 0.0F);
        this.hair1 = new ModelRenderer(this, 0, 64);
        this.hair1.setRotationPoint(0.0F, -4.0F, -2.0F);
        this.hair1.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 1.0F, modelSize);
        this.setRotateAngle(hair1, 0.35185837453889574F, 0.0F, 0.0F);
        this.hair2 = new ModelRenderer(this, 34, 64);
        this.hair2.setRotationPoint(0.0F, -4.0F, -1.0F);
        this.hair2.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 1.0F, modelSize);
        this.setRotateAngle(hair2, 0.17453292519943295F, 0.0F, 0.0F);
        this.bipedHead.addChild(this.hair3);
        this.bipedHead.addChild(this.hair4);
        this.bipedHead.addChild(this.hair5);
        this.bipedHead.addChild(this.hair1);
        this.bipedHead.addChild(this.hair2);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.bipedHead).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
