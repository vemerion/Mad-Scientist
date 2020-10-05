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
public class ChrysopoeiaModel extends Model {
    public ModelRenderer base;
    public ModelRenderer rightTower1;
    public ModelRenderer leftTower1;
    public ModelRenderer rightTower2;
    public ModelRenderer rightTower3;
    public ModelRenderer rightTower4;
    public ModelRenderer rightTowerBall;
    public ModelRenderer leftTower2;
    public ModelRenderer leftTower3;
    public ModelRenderer leftTower4;
    public ModelRenderer leftTowerBall;
    public ModelRenderer lightning;

    public ChrysopoeiaModel() {
    	super(RenderType::getEntityCutout);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftTowerBall = new ModelRenderer(this, 0, 0);
        this.leftTowerBall.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.leftTowerBall.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightTower3 = new ModelRenderer(this, 0, 8);
        this.rightTower3.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.rightTower3.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightTower4 = new ModelRenderer(this, 0, 13);
        this.rightTower4.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.rightTower4.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.leftTower3 = new ModelRenderer(this, 0, 8);
        this.leftTower3.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.leftTower3.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightTower1 = new ModelRenderer(this, 0, 8);
        this.rightTower1.setRotationPoint(-5.0F, -8.0F, 0.0F);
        this.rightTower1.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftTower4 = new ModelRenderer(this, 0, 13);
        this.leftTower4.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.leftTower4.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.rightTowerBall = new ModelRenderer(this, 0, 0);
        this.rightTowerBall.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.rightTowerBall.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftTower2 = new ModelRenderer(this, 0, 13);
        this.leftTower2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.leftTower2.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.leftTower1 = new ModelRenderer(this, 0, 8);
        this.leftTower1.setRotationPoint(5.0F, -8.0F, 0.0F);
        this.leftTower1.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.lightning = new ModelRenderer(this, 48, 0);
        this.lightning.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.lightning.addBox(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.rightTower2 = new ModelRenderer(this, 0, 13);
        this.rightTower2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.rightTower2.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.base.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.leftTower4.addChild(this.leftTowerBall);
        this.rightTower2.addChild(this.rightTower3);
        this.rightTower3.addChild(this.rightTower4);
        this.leftTower2.addChild(this.leftTower3);
        this.base.addChild(this.rightTower1);
        this.leftTower3.addChild(this.leftTower4);
        this.rightTower4.addChild(this.rightTowerBall);
        this.leftTower1.addChild(this.leftTower2);
        this.base.addChild(this.leftTower1);
        this.base.addChild(this.lightning);
        this.rightTower1.addChild(this.rightTower2);   
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
