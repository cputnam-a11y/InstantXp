package instantxp.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import instantxp.utils.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ExperienceBottleItem.class)
public abstract class ExperienceBottleItemMixin {
    @WrapOperation(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileEntity;spawnWithVelocity(Lnet/minecraft/entity/projectile/ProjectileEntity$ProjectileCreator;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;FFF)Lnet/minecraft/entity/projectile/ProjectileEntity;"
            )
    )
    private <T extends ProjectileEntity> T cancelSpawnProjectile(ProjectileEntity.ProjectileCreator<T> creator, ServerWorld world, ItemStack projectileStack, LivingEntity shooter, float roll, float power, float divergence, Operation<T> original, World normalWorld, PlayerEntity user, Hand hand) {
        Utils.applyExperience(user);
        return null;
    }

    @ModifyExpressionValue(
            method = "use",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/sound/SoundEvents;ENTITY_EXPERIENCE_BOTTLE_THROW:Lnet/minecraft/sound/SoundEvent;"
            )
    )
    private SoundEvent modifySoundEvent(SoundEvent original) {
        return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
    }
}
