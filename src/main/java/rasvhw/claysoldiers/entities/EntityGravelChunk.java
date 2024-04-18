package rasvhw.claysoldiers.entities;

import com.mojang.nbt.CompoundTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.fx.EntityDiggingFX;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;

import java.util.List;

public class EntityGravelChunk extends Entity {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int field_28019_h = 0;
    private boolean inGround = false;
    public boolean doesArrowBelongToPlayer = false;
    public int arrowShake = 0;
    public EntityLiving owner;
    private int ticksInGround;
    private int ticksInAir = 0;
    public int clayTeam;
    public int entityAge;

    public EntityGravelChunk(World world) {
        super(world);
        this.setSize(0.1F, 0.1F);
        this.viewScale = 5.0D;
        this.setPos(this.x, this.y, this.z);
    }

    @Override
    protected void init() {

    }

    public EntityGravelChunk(World world, double d, double d1, double d2, int i) {
        super(world);
        this.setSize(0.1F, 0.1F);
        this.viewScale = 5.0D;
        this.setPos(d, d1, d2);
        this.heightOffset = 0.0F;
        this.clayTeam = i;
    }

    public EntityGravelChunk(World world, EntityLiving entityliving, int i) {
        super(world);
        this.owner = entityliving;
        this.doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        this.setSize(0.1F, 0.1F);
        this.viewScale = 5.0D;
        this.setPos(entityliving.x, entityliving.y + (double)entityliving.getHeadHeight(), entityliving.z);
        this.setRot(entityliving.yRot, entityliving.xRot);
        this.x -= (double)(MathHelper.cos(this.yRot / 180.0F * 3.141593F) * 0.16F);
        this.y -= (double)0.1F;
        this.z -= (double)(MathHelper.sin(this.yRot / 180.0F * 3.141593F) * 0.16F);
        this.setPos(this.x, this.y, this.z);
        this.heightOffset = 0.0F;
        this.xd = (double)(-MathHelper.sin(this.yRot / 180.0F * 3.141593F) * MathHelper.cos(this.xRot / 180.0F * 3.141593F));
        this.zd = (double)(MathHelper.cos(this.yRot / 180.0F * 3.141593F) * MathHelper.cos(this.xRot / 180.0F * 3.141593F));
        this.yd = (double)(-MathHelper.sin(this.xRot / 180.0F * 3.141593F));
        this.setArrowHeading(this.xd, this.yd, this.zd, 1.5F, 1.0F);
        this.clayTeam = i;
    }
    public void setArrowHeading(double d, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= (double)f2;
        d1 /= (double)f2;
        d2 /= (double)f2;
        d += this.random.nextGaussian() * (double)0.0075F * (double)f1;
        d1 += this.random.nextGaussian() * (double)0.0075F * (double)f1;
        d2 += this.random.nextGaussian() * (double)0.0075F * (double)f1;
        d *= (double)f;
        d1 *= (double)f;
        d2 *= (double)f;
        this.xd = d;
        this.yd = d1;
        this.zd = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        this.yRotO = this.yRot = (float)(Math.atan2(d, d2) * 180.0D / (double)(float)Math.PI);
        this.xRotO = this.xRot = (float)(Math.atan2(d1, (double)f3) * 180.0D / (double)(float)Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    public void lerpMotion(double d, double d1, double d2) {
        this.xd = d;
        this.yd = d1;
        this.zd = d2;
        if(this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            this.yRotO = this.yRot = (float)(Math.atan2(d, d2) * 180.0D / (double)(float)Math.PI);
            this.xRotO = this.xRot = (float)(Math.atan2(d1, (double)f) * 180.0D / (double)(float)Math.PI);
            this.xRotO = this.xRot;
            this.yRotO = this.yRot;
            this.setPos(this.x, this.y, this.z);
            this.setRot(this.yRot, this.xRot);
            this.ticksInGround = 0;
        }

    }

    @Override
    public void tick() {
        ++this.entityAge;
        if(this.entityAge > 99999) {
            this.entityAge = 0;
        }
        super.tick();
        if(this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float i = MathHelper.sqrt_double(this.xd * this.xd + this.zd * this.zd);
            this.yRotO = this.yRot = (float)(Math.atan2(this.xd, this.zd) * 180.0D / (double)(float)Math.PI);
            this.xRotO = this.xRot = (float)(Math.atan2(this.yd, (double)i) * 180.0D / (double)(float)Math.PI);
        }

        int i18 = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
        if(i18 > 0) {
            Block.blocksList[i18].setBlockBoundsBasedOnState(this.world, this.xTile, this.yTile, this.zTile);
            AABB Vec3D = Block.blocksList[i18].getCollisionBoundingBoxFromPool(this.world, this.xTile, this.yTile, this.zTile);
            if(Vec3D != null && Vec3D.isVecInside(Vec3d.createVector(this.x, this.y, this.z))) {
                this.inGround = true;
            }
        }

        if(this.arrowShake > 0) {
            --this.arrowShake;
        }

        if(this.inGround) {
            int i20 = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
            int i21 = this.world.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            if(i20 == this.inTile && i21 == this.field_28019_h) {
                ++this.ticksInGround;
                if(this.ticksInGround == 1200) {
                    this.remove();
                }

            } else {
                this.inGround = false;
                this.xd *= (double)(this.random.nextFloat() * 0.2F);
                this.yd *= (double)(this.random.nextFloat() * 0.2F);
                this.zd *= (double)(this.random.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            Vec3d Vec3d19 = Vec3d.createVector(this.x, this.y, this.z);
            Vec3d Vec3d1 = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
            HitResult movingobjectposition = this.world.checkBlockCollisionBetweenPoints(Vec3d19, Vec3d1, false, true);
            Vec3d19 = Vec3d.createVector(this.x, this.y, this.z);
            Vec3d1 = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
            if(movingobjectposition != null) {
                Vec3d1 = Vec3d.createVector(movingobjectposition.location.xCoord, movingobjectposition.location.yCoord, movingobjectposition.location.zCoord);
            }

            Entity entity = null;
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.addCoord(this.xd, this.yd, this.zd).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;

            float f5;
            double b;
            for(int f2 = 0; f2 < list.size(); ++f2) {
                Entity f3 = (Entity)list.get(f2);
                if(f3.collidesWith(f3) && (f3 != this.owner || this.ticksInAir >= 5)) {
                    f5 = 0.3F;
                    AABB a = f3.bb.expand((double)f5, (double)f5, (double)f5);
                    HitResult f6 = a.func_1169_a(Vec3d19, Vec3d1);
                    if(f6 != null) {
                        b = Vec3d19.distanceTo(f6.location);
                        if(b < d || d == 0.0D) {
                            entity = f3;
                            d = b;
                        }
                    }
                }
            }

            if(entity != null) {
                movingobjectposition = new HitResult(entity);
            }

            float f24;
            if(movingobjectposition != null) {
                if(movingobjectposition.entity != null) {
                    byte b22 = 4;
                    if(!(movingobjectposition.entity instanceof EntityClayMan)) {
                        b22 = 0;
                    }

                    if(movingobjectposition.entity.hurt(this.owner, b22, null)) {
                        this.remove();
                    }
                } else {
                    this.xTile = movingobjectposition.x;
                    this.yTile = movingobjectposition.y;
                    this.zTile = movingobjectposition.z;
                    this.inTile = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
                    this.field_28019_h = this.world.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                    this.xd = (double)((float)(movingobjectposition.location.xCoord - this.x));
                    this.yd = (double)((float)(movingobjectposition.location.yCoord - this.y));
                    this.zd = (double)((float)(movingobjectposition.location.zCoord - this.z));
                    f24 = MathHelper.sqrt_double(this.xd * this.xd + this.yd * this.yd + this.zd * this.zd);
                    this.x -= this.xd / (double)f24 * (double)0.05F;
                    this.y -= this.yd / (double)f24 * (double)0.05F;
                    this.z -= this.zd / (double)f24 * (double)0.05F;
                    this.inGround = true;
                    this.arrowShake = 7;
                }
            }

            this.x += this.xd;
            this.y += this.yd;
            this.z += this.zd;
            f24 = MathHelper.sqrt_double(this.xd * this.xd + this.zd * this.zd);
            this.yRot = (float)(Math.atan2(this.xd, this.zd) * 180.0D / (double)(float)Math.PI);

            for(this.xRot = (float)(Math.atan2(this.yd, (double)f24) * 180.0D / (double)(float)Math.PI); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
            }

            while(this.xRot - this.xRotO >= 180.0F) {
                this.xRotO += 360.0F;
            }

            while(this.yRot - this.yRotO < -180.0F) {
                this.yRotO -= 360.0F;
            }

            while(this.yRot - this.yRotO >= 180.0F) {
                this.yRotO += 360.0F;
            }

            this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2F;
            this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2F;
            float f23 = 0.99F;
            f5 = 0.03F;
            if(this.isInWater()) {
                for(int i25 = 0; i25 < 4; ++i25) {
                    float f27 = 0.25F;
                    this.world.spawnParticle("bubble", this.x - this.xd * (double)f27, this.y - this.yd * (double)f27, this.z - this.zd * (double)f27, this.xd, this.yd, this.zd);
                }

                f23 = 0.8F;
            }

            this.xd *= (double)f23;
            this.yd *= (double)f23;
            this.zd *= (double)f23;
            this.yd -= (double)f5;
            this.setPos(this.x, this.y, this.z);
            if(this.ticksInGround > 0 || this.inGround) {
                this.remove();
            }

            if(!this.isAlive()) {
                double d26 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                b = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.25D;
                double c = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntityDiggingFX(this.world, d26, b, c, 0.0D, 0.0D, 0.0D, Block.gravel, 0));
                this.world.playSoundAtEntity(null,this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
            }

        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbttagcompound) {
        nbttagcompound.putShort("xTile", (short)this.xTile);
        nbttagcompound.putShort("yTile", (short)this.yTile);
        nbttagcompound.putShort("zTile", (short)this.zTile);
        nbttagcompound.putByte("inTile", (byte)this.inTile);
        nbttagcompound.putByte("inData", (byte)this.field_28019_h);
        nbttagcompound.putByte("shake", (byte)this.arrowShake);
        nbttagcompound.putByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbttagcompound.putBoolean("player", this.doesArrowBelongToPlayer);
        nbttagcompound.putByte("ClayTeam", (byte)this.clayTeam);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbttagcompound) {
        this.xTile = nbttagcompound.getShort("xTile");
        this.yTile = nbttagcompound.getShort("yTile");
        this.zTile = nbttagcompound.getShort("zTile");
        this.inTile = nbttagcompound.getByte("inTile") & 255;
        this.field_28019_h = nbttagcompound.getByte("inData") & 255;
        this.arrowShake = nbttagcompound.getByte("shake") & 255;
        this.inGround = nbttagcompound.getByte("inGround") == 1;
        this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
        this.clayTeam = nbttagcompound.getByte("ClayTeam") & 255;
    }

    @Override
    public void playerTouch(EntityPlayer entityplayer) {
        if(!this.world.isClientSide) {
            if(this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0) {
                entityplayer.inventory.insertItem(new ItemStack(Item.ammoArrow, 1), true);
                this.world.playSoundAtEntity(null,this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityplayer.onItemPickup(this, 1);
                this.remove();
            }
        }
    }
}
