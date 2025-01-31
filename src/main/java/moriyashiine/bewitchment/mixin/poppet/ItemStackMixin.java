package moriyashiine.bewitchment.mixin.poppet;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.misc.PoppetData;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow
	public abstract void setDamage(int damage);
	
	@Shadow
	public abstract int getDamage();
	
	@Shadow
	public abstract int getMaxDamage();
	
	@Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"), cancellable = true)
	private <T extends LivingEntity> void damage(int amount, T entity, Consumer<T> breakCallback, CallbackInfo callbackInfo) {
		if (getDamage() == getMaxDamage()) {
			PoppetData poppetData = BewitchmentAPI.getPoppet(entity.world, BWObjects.MENDING_POPPET, entity);
			if (!poppetData.stack.isEmpty()) {
				boolean sync = false;
				if (poppetData.stack.damage(entity instanceof PlayerEntity && BewitchmentAPI.getFamiliar((PlayerEntity) entity) == EntityType.WOLF && entity.getRandom().nextBoolean() ? 0 : 1, entity.getRandom(), null) && poppetData.stack.getDamage() == poppetData.stack.getMaxDamage()) {
					poppetData.stack.decrement(1);
					sync = true;
				}
				poppetData.maybeSync(entity.world, sync);
				setDamage(0);
				callbackInfo.cancel();
			}
		}
	}
}
