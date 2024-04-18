package rasvhw.claysoldiers.mixin;

import net.minecraft.core.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntityLiving.class, remap = false)
public interface EntityAccessor {
    @Accessor
    boolean getIsJumping();
    @Accessor
    float getMoveForward();
    @Accessor
    float getMoveStrafing();

}
