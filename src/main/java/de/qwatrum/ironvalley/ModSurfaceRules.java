package de.qwatrum.ironvalley;

import de.qwatrum.ironvalley.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import terrablender.api.SurfaceRuleManager;

import java.util.Optional;

public class ModSurfaceRules {

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRuleManager.RuleBuilder genRules() {

        return biomes -> {

            SurfaceRules.RuleSource surfaceBlock = makeStateRule(ModBlocks.IRON_DIRT);
            SurfaceRules.RuleSource underSurfaceBlock = makeStateRule(Blocks.DIRT);


            return SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                            SurfaceRules.isBiome(biomes, ModBiomes.IRON_VALLEY),
                            SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(
                                            SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                            surfaceBlock
                                    ),
                                    SurfaceRules.ifTrue(
                                            SurfaceRules.stoneDepthCheck(5, false, 0, CaveSurface.FLOOR),
                                            underSurfaceBlock
                                    )
                            )
                    )
            );

        };



    }
}
