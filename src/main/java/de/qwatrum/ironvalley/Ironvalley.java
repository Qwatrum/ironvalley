package de.qwatrum.ironvalley;

import de.qwatrum.ironvalley.block.ModBlocks;
import de.qwatrum.ironvalley.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancements.triggers.LightningStrikeTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.logging.Logger;


public class Ironvalley implements ModInitializer {
    public static final String MOD_ID = "ironvalley";
    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (player.getActiveItem().getItem() == ModItems.SIEVE && level.getBlockState(hitResult.getBlockPos()).getBlock() == ModBlocks.IRON_DIRT) {
                level.setBlock(hitResult.getBlockPos(), Blocks.DIRT.defaultBlockState(),0);
                ItemStack item = Item.byId(Item.getId(Items.IRON_NUGGET)).getDefaultInstance();
                Block.popResource(level, hitResult.getBlockPos(), item);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });


        ServerEntityEvents.ENTITY_LOAD.register(((entity, level) -> {
            if (entity instanceof LightningBolt lightningBolt) {
                BlockPos pos = lightningBolt.blockPosition();
                pos = pos.offset(0, -1, 0);

                if (level.getBlockState(pos).getBlock() == ModBlocks.IRON_DIRT) {
                    Player targetPlayer = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 100, true);
                    if (targetPlayer != null) {
                        targetPlayer.setHealth(targetPlayer.getHealth()-damageFromLightning(pos, targetPlayer.getOnPos()));
                    }

                }
            }
        }));
    }

    private float damageFromLightning(BlockPos lightning, BlockPos playerPos) {
        int xl = lightning.getX();
        int yl = lightning.getY();
        int zl = lightning.getZ();

        int dx = playerPos.getX() -xl;
        int dy = playerPos.getY() -yl;
        int dz = playerPos.getZ() -zl;

        double d = Math.sqrt((dx*dx) + (dy*dy) + (dz*dz));
        return (float) (-0.2*d+20);
    }
}
