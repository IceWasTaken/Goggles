package net.ice.goggles.common.util;

import net.ice.goggles.common.packet.UsedVisonTrack;
import net.ice.goggles.registry.DataComponentRegistry;
import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class GoggleUtil {
    public static EnumTrackID getVisualTrackIDFromItemStack(ItemStack helmet) {
        for(EnumTrackID trackID : EnumTrackID.values()) {
            Integer integer = helmet.get(DataComponentRegistry.TRACK_ID);
            if(integer != null) {
                if(integer.equals(trackID.ID)) {
                    return trackID;
                }
            }
        }
        return EnumTrackID.DEFAULT;
    }

    public static EnumTrackID getVisualTrackIDFromInt(int ID) {
        for(EnumTrackID trackID : EnumTrackID.values()) {
            if(ID == trackID.ID) {
                return trackID;
            }
        }
        return EnumTrackID.DEFAULT;
    }

    public static void insertVisualTrack(ItemStack helmet, EnumTrackID trackID) {
        if(trackID == EnumTrackID.DEFAULT) {
            ShaderUtil.loadAndCloseUnsafe(trackID.PATH, true);
        }

        helmet.set(DataComponentRegistry.TRACK_ID, trackID.ID);
        ShaderUtil.loadAndCloseUnsafe(trackID.PATH, false);
    }

    public static void removeVisualTrack(ItemStack stack, Player player) {
        if (stack.has(DataComponentRegistry.TRACK_ID)) {
            player.addItem(getVisualTrackIDFromItemStack(stack).ITEM.get().getDefaultInstance());
            stack.remove(DataComponentRegistry.TRACK_ID);
        }
    }

    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (event.getSlot().isArmor()) {
            if(event.getEntity() instanceof ServerPlayer) {

                if(event.getFrom().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    if(event.getFrom().has(DataComponentRegistry.TRACK_ID)) {
                        PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(), new UsedVisonTrack(0));
                        return;
                    }
                }
                if(event.getTo().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    if(event.getFrom().has(DataComponentRegistry.TRACK_ID)) {
                        EnumTrackID trackID = GoggleUtil.getVisualTrackIDFromItemStack(event.getTo());
                        PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(), new UsedVisonTrack(trackID.ID));
                    }
                }
            }
        }
    }
}
