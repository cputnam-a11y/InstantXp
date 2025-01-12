package instantxp.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

public class Utils {
    @Unique
    public static Optional<PlayerEntity> getClosestPlayer(World world, Vec3d pos) {
        double d = -1.0;
        PlayerEntity closest = null;
        for (PlayerEntity playerEntity : world.getPlayers()) {
            double e = playerEntity.squaredDistanceTo(pos);
            if (d == -1.0 || e < d) {
                d = e;
                closest = playerEntity;
            }
        }

        return Optional.ofNullable(closest);
    }

    public static void applyExperience(PlayerEntity player) {
        int i = 3 + player.getWorld().random.nextInt(5) + player.getWorld().random.nextInt(5);
        player.addExperience(i);
    }
}
