package moriyashiine.bewitchment.client.integration.rei;

import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import moriyashiine.bewitchment.client.integration.rei.category.AthameDropCategory;
import moriyashiine.bewitchment.client.integration.rei.category.DistillingCategory;
import moriyashiine.bewitchment.client.integration.rei.category.RitualCategory;
import moriyashiine.bewitchment.client.integration.rei.category.SpinningCategory;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.registry.BWRecipeTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BewitchmentREIPlugin implements REIPluginV0 {
	private static final Identifier ID = new Identifier(Bewitchment.MODID, "rei");
	
	@Override
	public Identifier getPluginIdentifier() {
		return ID;
	}
	
	@Override
	public void registerPluginCategories(RecipeHelper recipeHelper) {
		recipeHelper.registerCategory(new AthameDropCategory());
		recipeHelper.registerCategory(new RitualCategory());
		recipeHelper.registerCategory(new DistillingCategory());
		recipeHelper.registerCategory(new SpinningCategory());
	}
	
	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		World world = MinecraftClient.getInstance().world;
		if (world != null) {
			world.getRecipeManager().listAllOfType(BWRecipeTypes.athame_drop_type).forEach(recipe -> recipeHelper.registerDisplay(new AthameDropCategory.Display(recipe)));
			world.getRecipeManager().listAllOfType(BWRecipeTypes.ritual_type).forEach(recipe -> recipeHelper.registerDisplay(new RitualCategory.Display(recipe)));
			world.getRecipeManager().listAllOfType(BWRecipeTypes.distilling_type).forEach(recipe -> recipeHelper.registerDisplay(new DistillingCategory.Display(recipe)));
			world.getRecipeManager().listAllOfType(BWRecipeTypes.spinning_type).forEach(recipe -> recipeHelper.registerDisplay(new SpinningCategory.Display(recipe)));
		}
	}
	
	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerWorkingStations(AthameDropCategory.ID, AthameDropCategory.LOGO);
		recipeHelper.registerWorkingStations(RitualCategory.ID, RitualCategory.LOGO);
		recipeHelper.registerWorkingStations(DistillingCategory.ID, DistillingCategory.LOGO);
		recipeHelper.registerWorkingStations(SpinningCategory.ID, SpinningCategory.LOGO);
	}
}
