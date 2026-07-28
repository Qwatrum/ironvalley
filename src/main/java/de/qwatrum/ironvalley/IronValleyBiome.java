package de.qwatrum.ironvalley;

import net.minecraft.resources.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class IronValleyBiome implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        ModBiomes.registerBiomes();

        Regions.register(new OverworldRegion(Identifier.fromNamespaceAndPath(Ironvalley.MOD_ID, "overworld_region"), 1));

        SurfaceRuleManager.addSurfaceRules(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                Ironvalley.MOD_ID,
                ModSurfaceRules.genRules()
        );

    }
}
