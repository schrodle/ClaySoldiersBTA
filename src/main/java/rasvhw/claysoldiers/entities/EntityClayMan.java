package rasvhw.claysoldiers.entities;

import com.mojang.nbt.CompoundTag;
import rasvhw.claysoldiers.ClaySoldiers;
import rasvhw.claysoldiers.items.ItemClayMan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.fx.EntityDiggingFX;
import net.minecraft.client.entity.fx.EntitySlimeChunkFX;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pathfinder.Path;
import net.minecraft.core.entity.player.EntityPlayer;

import java.util.List;

public class EntityClayMan extends EntityAnimal {
    public int clayTeam;
    public int weaponPoints;
    public int armorPoints;
    public int foodLeft;
    public int sugarTime;
    public int resPoints;
    public int entCount;
    public int strikeTime;
    public int climbTime;
    public int gooTime;
    public int smokeTime;
    public int gooStock;
    public int smokeStock;
    public int logs;
    public int rocks;
    public int blockX;
    public int blockY;
    public int blockZ;
    public int throwTime;
    public float swingLeft;
    public boolean gunPowdered;
    public boolean king;
    public boolean glowing;
    public boolean isSwinging;
    public boolean stickSharp;
    public boolean armorPadded;
    public boolean heavyCore;
    public boolean isSwingingLeft;
    public Entity targetFollow;
    public Entity killedByPlayer;

    public EntityClayMan(World world) {
        super(world);
        this.setHealthRaw(20);
        this.clayTeam = 0;
        this.heightOffset = 0.0F;
        this.footSize = 0.1F;
        this.moveSpeed = 0.3F;
        this.setSize(0.15F, 0.4F);
        this.setPos(this.x, this.y, this.z);
        this.skinName = "clayman";
    }

    public EntityClayMan(World world, double x, double y, double z, int i) {
        super(world);
        this.setHealthRaw(20);
        this.clayTeam = i;
        this.heightOffset = 0.0F;
        this.footSize = 0.1F;
        this.moveSpeed = 0.3F;
        this.setSize(0.15F, 0.4F);
        this.setPos(x, y, z);
        this.skinName = "clayman";
        this.viewScale = 5.0;
        this.world.playSoundAtEntity(null,this, "step.gravel", 0.8F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.9F);
    }
    public String getEntityTexture() {return clayManTexture(clayTeam);}
    public String getDefaultEntityTexture() {
        return clayManTexture(clayTeam);
    }

    public String clayManTexture(int i) {
        String base = "/assets/claysoldiers/entity/clay_";
        if (i == 0) {
            base = base + "grey";
        } else if (i == 1) {
            base = base + "red";
        } else if (i == 2) {
            base = base + "yellow";
        } else if (i == 3) {
            base = base + "green";
        } else if (i == 4) {
            base = base + "blue";
        } else if (i == 5) {
            base = base + "orange";
        } else {
            base = base + "purple";
        }
        return base + ".png";
    }

    public int teamCloth(int teamNum) {
        if (teamNum == 0) {
            return 8;
        } else if (teamNum == 1) {
            return 14;
        } else if (teamNum == 2) {
            return 4;
        } else if (teamNum == 3) {
            return 13;
        } else if (teamNum == 4) {
            return 11;
        } else {
            return teamNum == 5 ? 1 : 10;
        }
    }

    public int teamDye(int teamNum) {
        if (teamNum == 0) {
            return 8;
        } else if (teamNum == 1) {
            return 1;
        } else if (teamNum == 2) {
            return 11;
        } else if (teamNum == 3) {
            return 2;
        } else if (teamNum == 4) {
            return 4;
        } else {
            return teamNum == 5 ? 14 : 5;
        }
    }

    @Override
    public void updatePlayerActionState() {
        super.updatePlayerActionState();
        if(this.strikeTime > 0) {
            --this.strikeTime;
        }

        if(this.sugarTime > 0) {
            this.moveSpeed = 0.6F + (this.entityToAttack == null && this.targetFollow == null ? 0.0F : 0.15F);
            --this.sugarTime;
        } else {
            this.moveSpeed = 0.3F + (this.entityToAttack == null && this.targetFollow == null ? 0.0F : 0.15F);
        }

        if(this.isInWater()) {
            this.isJumping = true;
        }

        int list;
        double item;
        double a;
        if(this.foodLeft > 0 && this.getHealth() <= 15 && this.getHealth() > 0) {
            for(list = 0; list < 12; ++list) {
                item = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                double gottam = this.y + 0.25D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                a = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntitySlimeChunkFX(this.world, item, gottam, a, Item.foodPorkchopRaw));
            }

			this.setHealthRaw(this.getHealth()+15);
            --this.foodLeft;
        }

        if(this.onGround) {
            this.climbTime = 10;
        }

        if(this.smokeTime > 0) {
            --this.smokeTime;
        }

        if(this.throwTime > 0) {
            --this.throwTime;
        }

        int i13;
        int i17;
        if(this.gooTime > 0) {
            this.xd = 0.0D;
            this.yd = 0.0D;
            this.zd = 0.0D;
            this.moveForward = 0.0F;
            this.moveStrafing = 0.0F;
            this.isJumping = false;
            this.moveSpeed = 0.0F;
            --this.gooTime;
            list = MathHelper.floor_double(this.x);
            i13 = MathHelper.floor_double(this.bb.minY - 1.0D);
            int stack = MathHelper.floor_double(this.z);
            i17 = this.world.getBlockId(list, i13, stack);
            if(i13 > 0 && i13 < 128 && (i17 == 0 || Block.blocksList[i17].getCollisionBoundingBoxFromPool(this.world, list, i13, stack) == null)) {
                this.gooTime = 0;
            }
        }

        if(this.throwTime > 6) {
            this.moveSpeed = -this.moveSpeed;
        }

        ++this.entCount;
        if(this.entityToAttack != null && !this.entityToAttack.isAlive()) {
            this.entityToAttack = null;
            this.setPathToEntity(null);
        } else if(this.entityToAttack != null && this.random.nextInt(25) == 0 && ((double)this.distanceTo(this.entityToAttack) > 8.0D || !this.canEntityBeSeen(this.entityToAttack))) {
            this.entityToAttack = null;
            this.setPathToEntity(null);
        }

