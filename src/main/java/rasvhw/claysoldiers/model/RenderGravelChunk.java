package rasvhw.claysoldiers.model;

import rasvhw.claysoldiers.entities.EntityGravelChunk;
import net.minecraft.client.render.RenderBlocks;

import java.util.Random;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGravelChunk extends EntityRenderer<EntityGravelChunk> {
    private RenderBlocks renderBlocks = new RenderBlocks();
    private Random random = new Random();

    public RenderGravelChunk() {
        this.shadowSize = 0.0F;
        this.shadowOpacity = 0.75F;
    }

    @Override

    public void doRender(EntityGravelChunk entitygravelchunk, double d, double d1, double d2, float f, float f1) {
        this.random.setSeed(187L);
        ItemStack itemstack = new ItemStack(Block.gravel.id, 1, 0);
        GL11.glPushMatrix();
        float f2 = MathHelper.sin(((float)entitygravelchunk.entityAge + f1) / 10.0F);
        float f3 = ((float)entitygravelchunk.entityAge + f1) / 20.0F;
        byte byte0 = 1;
        GL11.glTranslatef((float)d, (float)d1 + f2, (float)d2);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        if(itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderBlockPass())) {
            GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
            this.loadTexture("/terrain.png");
            float f4 = 0.1F;
            GL11.glScalef(f4, f4, f4);

            for(int j = 0; j < byte0; ++j) {
                GL11.glPushMatrix();
                if(j > 0) {
                    float f5 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    float f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    float f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    GL11.glTranslatef(f5, f7, f9);
                }

                this.renderBlocks.renderBlockOnInventory(Block.blocksList[itemstack.itemID], itemstack.getMetadata(), entitygravelchunk.getBrightness(f1));
                GL11.glPopMatrix();
            }
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void renderQuad(Tessellator tessellator, int i, int j, int k, int l, int i1) {
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(i1);
        tessellator.addVertex((double)(i + 0), (double)(j + 0), 0.0D);
        tessellator.addVertex((double)(i + 0), (double)(j + l), 0.0D);
        tessellator.addVertex((double)(i + k), (double)(j + l), 0.0D);
        tessellator.addVertex((double)(i + k), (double)(j + 0), 0.0D);
        tessellator.draw();
    }

    public void renderTexturedQuad(int i, int j, int k, int l, int i1, int j1) {
        float f = 0.0F;
        float f1 = 0.00390625F;
        float f2 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(i + 0), (double)(j + j1), (double)f, (double)((float)(k + 0) * f1), (double)((float)(l + j1) * f2));
        tessellator.addVertexWithUV((double)(i + i1), (double)(j + j1), (double)f, (double)((float)(k + i1) * f1), (double)((float)(l + j1) * f2));
        tessellator.addVertexWithUV((double)(i + i1), (double)(j + 0), (double)f, (double)((float)(k + i1) * f1), (double)((float)(l + 0) * f2));
        tessellator.addVertexWithUV((double)(i + 0), (double)(j + 0), (double)f, (double)((float)(k + 0) * f1), (double)((float)(l + 0) * f2));
        tessellator.draw();
    }
}
