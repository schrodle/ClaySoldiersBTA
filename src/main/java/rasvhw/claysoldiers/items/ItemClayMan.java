package rasvhw.claysoldiers.items;

import rasvhw.claysoldiers.entities.EntityClayMan;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

public class ItemClayMan extends Item {
    public int clayTeam;

    public ItemClayMan(String name, int id, int j) {
        super(name, id);
        this.clayTeam = j;
        this.maxStackSize = 16;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        if (world.getBlockId(blockX, blockY, blockZ) != Block.blockSnow.id) {
            blockX += side.getOffsetX();
            blockY += side.getOffsetY();
            blockZ += side.getOffsetZ();

            if(!world.isAirBlock(blockX, blockY, blockZ)) {
                return false;
            }
        }

        boolean jack = false;
        int p = world.getBlockId(blockX, blockY, blockZ);
        if(p == 0 || Block.blocksList[p].getCollisionBoundingBoxFromPool(world, blockX, blockY, blockZ) == null) {
            while(itemstack.stackSize > 0) {
                double a = (double)blockX + 0.25D + (double)itemRand.nextFloat() * 0.5D;
                double b = (double)blockY + 0.5D;
                double c = (double)blockZ + 0.25D + (double)itemRand.nextFloat() * 0.5D;
                EntityClayMan ec = new EntityClayMan(world, a, b, c, this.clayTeam);
                world.entityJoinedWorld(ec);
                jack = true;
                --itemstack.stackSize;
            }
        }

        return jack;
    }
}