        if(this.targetFollow != null && this.targetFollow.isAlive()) {
            this.targetFollow = null;
            this.setPathToEntity(null);
        } else if(this.targetFollow != null && this.random.nextInt(25) == 0 && ((double)this.distanceTo(this.targetFollow) > 8.0D || !this.canEntityBeSeen(this.targetFollow))) {
            this.targetFollow = null;
            this.setPathToEntity(null);
        }

        if(this.smokeTime <= 0 && this.entCount > 2 + this.random.nextInt(2) && this.getHealth() > 0) {
            this.entCount = 0;
            List list12 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(8.0D, 5.0D, 8.0D));

            EntityClayMan entityClayMan21;
            for(i13 = 0; i13 < list12.size(); ++i13) {
                Entity entity14 = (Entity)list12.get(i13);
                if(entity14 instanceof EntityClayMan && this.random.nextInt(3) == 0 && this.canEntityBeSeen(entity14)) {
                    EntityClayMan entityClayMan20 = (EntityClayMan)entity14;
                    if(entityClayMan20.getHealth() > 0 && entityClayMan20.clayTeam != this.clayTeam && this.clayTeam > 0 && this.logs <= 0) {
                        if(entityClayMan20.king) {
                            if(this.entityToAttack == null || !(this.entityToAttack instanceof EntityClayMan)) {
                                this.entityToAttack = entityClayMan20;
                                break;
                            }

                            entityClayMan21 = (EntityClayMan)this.entityToAttack;
                            if(!entityClayMan21.king) {
                                this.entityToAttack = entityClayMan20;
                                break;
                            }
                        } else if(this.entityToAttack == null) {
                            this.entityToAttack = entityClayMan20;
                            break;
                        }
                    } else if(entityClayMan20.getHealth() > 0 && this.targetFollow == null && this.entityToAttack == null && entityClayMan20.king && entityClayMan20.clayTeam == this.clayTeam && (double)this.distanceTo(entityClayMan20) > 3.0D) {
                        this.targetFollow = entityClayMan20;
                        break;
                    }
                } else if(this.entityToAttack == null && entity14 instanceof EntityMonster && this.canEntityBeSeen(entity14)) {
                    EntityMonster entityMob19 = (EntityMonster)entity14;
                    if(entityMob19.getTarget() != null) {
                        this.entityToAttack = entityMob19;
                        break;
                    }
                } else {
                    if(this.entityToAttack == null && this.targetFollow == null && !this.heavyCore && this.logs <= 0 && this.passenger == null && entity14 instanceof EntityDirtHorse && !entity14.isPassenger() && this.canEntityBeSeen(entity14)) {
                        this.targetFollow = entity14;
                        break;
                    }

                    if(this.entityToAttack == null && this.targetFollow == null && entity14 instanceof EntityBobber && this.canEntityBeSeen(entity14)) {
                        this.targetFollow = entity14;
                        break;
                    }

                    if(this.entityToAttack == null && (this.targetFollow == null || this.targetFollow instanceof EntityClayMan) && entity14 instanceof EntityItem && this.canEntityBeSeen(entity14)) {
                        EntityItem entityItem18 = (EntityItem)entity14;
                        if(entityItem18.item != null) {
                            ItemStack ec = entityItem18.item;
                            if(ec.stackSize > 0) {
                                if(this.weaponPoints <= 0 && ec.itemID == Item.stick.id) {
                                    this.targetFollow = entityItem18;
                                    break;
                                }

                                if(this.armorPoints <= 0 && ec.itemID == Item.leather.id) {
                                    this.targetFollow = entityItem18;
                                    break;
                                }

                                if(this.rocks <= 0 && ec.itemID == Block.gravel.id) {
                                    this.targetFollow = entityItem18;
                                    break;
                                }

                                if(!this.glowing && ec.itemID == Item.dustGlowstone.id) {
                                    this.targetFollow = entityItem18;
                                    break;
                                }

                                if(!this.king && ec.itemID == Item.ingotGold.id) {
                                    boolean z29 = false;
                                    List b = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(24.0D, 16.0D, 24.0D));

                                    for(int b1 = 0; b1 < b.size(); ++b1) {
                                        Entity c = (Entity)b.get(b1);
                                        if(c instanceof EntityClayMan) {
                                            EntityClayMan c1 = (EntityClayMan)c;
                                            if(c1.clayTeam == this.clayTeam && c1.king) {
                                                z29 = true;
                                                break;
                                            }
                                        }
                                    }

                                    if(!z29) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }
                                } else {
                                    if(!this.gunPowdered && ec.itemID == Item.sulphur.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.sugarTime <= 0 && ec.itemID == Item.dustSugar.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.foodLeft <= 0 && ec.getItem() != null && ec.getItem() instanceof ItemFood) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.resPoints <= 0 && ec.itemID == Item.clay.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.gooStock <= 0 && ec.itemID == Item.slimeball.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.smokeStock <= 0 && ec.itemID == Item.dustRedstone.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.weaponPoints > 0 && !this.stickSharp && ec.itemID == Item.flint.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.armorPoints > 0 && !this.armorPadded && ec.itemID == Block.wool.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(!this.heavyCore && !this.isPassenger() && ec.itemID == Item.ingotIron.id) {
                                        this.targetFollow = entityItem18;
                                        break;
                                    }

                                    if(this.resPoints > 0 && ec.getItem() != null && ec.getItem() instanceof ItemClayMan) {
                                        ItemClayMan itemClayMan28 = (ItemClayMan)ec.getItem();
                                        if(itemClayMan28.clayTeam == this.clayTeam) {
                                            this.targetFollow = entityItem18;
                                            break;
                                        }
                                    } else {
                                        if(ec.itemID == Item.dye.id && ec.getMetadata() == this.teamDye(this.clayTeam)) {
                                            this.targetFollow = entityItem18;
                                            break;
                                        }

                                        if(ec.itemID == Block.planksOak.id && !this.isPassenger() || ec.itemID == Block.planksOakPainted.id && !this.isPassenger()) {
                                            byte b25 = 0;
                                            if(this.logs < 20 && ec.stackSize >= 5) {
                                                b25 = 1;
                                            }

                                            if(b25 > 0) {
                                                this.targetFollow = entityItem18;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(this.entityToAttack != null) {
                if(this.strikeTime <= 0 && this.canEntityBeSeen(this.entityToAttack) && (double)this.distanceTo(this.entityToAttack) < (this.weaponPoints > 0 ? 1.0D : 0.7D) + (double)this.random.nextFloat() * 0.1D) {
                    if(this.hitTargetMakesDead(this.entityToAttack)) {
                        this.entityToAttack = null;
                        this.setPathToEntity(null);
                    }
                } else if(this.rocks > 0 && this.throwTime <= 0 && this.canEntityBeSeen(this.entityToAttack)) {
                    item = this.distanceTo(this.entityToAttack);
                    if(item >= 1.75D && item <= 7.0D) {
                        --this.rocks;
                        this.throwTime = 20;
                        this.throwRockAtEnemy(this.entityToAttack);
                    }
                }
            } else if(this.targetFollow != null) {
                if(!this.hasPath() || this.random.nextInt(10) == 0) {
                    this.setPathToEntity(this.world.getPathToEntity(this.targetFollow, this, 16.0F));
                }

                if(this.targetFollow instanceof EntityItem) {
                    EntityItem entityItem15 = (EntityItem)this.targetFollow;
                    if(entityItem15.item != null && this.canEntityBeSeen(entityItem15) && (double)this.distanceTo(entityItem15) < 0.75D) {
                        ItemStack itemStack16 = entityItem15.item;
                        if(itemStack16.stackSize > 0) {
                            if(itemStack16.itemID == Item.stick.id) {
                                this.weaponPoints = 15;
                                this.stickSharp = false;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.leather.id) {
                                this.armorPoints = 15;
                                this.armorPadded = false;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Block.gravel.id) {
                                this.rocks = 15;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.dustGlowstone.id) {
                                this.glowing = true;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.ingotGold.id) {
                                boolean z22 = false;
                                List list23 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(24.0D, 16.0D, 24.0D));

                                for(int i32 = 0; i32 < list23.size(); ++i32) {
                                    Entity entity27 = (Entity)list23.get(i32);
                                    if(entity27 instanceof EntityClayMan) {
                                        EntityClayMan entityClayMan33 = (EntityClayMan)entity27;
                                        if(entityClayMan33.clayTeam == this.clayTeam && entityClayMan33.king) {
                                            z22 = true;
                                            break;
                                        }
                                    }
                                }

                                if(!z22) {
                                    this.king = true;
                                    this.gotcha((EntityItem)this.targetFollow);
                                    entityItem15.remove();
                                } else {
                                    this.targetFollow = null;
                                    this.setPathToEntity(null);
                                }
                            } else if(itemStack16.itemID == Item.sulphur.id) {
                                this.gunPowdered = true;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.dustSugar.id) {
                                this.sugarTime = 1200;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.getItem() != null && itemStack16.getItem() instanceof ItemFood) {
                                this.foodLeft = 4;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.clay.id) {
                                this.resPoints = 4;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.dustRedstone.id) {
                                this.smokeStock = 2;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.slimeball.id) {
                                this.gooStock = 2;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else if(itemStack16.itemID == Item.ingotIron.id) {
                                this.heavyCore = true;
                                this.gotcha((EntityItem)this.targetFollow);
                            } else {
                                double d24;
                                double d30;
                                double d36;
                                if(itemStack16.itemID == Item.flint.id) {
                                    if(this.weaponPoints > 0) {
                                        this.stickSharp = true;

                                        for(i17 = 0; i17 < 4; ++i17) {
                                            d24 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            d30 = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.25D;
                                            d36 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntityDiggingFX(this.world, d24, d30, d36, 0.0D, 0.0D, 0.0D, Block.planksOak, 0));
                                        }

                                        this.world.playSoundAtEntity(null,this, "random.wood click", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                                    }

                                    this.targetFollow = null;
                                } else if(itemStack16.itemID == Block.wool.id) {
                                    if(this.armorPoints > 0) {
                                        this.armorPadded = true;

                                        for(i17 = 0; i17 < 4; ++i17) {
                                            d24 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            d30 = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.25D;
                                            d36 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntityDiggingFX(this.world, d24, d30, d36, 0.0D, 0.0D, 0.0D, Block.wool, 0));
                                        }

                                        this.world.playSoundAtEntity(null,this, "step.cloth", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                                    }

                                    this.targetFollow = null;
                                } else if(itemStack16.getItem() != null && itemStack16.getItem() instanceof ItemClayMan) {
                                    this.swingArm();
                                    this.world.playSoundAtEntity(entityItem15, this, "step.gravel", 0.8F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.9F);
                                    Item item34 = ClaySoldiers.greyDoll;
                                    if(this.clayTeam == 1) {
                                        item34 = ClaySoldiers.redDoll;
                                    } else if(this.clayTeam == 2) {
                                        item34 = ClaySoldiers.yellowDoll;
                                    } else if(this.clayTeam == 3) {
                                        item34 = ClaySoldiers.greenDoll;
                                    } else if(this.clayTeam == 4) {
                                        item34 = ClaySoldiers.blueDoll;
                                    } else if(this.clayTeam == 5) {
                                        item34 = ClaySoldiers.orangeDoll;
                                    } else if(this.clayTeam == 6) {
                                        item34 = ClaySoldiers.purpleDoll;
                                    }

                                    for(int i26 = 0; i26 < 18; ++i26) {
                                        a = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        double d35 = this.y + 0.25D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        double d37 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntitySlimeChunkFX(this.world, a, d35, d37, item34));
                                    }

                                    entityClayMan21 = new EntityClayMan(this.world, entityItem15.x, entityItem15.y, entityItem15.z, this.clayTeam);
                                    this.world.entityJoinedWorld(entityClayMan21);
                                    this.gotcha((EntityItem)this.targetFollow);
                                    --this.resPoints;
                                } else if(itemStack16.itemID == Block.planksOak.id && !this.isPassenger() || itemStack16.itemID == Block.planksOakPainted.id && !this.isPassenger()) {
                                    byte b31 = 0;
                                    if(this.logs < 20 && itemStack16.stackSize >= 5) {
                                        b31 = 1;
                                    }

                                    if(b31 > 0) {
                                        this.world.playSoundAtEntity(null, this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                                        if(b31 == 1) {
                                            this.logs += 5;
                                            if(entityItem15.item != null) {
                                                entityItem15.item.stackSize -= 5;
                                            }
                                        }

                                        if(entityItem15.item == null || entityItem15.item.stackSize <= 0) {
                                            entityItem15.remove();
                                        }
                                    }

                                    this.setPathToEntity(null);
                                    this.targetFollow = null;
                                } else if(itemStack16.itemID == Item.dye.id) {
                                    this.targetFollow = null;
                                }
                            }
                        }
                    }
                } else if(this.targetFollow instanceof EntityClayMan && (double)this.distanceTo(this.targetFollow) < 1.75D) {
                    this.targetFollow = null;
                } else if(this.targetFollow instanceof EntityBobber && (double)this.distanceTo(this.targetFollow) < 1.0D) {
                    this.targetFollow = null;
                } else if(this.targetFollow instanceof EntityDirtHorse && (double)this.distanceTo(this.targetFollow) < 0.75D && this.gooTime <= 0) {
                    if(!this.isPassenger() && this.targetFollow.passenger == null && !this.heavyCore && this.logs <= 0) {
                        this.startRiding(this.targetFollow);
                        this.world.playSoundAtEntity(null, this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                    }

                    this.targetFollow = null;
                }
            } else {
                this.updateBlockFinder();
                if(this.logs > 0 && this.random.nextInt(16) == 0) {
                    this.updateBuildings();
                }
            }
        }

        if(this.isSwinging) {
            this.prevSwingProgress += 0.15F;
            this.swingProgress += 0.15F;
            if(this.prevSwingProgress > 1.0F || this.swingProgress > 1.0F) {
                this.isSwinging = false;
                this.prevSwingProgress = 0.0F;
                this.swingProgress = 0.0F;
            }
        }

        if(this.isSwingingLeft) {
            this.swingLeft += 0.15F;
            if(this.swingLeft > 1.0F) {
                this.isSwingingLeft = false;
                this.swingLeft = 0.0F;
            }
        }
    }

    public void updateBlockFinder() {
        int x = MathHelper.floor_double(this.x);
        int y = MathHelper.floor_double(this.bb.minY);
        int z = MathHelper.floor_double(this.z);
        if(this.blockX != 0 && this.blockY != 0 && this.blockZ != 0 && !this.hasPath()) {
            Path i = this.world.getEntityPathToXYZ(this, this.blockX, this.blockY, this.blockZ, 16.0F);
            if(i != null && this.random.nextInt(5) != 0) {
                this.setPathToEntity(i);
            } else {
                this.blockX = 0;
                this.blockY = 0;
                this.blockZ = 0;
            }
        }

        int i11 = x;
        int j = y;
        int k = z;

        for(int q = 0; q < 16; ++q) {
            if(j >= 4 && j <= 124 && this.isAirySpace(i11, j, k) && !this.isAirySpace(i11, j - 1, k)) {
                int b = j - 1;
                if(this.checkSides(i11, b, k, i11, j, k, this.blocDist(i11, b, k, x, y, z), q == 0)) {
                    break;
                }

                ++b;
                int a = i11 - 1;
                if(this.checkSides(a, b, k, i11, j, k, this.blocDist(a, b, k, x, y, z), q == 0)) {
                    break;
                }

                a += 2;
                if(this.checkSides(a, b, k, i11, j, k, this.blocDist(a, b, k, x, y, z), q == 0)) {
                    break;
                }

                --a;
                int c = k - 1;
                if(this.checkSides(a, b, c, i11, j, k, this.blocDist(a, b, c, x, y, z), q == 0)) {
                    break;
                }

                c += 2;
                if(this.checkSides(a, b, c, i11, j, k, this.blocDist(a, b, c, x, y, z), q == 0)) {
                    break;
                }

                i11 = x + this.random.nextInt(6) - this.random.nextInt(6);
                j = y + this.random.nextInt(3) - this.random.nextInt(3);
                k = z + this.random.nextInt(6) - this.random.nextInt(6);
            }
        }

    }

    public double blocDist(int a, int b, int c, int x, int y, int z) {
        double i = (double)(a - x);
        double j = (double)(b - y);
        double k = (double)(c - z);
        return Math.sqrt(i * i + j * j + k * k);
    }

    public boolean isAirySpace(int x, int y, int z) {
        int p = this.world.getBlockId(x, y, z);
        return p == 0 || Block.blocksList[p].getCollisionBoundingBoxFromPool(this.world, x, y, z) == null;
    }

    public boolean checkSides(int a, int b, int c, int i, int j, int k, double dist, boolean first) {
        if(b > 4 && b < 124 && this.world.getBlockId(a, b, c) == Block.chestPlanksOak.id || b > 4 && b < 124 && this.world.getBlockId(a, b, c) == Block.chestPlanksOakPainted.id) {
            if(first && this.blockX == i && this.blockY == j && this.blockZ == k) {
                this.setPathToEntity(null);
                this.blockX = 0;
                this.blockY = 0;
                this.blockZ = 0;
                this.chestOperations(a, b, c, true);
                return true;
            }

            if(this.blockX == 0 && this.blockY == 0 && this.blockZ == 0 && this.chestOperations(a, b, c, false)) {
                Path emily = this.world.getEntityPathToXYZ(this, i, j, k, 16.0F);
                if(emily != null) {
                    this.setPathToEntity(emily);
                    this.blockX = i;
                    this.blockY = j;
                    this.blockZ = k;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean chestOperations(int x, int y, int z, boolean arrived) {
        TileEntity te = this.world.getBlockTileEntity(x, y, z);
        if(te != null && te instanceof TileEntityChest) {
            TileEntityChest chest = (TileEntityChest)te;

            for(int q = 0; q < chest.getSizeInventory(); ++q) {
                if(chest.getStackInSlot(q) != null) {
                    ItemStack stack = chest.getStackInSlot(q);
                    if(stack.stackSize > 0) {
                        if(this.weaponPoints <= 0 && stack.itemID == Item.stick.id) {
                            if(arrived) {
                                this.weaponPoints = 15;
                                this.stickSharp = false;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if(this.armorPoints <= 0 && stack.itemID == Item.leather.id) {
                            if(arrived) {
                                this.armorPoints = 15;
                                this.armorPadded = false;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if(this.rocks <= 0 && stack.itemID == Block.gravel.id) {
                            if(arrived) {
                                this.rocks = 15;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if(!this.glowing && stack.itemID == Item.dustGlowstone.id) {
                            if(arrived) {
                                this.glowing = true;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        int a;
                        if(!this.king && stack.itemID == Item.ingotGold.id) {
                            boolean z21 = false;
                            List list24 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(24.0D, 16.0D, 24.0D));

                            for(a = 0; a < list24.size(); ++a) {
                                Entity entity23 = (Entity)list24.get(a);
                                if(entity23 instanceof EntityClayMan) {
                                    EntityClayMan entityClayMan25 = (EntityClayMan)entity23;
                                    if(entityClayMan25.clayTeam == this.clayTeam && entityClayMan25.king) {
                                        z21 = true;
                                        break;
                                    }
                                }
                            }

                            if(!z21) {
                                if(arrived) {
                                    this.king = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }
                        } else {
                            if(!this.gunPowdered && stack.itemID == Item.sulphur.id) {
                                if(arrived) {
                                    this.gunPowdered = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.sugarTime <= 0 && stack.itemID == Item.dustSugar.id) {
                                if(arrived) {
                                    this.sugarTime = 1200;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.foodLeft <= 0 && stack.getItem() != null && stack.getItem() instanceof ItemFood) {
                                if(arrived) {
                                    this.foodLeft = 4;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.resPoints <= 0 && stack.itemID == Item.clay.id) {
                                if(arrived) {
                                    this.resPoints = 4;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.gooStock <= 0 && stack.itemID == Item.slimeball.id) {
                                if(arrived) {
                                    this.gooStock = 2;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.smokeStock <= 0 && stack.itemID == Item.dustRedstone.id) {
                                if(arrived) {
                                    this.smokeStock = 2;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(stack.itemID == Block.planksOak.id && !this.isPassenger() || stack.itemID == Block.planksOakPainted.id && !this.isPassenger()) {
                                byte b19 = 0;
                                if(this.logs < 20 && stack.stackSize >= 5) {
                                    b19 = 1;
                                }

                                if(arrived && b19 > 0) {
                                    this.world.playSoundAtEntity(null, this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                                    if(b19 == 1) {
                                        this.logs += 5;
                                        chest.decrStackSize(q, 5);
                                    }
                                }

                                return b19 > 0 || arrived;
                            }

                            double a1;
                            double b1;
                            int i18;
                            double d22;
                            if(this.weaponPoints > 0 && !this.stickSharp && stack.itemID == Item.flint.id) {
                                if(arrived) {
                                    this.stickSharp = true;

                                    for(i18 = 0; i18 < 4; ++i18) {
                                        d22 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        a1 = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.25D;
                                        b1 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntityDiggingFX(this.world, d22, a1, b1, 0.0D, 0.0D, 0.0D, Block.planksOak, 0));
                                    }

                                    this.world.playSoundAtEntity(null, this, "random.wood click", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                                }

                                return true;
                            }

                            if(this.armorPoints > 0 && !this.armorPadded && stack.itemID == Block.wool.id) {
                                if(arrived) {
                                    this.armorPadded = true;

                                    for(i18 = 0; i18 < 4; ++i18) {
                                        d22 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        a1 = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.25D;
                                        b1 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntityDiggingFX(this.world, d22, a1, b1, 0.0D, 0.0D, 0.0D, Block.wool, 0));
                                    }

                                    this.world.playSoundAtEntity(null, this, "step.cloth", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
                                }

                                return true;
                            }

                            if(!this.heavyCore && this.vehicle == null && stack.itemID == Item.ingotIron.id) {
                                if(arrived) {
                                    this.heavyCore = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if(this.resPoints > 0 && stack.getItem() != null && stack.getItem() instanceof ItemClayMan) {
                                ItemClayMan ic = (ItemClayMan)stack.getItem();
                                if(ic.clayTeam == this.clayTeam) {
                                    if(arrived) {
                                        this.swingArm();
                                        Item item1 = ClaySoldiers.greyDoll;
                                        if(this.clayTeam == 1) {
                                            item1 = ClaySoldiers.redDoll;
                                        } else if(this.clayTeam == 2) {
                                            item1 = ClaySoldiers.yellowDoll;
                                        } else if(this.clayTeam == 3) {
                                            item1 = ClaySoldiers.greenDoll;
                                        } else if(this.clayTeam == 4) {
                                            item1 = ClaySoldiers.blueDoll;
                                        } else if(this.clayTeam == 5) {
                                            item1 = ClaySoldiers.orangeDoll;
                                        } else if(this.clayTeam == 6) {
                                            item1 = ClaySoldiers.purpleDoll;
                                        }

                                        for(a = 0; a < 18; ++a) {
                                            a1 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            b1 = this.y + 0.25D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            double c1 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                            Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntitySlimeChunkFX(this.world, a1, b1, c1, item1));
                                        }

                                        double d20 = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        double b = this.y + (double)this.random.nextFloat() * 0.125D;
                                        double c = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                                        EntityClayMan ec = new EntityClayMan(this.world, d20, b, c, this.clayTeam);
                                        this.world.entityJoinedWorld(ec);
                                        this.gotcha(chest, q);
                                        --this.resPoints;
                                    }

                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public void updateBuildings() {
        int x = MathHelper.floor_double(this.x);
        int y = MathHelper.floor_double(this.bb.minY);
        int z = MathHelper.floor_double(this.z);
        if(y >= 4 && y <= 120) {
            byte broad = 2;
            byte high = 3;
            if(this.logs == 20) {
                broad = 3;
                high = 4;
            }

            boolean flag = false;

            for(int gee = -broad; gee < broad + 1 && !flag; ++gee) {
                for(int b = -1; b < high + 1 && !flag; ++b) {
                    for(int list = -broad; list < broad + 1 && !flag; ++list) {
                        if(b == -1) {
                            if(this.isAirySpace(x + gee, y + b, z + list)) {
                                flag = true;
                            }
                        } else if(!this.isAirySpace(x + gee, y + b, z + list) || this.world.getBlockMaterial(x + gee, y + b, z + list) == Material.water) {
                            flag = true;
                        }
                    }
                }
            }

            if(!flag) {
                double d10 = (double)broad;
                List list11 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(d10, d10, d10));
                if(list11.size() > 0) {
                    flag = true;
                }
            }

            if(!flag) {
                if(this.logs == 20 && this.random.nextInt(2) == 0) {
                    this.buildHouseThree();
                } else if(this.logs >= 10 && this.random.nextInt(3) > 0) {
                    this.buildHouseTwo();
                } else if(this.logs >= 5) {
                    this.buildHouseOne();
                }
            }

        }
    }

    public void dropLogs() {
        this.spawnAtLocation(Block.wool.id, this.logs);
        this.logs = 0;
    }

    public void buildHouseOne() {
        int x = MathHelper.floor_double(this.x + 0.5D);
        int y = MathHelper.floor_double(this.bb.minY);
        int z = MathHelper.floor_double(this.z + 0.5D);
        int direction = this.random.nextInt(4);

        for(int j = 0; j < 3; ++j) {
            int b = j;

            for(int i = -1; i < 3; ++i) {
                for(int k = -1; k < 2; ++k) {
                    int a = i;
                    int c = k;
                    if(direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if(direction / 2 == 0) {
                        int swap = a;
                        a = c;
                        c = swap;
                    }

                    if(j == 0) {
                        if(i != -1 && i != 2 && k != -1) {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                        } else {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                        }
                    } else if(j == 1) {
                        if(i == -1) {
                            this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                            this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                        } else if(i == 2) {
                            this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                            this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2) % 4);
                        } else if(k == -1) {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                        } else {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                        }
                    } else if(i == 0) {
                        this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                        this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                    } else if(i == 1) {
                        this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                        this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2) % 4);
                    } else {
                        this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                    }
                }
            }
        }

        this.world.playSoundAtEntity(null, this, "random.woodclick", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.world.playSoundAtEntity(null, this, "step.wood", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.logs -= 5;
    }

    public void buildHouseTwo() {
        int x = MathHelper.floor_double(this.x);
        int y = MathHelper.floor_double(this.bb.minY);
        int z = MathHelper.floor_double(this.z);
        int direction = this.random.nextInt(4);

        for(int j = 0; j < 3; ++j) {
            int b = j;

            for(int i = -2; i < 3; ++i) {
                for(int k = -2; k < 3; ++k) {
                    int a = i;
                    int c = k;
                    if(direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if(direction / 2 == 0) {
                        int swap = a;
                        a = c;
                        c = swap;
                    }

                    if(i != -2 && i != 2 || k != -2 && k != 2) {
                        if(j != 0 && j != 1) {
                            if(j == 2) {
                                if(i == -2) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if(i == 2) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2) % 4);
                                } else if(k == -2) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if(k == 2) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, direction % 4);
                                } else {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                                }
                            }
                        } else if(i != -2 && i != 2 && k != -2 && (k != 2 || i == 0 && j != 1)) {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                        } else {
                            this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                        }
                    }
                }
            }
        }

        this.world.playSoundAtEntity(null, this, "random.woodclick", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.world.playSoundAtEntity(null, this, "step.wood", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.logs -= 10;
    }

    public void buildHouseThree() {
        int x = MathHelper.floor_double(this.x);
        int y = MathHelper.floor_double(this.bb.minY);
        int z = MathHelper.floor_double(this.z);
        int direction = this.random.nextInt(4);

        for(int j = 0; j < 4; ++j) {
            int b = j;

            for(int i = -3; i < 4; ++i) {
                for(int k = -2; k < 3; ++k) {
                    int a = i;
                    int c = k;
                    if(direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if(direction / 2 == 0) {
                        int chest = a;
                        a = c;
                        c = chest;
                    }

                    if(i != -3 && i != 3 || k != -2 && k != 2) {
                        if(j < 3) {
                            if(i != -3 && i != 3 && k != -2 && (k != 2 || i == 0 && j <= 0)) {
                                if(i == -2 && j == 0 && k == 0) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.chestPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2) % 4);
                                    TileEntityChest tileEntityChest12 = (TileEntityChest)this.world.getBlockTileEntity(x + a, y + b, z + c);
                                    tileEntityChest12.setInventorySlotContents(0, new ItemStack(Item.stick, 16, 0));
                                } else if(i == 0 && j == 0 && k == -1) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if(i == 1 && j == 1 && k == -1) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if(i == 2 && j == 1 && k == -1) {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                                } else if(i == 2 && j == 2 && k == 0) {
                                    this.world.setBlock(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                    this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, (direction + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if(i == 0 && j == 2 && k == -1) {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                                } else if(i == 1 && j == 2 && k == -1) {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                                } else if(i == 2 && j == 2 && k == -1) {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                                } else if(j == 2) {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                                } else {
                                    this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                                }
                            } else {
                                this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                            }
                        } else if(j == 3) {
                            if(i != -3 && i != 3 && k != -2 && (k != 2 || i == 0 && j <= 0)) {
                                this.world.setBlockWithNotify(x + a, y + b, z + c, 0);
                            } else if(i != -2 && i != 0 && i != 2 && k != 0) {
                                this.world.setBlockWithNotify(x + a, y + b, z + c, Block.stairsPlanksOak.id);
                                this.world.setBlockMetadataWithNotify(x + a, y + b, z + c, 2);
                            } else {
                                this.world.setBlockWithNotify(x + a, y + b, z + c, Block.planksOak.id);
                            }
                        }
                    }
                }
            }
        }

        this.world.playSoundAtEntity(null, this, "random.wood click", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.world.playSoundAtEntity(null, this, "step.wood", 1.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.logs -= 20;
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

    public void swingArm() {
        if(!this.isSwinging) {
            this.isSwinging = true;
            this.prevSwingProgress = 0.0F;
            this.swingProgress = 0.0F;
        }

    }

    public void swingLeftArm() {
        if(!this.isSwingingLeft) {
            this.isSwingingLeft = true;
            this.swingLeft = 0.01F;
        }

    }

    public boolean hitTargetMakesDead(Entity e) {
        this.strikeTime = 12;
        this.swingArm();
        int power = this.weaponPoints > 0 ? 3 + this.random.nextInt(2) + (this.stickSharp ? 1 : 0) : 2;
        if(this.weaponPoints > 0) {
            --this.weaponPoints;
        }

        boolean flag = e.hurt(this, power, null);
        if(flag && e instanceof EntityLiving) {
            EntityLiving el = (EntityLiving)e;
            if(el.getHealth() <= 0) {
                return true;
            }
        }

        return false;
    }

    public void throwRockAtEnemy(Entity entity) {
        double d = entity.x - this.x;
        double d1 = entity.z - this.z;
        EntityGravelChunk entitygravelchunk = new EntityGravelChunk(this.world, this, this.clayTeam);
        entitygravelchunk.y += 0.3999999761581421D;
        double d2 = entity.y + (double)entity.getHeadHeight() - 0.10000000298023223D - entitygravelchunk.y;
        float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
        this.world.entityJoinedWorld(entitygravelchunk);
        entitygravelchunk.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12.0F);
        this.attackTime = 30;
        this.moveForward = -this.moveForward;
        this.yRot = (float)(Math.atan2(d1, d) * 180.0D / (double)(float)Math.PI) - 90.0F;
        this.hasAttacked = true;
        this.swingLeftArm();
    }

    public void gotcha(EntityItem item) {
        this.world.playSoundAtEntity(null, item, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        if(item.item != null) {
            --item.item.stackSize;
            if(item.item.stackSize <= 0) {
                item.remove();
            }
        } else {
            item.remove();
        }

        this.targetFollow = null;
        this.setPathToEntity(null);
    }

    public void gotcha(TileEntityChest chest, int q) {
        this.world.playSoundAtEntity(null, this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        chest.decrStackSize(q, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbttagcompound) {
        super.addAdditionalSaveData(nbttagcompound);
        nbttagcompound.putShort("ClayTeam", (short)this.clayTeam);
        nbttagcompound.putShort("WeaponPoints", (short)this.weaponPoints);
        nbttagcompound.putShort("ArmorPoints", (short)this.armorPoints);
        nbttagcompound.putShort("FoodLeft", (short)this.foodLeft);
        nbttagcompound.putShort("SugarTime", (short)this.sugarTime);
        nbttagcompound.putShort("ResPoints", (short)this.resPoints);
        nbttagcompound.putShort("StrikeTime", (short)this.strikeTime);
        nbttagcompound.putShort("ClimbTime", (short)this.climbTime);
        nbttagcompound.putShort("GooTime", (short)this.gooTime);
        nbttagcompound.putShort("SmokeTime", (short)this.smokeTime);
        nbttagcompound.putShort("GooStock", (short)this.gooStock);
        nbttagcompound.putShort("SmokeStock", (short)this.smokeStock);
        nbttagcompound.putShort("Logs", (short)this.logs);
        nbttagcompound.putShort("Rocks", (short)this.rocks);
        nbttagcompound.putBoolean("Gunpowdered", this.gunPowdered);
        nbttagcompound.putBoolean("King", this.king);
        nbttagcompound.putBoolean("Glowing", this.glowing);
        nbttagcompound.putBoolean("StickSharp", this.stickSharp);
        nbttagcompound.putBoolean("ArmorPadded", this.armorPadded);
        nbttagcompound.putBoolean("HeavyCore", this.heavyCore);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag nbttagcompound) {
        super.readAdditionalSaveData(nbttagcompound);
        this.clayTeam = nbttagcompound.getShort("ClayTeam");
        this.weaponPoints = nbttagcompound.getShort("WeaponPoints");
        this.armorPoints = nbttagcompound.getShort("ArmorPoints");
        this.foodLeft = nbttagcompound.getShort("FoodLeft");
        this.sugarTime = nbttagcompound.getShort("SugarTime");
        this.resPoints = nbttagcompound.getShort("ResPoints");
        this.strikeTime = nbttagcompound.getShort("StrikeTime");
        this.climbTime = nbttagcompound.getShort("ClimbTime");
        this.gooTime = nbttagcompound.getShort("GooTime");
        this.smokeTime = nbttagcompound.getShort("SmokeTime");
        this.gooStock = nbttagcompound.getShort("GooStock");
        this.smokeStock = nbttagcompound.getShort("SmokeStock");
        this.logs = nbttagcompound.getShort("Logs");
        this.rocks = nbttagcompound.getShort("Rocks");
        this.gunPowdered = nbttagcompound.getBoolean("Gunpowdered");
        this.king = nbttagcompound.getBoolean("King");
        this.glowing = nbttagcompound.getBoolean("Glowing");
        this.stickSharp = nbttagcompound.getBoolean("StickSharp");
        this.armorPadded = nbttagcompound.getBoolean("ArmorPadded");
        this.heavyCore = nbttagcompound.getBoolean("HeavyCore");
    }

    @Override
    protected String getHurtSound() {
        this.world.playSoundAtEntity(null, this, "random.hurt", 0.6F, 1.0F * (this.random.nextFloat() * 0.2F + 1.6F));
        this.world.playSoundAtEntity(null, this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    @Override
    protected String getDeathSound() {
        this.world.playSoundAtEntity(null, this, "random.hurt", 0.6F, 1.0F * (this.random.nextFloat() * 0.2F + 1.6F));
        return "step.gravel";
    }

    @Override
    protected void jump() {
        if(this.gooTime <= 0) {
            if(this.sugarTime > 0) {
                this.yd = 0.375D;
            } else {
                this.yd = 0.275D;
            }

        }
    }


    public boolean isOnLadder() {
        if(this.logs <= 0 && this.horizontalCollision && !this.onGround && this.climbTime > 0) {
            if(this.climbTime != 10) {
                this.throwTime = 5;
                --this.climbTime;
                return true;
            }

            if(this.yd < 0.05D) {
                --this.climbTime;
                this.throwTime = 5;
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean hasStick() {
        return this.weaponPoints > 0;
    }

    public boolean hasArmor() {
        return this.armorPoints > 0;
    }

    public boolean hasSpecks() {
        return this.gunPowdered;
    }

    public boolean hasCrown() {
        return this.king;
    }

    public boolean isGlowing() {
        return this.glowing;
    }

    public boolean isSharpened() {
        return this.stickSharp;
    }

    public boolean isPadded() {
        return this.armorPadded;
    }

    public boolean isGooey() {
        return this.gooTime > 0;
    }

    public boolean hasLogs() {
        return this.logs > 0;
    }

    public float armLeft() {
        return this.swingLeft;
    }

    public boolean hasRocks() {
        return this.rocks > 0 && this.throwTime <= 0 && this.logs <= 0;
    }

    @Override
    public void dropFewItems() {
        if(!this.gunPowdered) {
            Item item1 = ClaySoldiers.greyDoll;
            if(this.clayTeam == 1) {
                item1 = ClaySoldiers.redDoll;
            } else if(this.clayTeam == 2) {
                item1 = ClaySoldiers.yellowDoll;
            } else if(this.clayTeam == 3) {
                item1 = ClaySoldiers.greenDoll;
            } else if(this.clayTeam == 4) {
                item1 = ClaySoldiers.blueDoll;
            } else if(this.clayTeam == 5) {
                item1 = ClaySoldiers.orangeDoll;
            } else if(this.clayTeam == 6) {
                item1 = ClaySoldiers.purpleDoll;
            }

            this.spawnAtLocation(item1.id, 1);
            if(this.resPoints > 0) {
                this.spawnAtLocation(Item.clay.id, 1);
            }

            if(this.weaponPoints > 7 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.stick.id, 1);
            }

            if(this.armorPoints > 7 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.leather.id, 1);
            }

            if(this.rocks > 7 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Block.gravel.id, 1);
            }

            if(this.smokeStock > 1 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.dustRedstone.id, 1);
            }

            if(this.gooStock > 1 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.slimeball.id, 1);
            }

            if(this.smokeStock > 1 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.dustRedstone.id, 1);
            }

            if(this.gooStock > 1 && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.slimeball.id, 1);
            }

            if(this.glowing && this.random.nextInt(2) == 0) {
                this.spawnAtLocation(Item.dustGlowstone.id, 1);
            }

            if(this.king) {
                this.spawnAtLocation(Item.ingotGold.id, 1);
            }

            if(this.heavyCore) {
                this.spawnAtLocation(Item.ingotIron.id, 1);
            }

            if(this.logs > 0) {
                this.dropLogs();
            }
        }

    }


    public boolean hurt(Entity e, int i, DamageType type) {
        if(this.vehicle != null && i < 100 && this.random.nextInt(2) == 0) {
            if (vehicle instanceof Entity) {
                return ((Entity) this.vehicle).hurt(e, i, (DamageType) null);
            }
            return false;
        } else {
            if(e != null && e instanceof EntityClayMan) {
                EntityClayMan fred = (EntityClayMan)e;
                if(fred.clayTeam == this.clayTeam) {
                    return false;
                }

                if(this.logs > 0) {
                    this.dropLogs();
                }

                if(this.smokeTime <= 0) {
                    this.entityToAttack = e;
                }

                if(this.armorPoints > 0) {
                    i /= 2;
                    if(this.armorPadded) {
                        --i;
                    }

                    --this.armorPoints;
                    if(i < 0) {
                        i = 0;
                    }
                }

                if(this.getHealth() - i > 0) {
                    int item1;
                    double q;
                    double b;
                    double c;
                    if((fred.smokeStock <= 0 || this.smokeTime <= 0 || this.random.nextInt(2) == 0) && fred.gooStock > 0 && this.gooTime <= 0 && this.onGround) {
                        --fred.gooStock;
                        this.gooTime = 150;
                        this.world.playSoundAtEntity(null, this, "mob.slimeattack", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));

                        for(item1 = 0; item1 < 4; ++item1) {
                            q = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                            b = this.bb.minY + 0.125D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                            c = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                            this.world.spawnParticle("slime", q, b, c, 0.0D, 0.1D, 0.0D);
                        }

                        this.xd = 0.0D;
                        this.yd = 0.0D;
                        this.zd = 0.0D;
                        this.moveForward = 0.0F;
                        this.moveStrafing = 0.0F;
                        this.isJumping = false;
                    } else if(fred.smokeStock > 0 && this.smokeTime <= 0) {
                        --fred.smokeStock;
                        this.smokeTime = 100;
                        this.world.playSoundAtEntity(null, this, "random.fizz", 0.75F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));

                        for(item1 = 0; item1 < 8; ++item1) {
                            q = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                            b = this.bb.minY + 0.25D + (double)this.random.nextFloat() * 0.25D;
                            c = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                            this.world.spawnParticle("reddust", q, b, c, 0.0D, 0.1D, 0.0D);
                        }

                        this.targetFollow = null;
                        this.entityToAttack = null;
                        this.setPathToEntity(null);
                    }
                }
            } else {
                i = 20;
                if(e instanceof EntityBobber) {
                    return false;
                }
            }

            boolean z12 = super.hurt(e, i, (DamageType)null);
            if(z12 && this.getHealth() <= 0) {
                Item item13 = ClaySoldiers.greyDoll;
                if(this.clayTeam == 1) {
                    item13 = ClaySoldiers.redDoll;
                } else if(this.clayTeam == 2) {
                    item13 = ClaySoldiers.yellowDoll;
                } else if(this.clayTeam == 3) {
                    item13 = ClaySoldiers.greenDoll;
                } else if(this.clayTeam == 4) {
                    item13 = ClaySoldiers.blueDoll;
                } else if(this.clayTeam == 5) {
                    item13 = ClaySoldiers.orangeDoll;
                } else if(this.clayTeam == 6) {
                    item13 = ClaySoldiers.purpleDoll;
                }

                for(int i14 = 0; i14 < 24; ++i14) {
                    double a = this.x + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                    double b1 = this.y + 0.25D + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                    double c1 = this.z + (double)(this.random.nextFloat() - this.random.nextFloat()) * 0.125D;
                    Minecraft.getMinecraft(this).effectRenderer.addEffect(new EntitySlimeChunkFX(this.world, a, b1, c1, item13));
                }

                this.removed = true;
                if(e != null && e instanceof EntityPlayer) {
                    this.killedByPlayer = e;
                }

                if(this.gunPowdered) {
                    this.world.createExplosion((Entity)null, this.x, this.y, this.z, 1.0F);
                }
            }

            return z12;
        }
    }

    public void push(double d, double d1, double d2) {
        if(this.gooTime <= 0) {
            this.xd += d;
            this.yd += d1;
            this.zd += d2;
        }
    }

    @Override
    public void knockBack(Entity entity, int i, double d, double d1) {
        if(this.gooTime <= 0) {
            super.knockBack(entity, i, d, d1);
            if(entity != null && entity instanceof EntityClayMan) {
                EntityClayMan ec = (EntityClayMan)entity;
                if((!ec.heavyCore || !this.heavyCore) && (ec.heavyCore || this.heavyCore)) {
                    if(!ec.heavyCore && this.heavyCore) {
                        this.xd *= 0.2D;
                        this.yd *= 0.4D;
                        this.zd *= 0.2D;
                    } else {
                        this.xd *= 1.5D;
                        this.zd *= 1.5D;
                    }
                } else {
                    this.xd *= 0.6D;
                    this.yd *= 0.75D;
                    this.zd *= 0.6D;
                }
            } else if(entity != null && entity instanceof EntityGravelChunk) {
                this.xd *= 0.6D;
                this.yd *= 0.75D;
                this.zd *= 0.6D;
            }

        }
    }
}
