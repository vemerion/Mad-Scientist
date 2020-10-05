package mod.vemerion.madscientist.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Created using Tabula 8.0.0
 */
public class VatModel extends Model {
    public ModelRenderer glass;
    public ModelRenderer base;

    public VatModel() {
    	super(RenderType::getEntityCutout);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.glass = new ModelRenderer(this, 0, 0);
        this.glass.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.glass.addBox(-6.0F, -7.0F, -6.0F, 12.0F, 13.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 25);
        this.base.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.base.addBox(-7.0F, 0.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, 0.0F, 0.0F);     
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.glass, this.base).forEach((modelRenderer) -> { 
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
