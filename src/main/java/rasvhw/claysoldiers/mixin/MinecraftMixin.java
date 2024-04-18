package rasvhw.claysoldiers.mixin;

import rasvhw.claysoldiers.ClaySoldiers;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;runTick()V", shift = At.Shift.AFTER))
    private void addWaveTime(CallbackInfo ci) {
        ClaySoldiers.updateWaveTime();
    }
}
