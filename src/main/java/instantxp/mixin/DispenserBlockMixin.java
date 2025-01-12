package instantxp.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import instantxp.extension.DispenserBlockExtension;
import instantxp.utils.Conversions;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;
import java.util.function.BiFunction;

@Debug(export = true)
@SuppressWarnings("unchecked")
@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @WrapOperation(
            method = "registerBehavior",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static <K, V> V wrapRegisterBehavior(Map<K, V> instance, K k, V v, Operation<V> original) {
        return (V) wrapAdd(
                Conversions.toItem(k),
                Conversions.toBehavior(v),
                (provider, behavior) -> Conversions.toNullableBehavior(
                        original.call(
                                instance,
                                provider,
                                behavior
                        )
                )
        );
    }

    @WrapOperation(
            method = "registerProjectileBehavior",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static <K, V> V wrapRegisterProjectileBehavior(Map<K, V> instance, K k, V v, Operation<V> original) {
        return (V) wrapAdd(
                Conversions.toItem(k),
                Conversions.toBehavior(v),
                (provider, behavior) -> Conversions.toNullableBehavior(
                        original.call(
                                instance,
                                provider,
                                behavior
                        )
                )
        );
    }

    @Unique
    private static <Adder extends BiFunction<Item, DispenserBehavior, @Nullable DispenserBehavior>>
    @Nullable DispenserBehavior wrapAdd(
            Item item, DispenserBehavior behavior, Adder adder
    ) {
        return adder.apply(item, DispenserBlockExtension.getNextOrPass(behavior));
    }
}
