package de.qwatrum.ironvalley;

import de.qwatrum.ironvalley.Ironvalley;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.logging.Logger;

public class ModBiomes {
    public static final ResourceKey<Biome> IRON_VALLEY = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(Ironvalley.MOD_ID, "iron_valley")
    );

    public static void registerBiomes() {
    }
}
