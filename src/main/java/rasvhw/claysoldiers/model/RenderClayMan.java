package rasvhw.claysoldiers.model;

import rasvhw.claysoldiers.entities.EntityClayMan;

import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBiped;
import org.lwjgl.opengl.GL11;

public class RenderClayMan extends LivingRenderer<EntityClayMan> {
    public ModelClayMan mc1;

    public RenderClayMan(ModelBiped model, float f) {
        super(model, f);
        this.mc1 = (ModelClayMan)model;
    }

    @Override
    protected void preRenderCallback(EntityClayMan entityClayMan, float f) {
        this.mc1.hasStick = entityClayMan.hasStick();
        this.mc1.hasSpecks = entityClayMan.isGunPowdered();
        this.mc1.hasArmor = entityClayMan.hasArmor();
        this.mc1.hasCrown = entityClayMan.isKing();
        this.mc1.isPadded = entityClayMan.isArmorPadded();
        this.mc1.isSharpened = entityClayMan.isStickSharp();
        this.mc1.isGooey = entityClayMan.isGooey();
        this.mc1.hasLogs = entityClayMan.hasLogs();
        this.mc1.hasRocks = entityClayMan.hasRocks();
        this.mc1.armLeft = entityClayMan.armLeft();
        boolean flag = false;
        if(entityClayMan.isOnLadder()) {
            entityClayMan.setClimbTime(entityClayMan.getClimbTime() + 1);
            flag = true;
        }

        this.mc1.isClimbing = flag;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        if(entityClayMan.isGlowing()) {
            if(entityClayMan.hurtTime <= 0 && entityClayMan.deathTime <= 0) {
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            } else {
                GL11.glColor3f(1.0F, 0.5F, 0.5F);
            }
        }

    }

    @Override
    public void render(EntityClayMan entityClayMan, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.render(entityClayMan, d, d1, d2, f, f1);
    }
}
