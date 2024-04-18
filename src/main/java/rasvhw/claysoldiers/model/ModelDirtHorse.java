package rasvhw.claysoldiers.model;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.util.helper.MathHelper;

public class ModelDirtHorse extends ModelBiped {
    public Cube bipedEar1;
    public Cube bipedEar2;
    public Cube bipedTail;
    public Cube bipedNeck;
    public Cube bipedMane;

    public ModelDirtHorse() {
        this(0.0F);
    }

    public ModelDirtHorse(float f) {
        this(f, 0.0F);
    }

    public ModelDirtHorse(float f, float f1) {
        this.bipedHead = new Cube(0, 0);
        this.bipedHead.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, f + 0.2F);
        this.bipedHead.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar1 = new Cube(0, 0);
        this.bipedEar1.addBox(-1.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar1.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar2 = new Cube(0, 0);
        this.bipedEar2.addBox(0.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar2.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.bipedBody = new Cube(0, 8);
        this.bipedBody.addBox(-2.0F, 0.0F, -4.0F, 4, 4, 8, f);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedNeck = new Cube(12, 0);
        this.bipedNeck.addBox(-1.0F, 0.0F, -6.0F, 2, 2, 6, f + 0.4F);
        this.bipedNeck.setRotationPoint(0.0F, 0.0F + f1, -2.0F);
        this.bipedMane = new Cube(28, 0);
        this.bipedMane.addBox(-1.0F, -1.1F, -6.0F, 2, 1, 6, f);
        this.bipedMane.setRotationPoint(0.0F, 0.0F + f1, -2.0F);
        this.bipedRightArm = new Cube(24, 10);
        this.bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.bipedRightArm.setRotationPoint(-1.0F, 3.75F + f1, -2.75F);
        this.bipedLeftArm = new Cube(24, 10);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.bipedLeftArm.setRotationPoint(1.0F, 3.75F + f1, -2.75F);
        this.bipedRightLeg = new Cube(24, 10);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.bipedRightLeg.setRotationPoint(-1.0F, 3.75F + f1, 2.75F);
        this.bipedLeftLeg = new Cube(24, 10);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.bipedLeftLeg.setRotationPoint(1.0F, 3.75F + f1, 2.75F);
        this.bipedTail = new Cube(36, 11);
        this.bipedTail.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, f + 0.15F);
        this.bipedTail.setRotationPoint(0.0F, 0.0F + f1, 3.75F);
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedNeck.render(f5);
        this.bipedMane.render(f5);
        this.bipedTail.render(f5);
        this.bipedEar1.render(f5);
        this.bipedEar2.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bipedHead.rotateAngleY = f3 / 57.29578F;
        this.bipedHead.rotateAngleX = f4 / 57.29578F + 0.79F;
        this.bipedHeadOverlay.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadOverlay.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.25F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.25F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.5F * f1;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.5F * f1;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        if(this.isRiding) {
            this.bipedRightArm.rotateAngleX += -0.6283185F;
            this.bipedLeftArm.rotateAngleX += -0.6283185F;
            this.bipedRightLeg.rotateAngleX = -1.256637F;
            this.bipedLeftLeg.rotateAngleX = -1.256637F;
            this.bipedRightLeg.rotateAngleY = 0.3141593F;
            this.bipedLeftLeg.rotateAngleY = -0.3141593F;
        }

        if(this.field_1279_h) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F;
        }

        if(this.field_1278_i) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.3141593F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        if(this.onGround > -9990.0F) {
            float f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
        }

        this.bipedTail.rotateAngleX = 0.3F + this.bipedRightArm.rotateAngleX * this.bipedRightArm.rotateAngleX;
        this.bipedMane.rotateAngleX = this.bipedNeck.rotateAngleX = -0.6F;
        this.bipedEar1.rotateAngleX = this.bipedEar2.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedEar1.rotateAngleY = this.bipedEar2.rotateAngleY = this.bipedHead.rotateAngleY;
    }
}
