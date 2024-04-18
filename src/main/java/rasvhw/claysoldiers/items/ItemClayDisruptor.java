package rasvhw.claysoldiers.items;

import rasvhw.claysoldiers.ClaySoldiers;
import rasvhw.claysoldiers.entities.EntityClayMan;
import rasvhw.claysoldiers.entities.EntityDirtHorse;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import java.util.List;

public class ItemClayDisruptor extends Item {
    public ItemClayDisruptor(String name,int id) {
        super(name,id);
        this.setMaxDamage(16);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if(ClaySoldiers.waveTime <= 0) {
            itemstack.damageItem(1, entityplayer);
            ClaySoldiers.waveTime = 150;
            world.playSoundAtEntity(entityplayer,entityplayer, "portal.trigger", 1.5F, itemRand.nextFloat() * 0.2F + 1.4F);
            entityplayer.timeInPortal = 1.5F;
            entityplayer.prevTimeInPortal = 1.5F;
            List list1 = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.bb.expand(16.0D, 16.0D, 16.0D));

            int x;
            for(x = 0; x < list1.size(); ++x) {
                Entity y = (Entity)list1.get(x);
                if(y instanceof EntityClayMan && y.isAlive() && ((EntityLiving)y).getHealth() > 0) {
                    ((EntityClayMan) y).hurt(entityplayer, 100, null);
                } else if(y instanceof EntityDirtHorse && y.isAlive() && ((EntityLiving)y).getHealth() > 0) {
                    ((EntityDirtHorse) y).hurt(entityplayer, 100, null);
                }
            }

            x = MathHelper.floor_double(entityplayer.x);
            int i23 = MathHelper.floor_double(entityplayer.bb.minY);
            int z = MathHelper.floor_double(entityplayer.z);

            int i;
            double distance;
            double d;
            double f;
            for(i = -12; i < 13; ++i) {
                for(int angle = -12; angle < 13; ++angle) {
                    for(int k = -12; k < 13; ++k) {
                        if(angle + i23 > 0 && angle + i23 < 127 && world.getBlockId(x + i, i23 + angle, z + k) == Block.blockClay.id) {
                            distance = (double)i;
                            d = (double)angle;
                            f = (double)k;
                            if(Math.sqrt(distance * distance + d * d + f * f) <= 12.0D) {
                                this.blockCrush(world, x + i, i23 + angle, z + k);
                            }
                        }
                    }
                }
            }

            for(i = 0; i < 128; ++i) {
                double d24 = (double)i / 3.0D;
                distance = 0.5D + (double)i / 6.0D;
                d = Math.sin(d24) * 0.25D;
                f = Math.cos(d24) * 0.25D;
                double a = entityplayer.x + d * distance;
                double b = entityplayer.bb.minY + 0.5D;
                double c = entityplayer.z + f * distance;
                world.spawnParticle("portal", a, b, c, d, 0.0D, f);
            }
        }

        return itemstack;
    }

    public void blockCrush(World world, int x, int y, int z) {
        int a = world.getBlockId(x, y, z);
        int b = world.getBlockMetadata(x, y, z);
        if(a != 0) {
            Minecraft.getMinecraft(this).effectRenderer.addBlockDestroyEffects(x, y, z, a, b);
            Block.blocksList[a].onBlockRemoved(world, x, y, z, world.getBlockMetadata(x,y,z));
            Block.blocksList[a].dropBlockWithCause(world, EnumDropCause.WORLD,x, y, z, b, null);
            world.setBlockWithNotify(x, y, z, 0);
        }
    }
}
