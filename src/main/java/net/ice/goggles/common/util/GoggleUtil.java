 package net.ice.goggles.common.util;

import net.ice.goggles.Goggles;
import net.ice.goggles.common.packet.PayloadHandler;
import net.ice.goggles.common.packet.UsedVisonTrack;
import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GoggleUtil {
    private static final CompoundTag trackTag = new CompoundTag();

    public static EnumTrackID getVisualTrackIDFromItemStack(ItemStack helmet) {
        if (helmet.hasTag()) {
            Integer integer = helmet.getTag().getInt("trackID");
            for (EnumTrackID trackID : EnumTrackID.values()) {
                if (integer.equals(trackID.ID)) {
                    return trackID;
                }
            }
            return EnumTrackID.DEFAULT;
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

        trackTag.putInt("trackID", trackID.ID);

        if(trackID == EnumTrackID.DEFAULT) {
            ShaderUtil.loadAndCloseUnsafe(trackID.PATH, true);
        }

        helmet.setTag(trackTag);
        ShaderUtil.loadAndCloseUnsafe(trackID.PATH, false);
    }

    public static void removeVisualTrack(ItemStack stack, Player player) {
        if (stack.hasTag()) {
            player.addItem(getVisualTrackIDFromItemStack(stack).ITEM.get().getDefaultInstance());
            stack.removeTagKey("trackID");
        }
    }

    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (event.getSlot().isArmor()) {
            if(event.getEntity() instanceof ServerPlayer) {

                if(event.getFrom().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    if(event.getFrom().hasTag()) {
                        PayloadHandler.sendToPlayer(new UsedVisonTrack(0), (ServerPlayer) event.getEntity());
                        return;
                    }
                }
                if(event.getTo().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    EnumTrackID trackID = GoggleUtil.getVisualTrackIDFromItemStack(event.getTo());
                    PayloadHandler.sendToPlayer(new UsedVisonTrack(trackID.ID), (ServerPlayer) event.getEntity());
                }
            }
        }
    }
}
