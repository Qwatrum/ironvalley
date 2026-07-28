package de.qwatrum.ironvalley;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class OverworldRegion extends Region {
    public OverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiome(mapper,
                ParameterUtils.Temperature.HOT.parameter(),
                ParameterUtils.Humidity.DRY.parameter(),
                ParameterUtils.Continentalness.FAR_INLAND.parameter(),
                ParameterUtils.Erosion.EROSION_4.parameter(),
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING.parameter(),
                ParameterUtils.Depth.SURFACE.parameter(),
                0.3F,
                ModBiomes.IRON_VALLEY);
    }
}
