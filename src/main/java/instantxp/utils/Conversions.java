package instantxp.utils;

import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;

public class Conversions {
    public static Item toItem(Object o) {
        if (o instanceof Item) {
            return (Item) o;
        }
        throw new AssertionError("Expected Item, got " + o);
    }

    public static DispenserBehavior toBehavior(Object o) {
        if (o instanceof DispenserBehavior behavior) {
            return behavior;
        }
        throw new AssertionError("Expected DispenserBehavior, got " + o);
    }

    public static DispenserBehavior toNullableBehavior(Object o) {
        if (o instanceof DispenserBehavior || o == null) {
            return (DispenserBehavior) o;
        }
        throw new AssertionError("Expected @Nullable DispenserBehavior, got " + o);
    }
}
