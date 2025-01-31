package moriyashiine.bewitchment.common.curse;

import moriyashiine.bewitchment.api.component.MagicComponent;
import moriyashiine.bewitchment.api.registry.Curse;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ApathyCurse extends Curse {
	public ApathyCurse(Type type) {
		super(type);
	}
	
	@Override
	public void tick(LivingEntity target) {
		if (target instanceof PlayerEntity player) {
			MagicComponent.maybeGet(player).ifPresent(magicComponent -> {
				if (magicComponent.getMagic() > 0) {
					magicComponent.setMagic(0);
				}
			});
		}
	}
}
