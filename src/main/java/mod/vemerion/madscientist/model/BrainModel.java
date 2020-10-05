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
public class BrainModel extends Model {
    public ModelRenderer upper;
    public ModelRenderer lower;

    public BrainModel() {
    	super(RenderType::getEntityCutout);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lower = new ModelRenderer(this, 0, 24);
        this.lower.setRotationPoint(3.0F, 10.0F, 6.0F);
        this.lower.addBox(-7.0F, -7.0F, -7.0F, 8.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.upper = new ModelRenderer(this, 0, 0);
        this.upper.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.upper.addBox(-7.0F, -7.0F, -7.0F, 14.0F, 10.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.upper.addChild(this.lower);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
    	matrixStackIn.push();
    	matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        ImmutableList.of(this.upper).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        matrixStackIn.pop();
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