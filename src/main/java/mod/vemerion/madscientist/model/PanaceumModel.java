package mod.vemerion.madscientist.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PanaceumModel extends Model {
    public ModelRenderer base;
    public ModelRenderer leftPipe1;
    public ModelRenderer rightPipe1;
    public ModelRenderer buttonBase1;
    public ModelRenderer buttonBase2;
    public ModelRenderer leftPipe2;
    public ModelRenderer leftPipe3;
    public ModelRenderer leftPipe4;
    public ModelRenderer leftPipe5;
    public ModelRenderer leftPipe6;
    public ModelRenderer leftPipe7;
    public ModelRenderer rightPipe2;
    public ModelRenderer rightPipe3;
    public ModelRenderer rightPipe4;
    public ModelRenderer rightPipe5;
    public ModelRenderer rightPipe6;
    public ModelRenderer rightPipe7;
    public ModelRenderer button1;
    public ModelRenderer button2;
    public ModelRenderer leftFluid;
    public ModelRenderer rightFluid;


    public PanaceumModel() {
    	super(RenderType::getEntityCutout);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftPipe6 = new ModelRenderer(this, 0, 0);
        this.leftPipe6.setRotationPoint(1.0F, -1.0F, 1.0F);
        this.leftPipe6.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.base.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.rightFluid = new ModelRenderer(this, 48, 23);
        this.rightFluid.setRotationPoint(-6.0F, 18.0F, -5.0F);
        this.rightFluid.addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);   
        this.rightPipe5 = new ModelRenderer(this, 48, 10);
        this.rightPipe5.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.rightPipe5.addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe1 = new ModelRenderer(this, 48, 14);
        this.leftPipe1.setRotationPoint(4.0F, -3.0F, 0.0F);
        this.leftPipe1.addBox(0.0F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe2 = new ModelRenderer(this, 0, 0);
        this.leftPipe2.setRotationPoint(0.0F, -1.0F, -4.0F);
        this.leftPipe2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.buttonBase1 = new ModelRenderer(this, 6, 24);
        this.buttonBase1.setRotationPoint(-1.5F, -11.0F, -0.5F);
        this.buttonBase1.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe5 = new ModelRenderer(this, 48, 10);
        this.leftPipe5.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.leftPipe5.addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe3 = new ModelRenderer(this, 48, 10);
        this.rightPipe3.setRotationPoint(-1.0F, -1.0F, -1.0F);
        this.rightPipe3.addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe7 = new ModelRenderer(this, 48, 14);
        this.rightPipe7.setRotationPoint(0.0F, -1.0F, 4.0F);
        this.rightPipe7.addBox(0.0F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe4 = new ModelRenderer(this, 48, 0);
        this.leftPipe4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftPipe4.addBox(0.0F, -7.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe1 = new ModelRenderer(this, 48, 14);
        this.rightPipe1.setRotationPoint(-5.0F, -3.0F, 0.0F);
        this.rightPipe1.addBox(0.0F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe3 = new ModelRenderer(this, 48, 10);
        this.leftPipe3.setRotationPoint(-1.0F, -1.0F, -1.0F);
        this.leftPipe3.addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe7 = new ModelRenderer(this, 48, 14);
        this.leftPipe7.setRotationPoint(0.0F, -1.0F, 4.0F);
        this.leftPipe7.addBox(0.0F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.button2 = new ModelRenderer(this, 10, 24);
        this.button2.setRotationPoint(0.0F, -0.5F, -1.0F);
        this.button2.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe6 = new ModelRenderer(this, 0, 0);
        this.rightPipe6.setRotationPoint(1.0F, -1.0F, 1.0F);
        this.rightPipe6.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.buttonBase2 = new ModelRenderer(this, 6, 24);
        this.buttonBase2.setRotationPoint(1.5F, -11.0F, -0.5F);
        this.buttonBase2.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.button1 = new ModelRenderer(this, 0, 24);
        this.button1.setRotationPoint(0.0F, -0.5F, -1.0F);
        this.button1.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leftFluid = new ModelRenderer(this, 48, 19);
        this.leftFluid.setRotationPoint(3.0F, 18.0F, -5.0F);
        this.leftFluid.addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe2 = new ModelRenderer(this, 0, 0);
        this.rightPipe2.setRotationPoint(0.0F, -1.0F, -4.0F);
        this.rightPipe2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.rightPipe4 = new ModelRenderer(this, 48, 0);
        this.rightPipe4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightPipe4.addBox(0.0F, -7.0F, 0.0F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leftPipe5.addChild(this.leftPipe6);
        this.rightPipe4.addChild(this.rightPipe5);
        this.base.addChild(this.leftPipe1);
        this.leftPipe1.addChild(this.leftPipe2);
        this.base.addChild(this.buttonBase1);
        this.leftPipe4.addChild(this.leftPipe5);
        this.rightPipe2.addChild(this.rightPipe3);
        this.rightPipe6.addChild(this.rightPipe7);
        this.leftPipe3.addChild(this.leftPipe4);
        this.base.addChild(this.rightPipe1);
        this.leftPipe2.addChild(this.leftPipe3);
        this.leftPipe6.addChild(this.leftPipe7);
        this.buttonBase2.addChild(this.button2);
        this.rightPipe5.addChild(this.rightPipe6);
        this.base.addChild(this.buttonBase2);
        this.buttonBase1.addChild(this.button1);
        this.rightPipe1.addChild(this.rightPipe2);
        this.rightPipe3.addChild(this.rightPipe4);      
    }
    
    public void renderLeftFluid(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
        leftFluid.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1, 1, 1, 1);
    }
    
    public void renderRightFluid(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
        rightFluid.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1, 1, 1, 1);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.base).forEach((modelRenderer) -> { 
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
