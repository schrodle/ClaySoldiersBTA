package rasvhw.claysoldiers.model;

import rasvhw.claysoldiers.entities.EntityClayMan;
import rasvhw.claysoldiers.entities.EntityDirtHorse;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderDirtHorse extends LivingRenderer<EntityDirtHorse> {
    public RenderDirtHorse(ModelBiped model, float f) {
        super(model, f);
    }

    @Override
    protected void preRenderCallback(EntityDirtHorse entityDirtHorse, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public void doRender(EntityDirtHorse entityDirtHorse, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.doRender(entityDirtHorse, d, d1, d2, f, f1);
    }
}
