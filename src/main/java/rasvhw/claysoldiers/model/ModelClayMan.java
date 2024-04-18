package rasvhw.claysoldiers.model;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.util.helper.MathHelper;

public class ModelClayMan extends ModelBiped {
    public Cube bipedRightArmor;
    public Cube bipedLeftArmor;
    public Cube bipedChest;
    public Cube bipedRightPadding;
    public Cube bipedLeftPadding;
    public Cube bipedPadding;
    public Cube gooBase;
    public Cube logBase;
    public Cube bipedRock;
    public Cube speckyHead;
    public Cube speckyBody;
    public Cube speckyRightArm;
    public Cube speckyLeftArm;
    public Cube speckyRightLeg;
    public Cube speckyLeftLeg;
    public Cube stick;
    public Cube stickBlunt;
    public Cube stickSharp;
    public float armLeft;
    public boolean hasStick;
    public boolean hasArmor;
    public boolean hasCrown;
    public boolean hasSpecks;
    public boolean isClimbing;
    public boolean isSharpened;
    public boolean isPadded;
    public boolean isGooey;
    public boolean hasLogs;
    public boolean hasRocks;

    public ModelClayMan() {
        this(0.0F);
    }

    public ModelClayMan(float f) {
        this(f, 0.0F);
    }

    public ModelClayMan(float f, float f1) {
        this.bipedHead = new Cube(0, 0);
        this.bipedHead.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, f);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedHeadOverlay = new Cube(19, 16);
        this.bipedHeadOverlay.addBox(-1.5F, -4.0F, -1.5F, 3, 2, 3, f + 0.3F);
        this.bipedHeadOverlay.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedBody = new Cube(15, 0);
        this.bipedBody.addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, f);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightArm = new Cube(9, 7);
        this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        this.bipedRightArm.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftArm = new Cube(9, 7);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        this.bipedLeftArm.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.bipedRightLeg = new Cube(0, 7);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        this.bipedRightLeg.setRotationPoint(-1.0F, 5.0F + f1, 0.0F);
        this.bipedLeftLeg = new Cube(0, 7);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        this.bipedLeftLeg.setRotationPoint(1.0F, 5.0F + f1, 0.0F);
        this.stick = new Cube(31, 11);
        this.stick.addBox(-0.5F, 3.5F, -4.0F, 1, 1, 3, f);
        this.stick.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.stickBlunt = new Cube(32, 12);
        this.stickBlunt.addBox(-0.5F, 3.5F, -6.0F, 1, 1, 2, f);
        this.stickBlunt.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.stickSharp = new Cube(9, 0);
        this.stickSharp.addBox(-0.5F, 3.5F, -5.5F, 1, 1, 2, f - 0.2F);
        this.stickSharp.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedChest = new Cube(0, 21);
        this.bipedChest.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 2, f + 0.3F);
        this.bipedChest.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightArmor = new Cube(0, 16);
        this.bipedRightArmor.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedRightArmor.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftArmor = new Cube(0, 16);
        this.bipedLeftArmor.mirror = true;
        this.bipedLeftArmor.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedLeftArmor.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.bipedPadding = new Cube(12, 21);
        this.bipedPadding.addBox(-2.0F, 2.9F, -1.0F, 4, 2, 2, f + 0.2F);
        this.bipedPadding.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightPadding = new Cube(9, 16);
        this.bipedRightPadding.addBox(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedRightPadding.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftPadding = new Cube(9, 16);
        this.bipedLeftPadding.mirror = true;
        this.bipedLeftPadding.addBox(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedLeftPadding.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.speckyHead = new Cube(37, 17);
        this.speckyHead.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, f + 0.05F);
        this.speckyHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.speckyBody = new Cube(52, 17);
        this.speckyBody.addBox(-2.0F, 0.0F, -1.0F, 4, 5, 2, f + 0.05F);
        this.speckyBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.speckyRightArm = new Cube(37, 24);
        this.speckyRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightArm.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.speckyLeftArm = new Cube(46, 24);
        this.speckyLeftArm.mirror = true;
        this.speckyLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftArm.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.speckyRightLeg = new Cube(46, 24);
        this.speckyRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightLeg.setRotationPoint(-1.0F, 5.0F + f1, 0.0F);
        this.speckyLeftLeg = new Cube(37, 24);
        this.speckyLeftLeg.mirror = true;
        this.speckyLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftLeg.setRotationPoint(1.0F, 5.0F + f1, 0.0F);
        this.gooBase = new Cube(0, 27);
        this.gooBase.addBox(-2.5F, 9.0F, -1.5F, 5, 2, 3, f);
        this.gooBase.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.logBase = new Cube(16, 26);
        this.logBase.addBox(-2.5F, -6.5F, -1.5F, 5, 3, 3, f);
        this.logBase.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRock = new Cube(31, 3);
        this.bipedRock.mirror = true;
        this.bipedRock.addBox(-1.0F, 3.5F, -1.0F, 2, 2, 2, f + 0.375F);
        this.bipedRock.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
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
        if(this.hasCrown) {
            this.bipedHeadOverlay.render(f5);
        }

        if(this.hasStick) {
            this.stick.render(f5);
            if(this.isSharpened) {
                this.stickSharp.render(f5);
            } else {
                this.stickBlunt.render(f5);
            }
        }

        if(this.hasArmor) {
            this.bipedChest.render(f5);
            this.bipedRightArmor.render(f5);
            this.bipedLeftArmor.render(f5);
            if(this.isPadded) {
                this.bipedPadding.render(f5);
                this.bipedRightPadding.render(f5);
                this.bipedLeftPadding.render(f5);
            }
        }

        if(this.hasSpecks) {
            this.speckyHead.render(f5);
            this.speckyBody.render(f5);
            this.speckyRightArm.render(f5);
            this.speckyLeftArm.render(f5);
            this.speckyRightLeg.render(f5);
            this.speckyLeftLeg.render(f5);
        }

        if(this.isGooey) {
            this.gooBase.render(f5);
        }

        if(this.hasLogs) {
            this.logBase.render(f5);
        }

        if(this.hasRocks) {
            this.bipedRock.render(f5);
        }

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bipedHead.rotateAngleY = f3 / 57.29578F;
        this.bipedHead.rotateAngleX = f4 / 57.29578F;
        this.bipedHeadOverlay.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadOverlay.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
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
        float f6;
        float f7;
        if(this.onGround > -9990.0F) {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        if(this.isClimbing) {
            --this.bipedRightArm.rotateAngleX;
            --this.bipedLeftArm.rotateAngleX;
            this.bipedRightLeg.rotateAngleX -= 0.7F;
            this.bipedLeftLeg.rotateAngleX -= 0.7F;
            this.bipedHead.rotateAngleX -= 0.6F;
            this.bipedHeadOverlay.rotateAngleX -= 0.6F;
        } else if(this.hasLogs) {
            this.bipedRightArm.rotateAngleX = 3.141593F;
            this.bipedLeftArm.rotateAngleX = 3.141593F;
        } else if(this.armLeft > 0.0F) {
            f6 = -4.0F + this.armLeft * 4.0F;
            f7 = 1.0F - this.armLeft;
            this.bipedLeftArm.rotateAngleX = f6;
            this.bipedLeftArm.rotateAngleZ = f7;
        }

        this.speckyHead.rotateAngleX = this.bipedHead.rotateAngleX;
        this.speckyHead.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedPadding.rotateAngleY = this.bipedChest.rotateAngleY = this.speckyBody.rotateAngleY = this.bipedBody.rotateAngleY;
        this.stickBlunt.rotateAngleX = this.stickSharp.rotateAngleX = this.stick.rotateAngleX = this.bipedRightPadding.rotateAngleX = this.bipedRightArmor.rotateAngleX = this.speckyRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.stickBlunt.rotateAngleY = this.stickSharp.rotateAngleY = this.stick.rotateAngleY = this.bipedRightPadding.rotateAngleY = this.bipedRightArmor.rotateAngleY = this.speckyRightArm.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.stickBlunt.rotateAngleZ = this.stickSharp.rotateAngleZ = this.stick.rotateAngleZ = this.bipedRightPadding.rotateAngleZ = this.bipedRightArmor.rotateAngleZ = this.speckyRightArm.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
        this.bipedRock.rotateAngleX = this.bipedLeftPadding.rotateAngleX = this.bipedLeftArmor.rotateAngleX = this.speckyLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
        this.bipedRock.rotateAngleY = this.bipedLeftPadding.rotateAngleY = this.bipedLeftArmor.rotateAngleY = this.speckyLeftArm.rotateAngleY = this.bipedLeftArm.rotateAngleY;
        this.bipedRock.rotateAngleZ = this.bipedLeftPadding.rotateAngleZ = this.bipedLeftArmor.rotateAngleZ = this.speckyLeftArm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
        this.speckyRightLeg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
        this.speckyRightLeg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
        this.speckyRightLeg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
        this.speckyLeftLeg.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
        this.speckyLeftLeg.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
        this.speckyLeftLeg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
    }
}
