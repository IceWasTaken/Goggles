package net.ice.goggles.common.util;

import net.ice.goggles.registry.DataComponentRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GoggleUtil {
    public static EnumTrackID getVisualTrack(ItemStack helmet) {
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

    public static void insertVisualTrack(ItemStack helmet, EnumTrackID trackID) {
        if(trackID == EnumTrackID.DEFAULT) {
            ShaderUtil.loadAndCloseUnsafe(trackID.PATH, true);
        }

        helmet.set(DataComponentRegistry.TRACK_ID, trackID.ID);
        ShaderUtil.loadAndCloseUnsafe(trackID.PATH, false);
    }

    public static void removeVisualTrack(ItemStack stack, Player player) {
        if (stack.has(DataComponentRegistry.TRACK_ID)) {
            player.addItem(getVisualTrack(stack).ITEM.get().getDefaultInstance());
            stack.remove(DataComponentRegistry.TRACK_ID);
        }
    }
}
