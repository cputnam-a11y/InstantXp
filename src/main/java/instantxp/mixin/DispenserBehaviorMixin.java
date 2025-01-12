package instantxp.mixin;

import instantxp.dispenserbehavior.ExperienceBottleDispenserBehavior;
import instantxp.extension.DispenserBlockExtension;
import net.minecraft.block.dispenser.DispenserBehavior;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(DispenserBehavior.class)
public interface DispenserBehaviorMixin {
    @Inject(
            method = "registerDefaults",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/DispenserBlock;registerProjectileBehavior(Lnet/minecraft/item/ItemConvertible;)V",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/item/Items;EXPERIENCE_BOTTLE:Lnet/minecraft/item/Item;"
                    )
            )
    )
    private static void registerExperienceBottleDispenserBehavior(CallbackInfo ci) {
        DispenserBlockExtension.queueNext(new ExperienceBottleDispenserBehavior());
    }

}
