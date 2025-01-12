package instantxp.dispenserbehavior;

import instantxp.utils.Utils;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;

import java.util.Optional;

public class ExperienceBottleDispenserBehavior implements DispenserBehavior {
    @Override
    public ItemStack dispense(BlockPointer pointer, ItemStack originalStack) {
        Optional<PlayerEntity> player = Utils.getClosestPlayer(
                pointer.world(),
                pointer.centerPos()
        );
        if (player.isPresent()) {
            var stack = originalStack.copy();
            Utils.applyExperience(player.get());
            stack.decrement(1);
            return stack;
        }
        return originalStack;
    }
}
