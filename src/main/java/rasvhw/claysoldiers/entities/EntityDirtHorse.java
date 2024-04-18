package rasvhw.claysoldiers.entities;

import com.mojang.nbt.CompoundTag;
import rasvhw.claysoldiers.ClaySoldiers;
import rasvhw.claysoldiers.mixin.EntityAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.fx.EntitySlimeChunkFX;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

import java.util.List;

public class EntityDirtHorse extends EntityAnimal {
    public boolean gotRider;

    public EntityDirtHorse(World world) {
        super(world);
        this.setHealthRaw(30);
        this.heightOffset = 0.0F;
        this.footSize = 0.1F;
        this.moveSpeed = 0.6F;
        this.setSize(0.25F, 0.4F);
        this.setPos(this.x, this.y, this.z);
        this.skinName = "dirt_horse";
        this.viewScale = 5.0D;
    }

    public EntityDirtHorse(World world, double x, double y, double z) {
        super(world);
        this.setHealthRaw(30);
        this.heightOffset = 0.0F;
        this.footSize = 0.1F;
        this.moveSpeed = 0.6F;
        this.setSize(0.25F, 0.4F);
        this.setPos(x, y, z);
        this.skinName = "dirt_horse";
        this.viewScale = 5.0D;
        this.world.playSoundAtEntity(null, this, "step.gravel", 0.8F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.9F);
    }
    public String getEntityTexture() {return "assets/claysoldiers/entity/dirt_horse.png";}
    public String getDefaultEntityTexture() {
        return "assets/claysoldiers/entity/dirt_horse.png";
    }
    @Override
    public void tick() {
        super.tick();
        if(this.gotRider) {
            if(this.passenger != null) {
                this.gotRider = false;
                return;
            }

            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(0.1D, 0.1D, 0.1D));

            for(int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                if(entity instanceof EntityClayMan) {
                    EntityLiving entityliving = (EntityLiving)entity;
                    if(entityliving.vehicle == null && entityliving.passenger != this) {
                        entity.startRiding(this);
                        break;
                    }
                }
            }

            this.gotRider = false;
        }

    }

    @Override
    public void updatePlayerActionState() {
        if(this.passenger != null && this.passenger instanceof EntityClayMan) {
            EntityClayMan rider = (EntityClayMan)this.passenger;
            EntityAccessor accessor = (EntityAccessor)rider;
            this.isJumping = accessor.getIsJumping() || this.isInWater();
            this.moveForward = accessor.getMoveForward() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.moveStrafing = accessor.getMoveStrafing() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.xRot = this.xRotO = rider.xRot;
            this.yRot = this.yRotO = rider.yRot;
            rider.renderYawOffset = this.renderYawOffset;
            this.passenger.fallDistance = 0.0F;
            if(!rider.isAlive() || rider.getHealth() <= 0) {
                rider.startRiding(null);
            }
        } else {
            super.updatePlayerActionState();
        }

    }

    @Override
    public void moveEntityWithHeading(float f, float f1) {
        super.moveEntityWithHeading(f, f1);
        double d2 = (this.x - this.xo) * 2.0D;
        double d3 = (this.z - this.zo) * 2.0D;
        float f5 = MathHelper.sqrt_double(d2 * d2 + d3 * d3) * 4.0F;
        if(f5 > 1.0F) {
            f5 = 1.0F;
        }

        this.limbYaw += (f5 - this.limbYaw) * 0.4F;
        this.limbSwing += this.limbYaw;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbttagcompound) {
        super.addAdditionalSaveData(nbttagcompound);
        this.gotRider = this.passenger == null;
        nbttagcompound.putBoolean("GotRider", this.gotRider);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbttagcompound) {
        super.readAdditionalSaveData(nbttagcompound);
        this.gotRider = nbttagcompound.getBoolean("GotRider");
    }

    @Override
    protected String getHurtSound() {
        this.world.playSoundAtEntity(null, this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    @Override
    protected String getDeathSound() {
        return "step.gravel";
    }

    @Override
    protected void jump() {
        this.yd = 0.4D;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void dropFewItems() {
        Item item1 = ClaySoldiers.dirtHorse;
        this.spawnAtLocation(item1.id, 1);
    }

    public boolean hurt(Entity e, int i, DamageType type) {
        if(e == null || !(e instanceof EntityClayMan)) {
            i = 30;
        }

        boolean fred = super.hurt(e, i, (DamageType) null);
        if(fred && this.getHealth() <= 0) {
            Item item1 = ClaySoldiers.dirtHorse;

            for(int q = 0; q < 24; ++q) {
                double a = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                double b = this.y + 0.25D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                double c = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntitySlimeChunkFX(this.world, a, b, c, item1));
            }

            this.remove();
        }

        return fred;
    }

    @Override
    public void knockBack(Entity entity, int i, double d, double d1) {
        super.knockBack(entity, i, d, d1);
        if(entity != null && entity instanceof EntityClayMan) {
            this.xd *= 0.6D;
            this.yd *= 0.75D;
            this.zd *= 0.6D;
        }

    }

    @Override
    public boolean canClimb() {
        return false;
    }
}
