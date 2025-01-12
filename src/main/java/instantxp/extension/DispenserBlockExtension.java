package instantxp.extension;

import net.minecraft.block.dispenser.DispenserBehavior;

import java.util.Optional;

public class DispenserBlockExtension {
    private static final ThreadLocal<Optional<DispenserBehavior>> NEXT_REPLACEMENT = ThreadLocal.withInitial(Optional::empty);

    public static DispenserBehavior getNextOrPass(DispenserBehavior defaultBehavior) {
        var behavior = NEXT_REPLACEMENT.get().orElse(defaultBehavior);
        NEXT_REPLACEMENT.remove();
        return behavior;
    }

    public static void queueNext(DispenserBehavior replacement) {
        NEXT_REPLACEMENT.set(Optional.of(replacement));
    }
}
