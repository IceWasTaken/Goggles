package net.ice.goggles.common.packet;

import net.ice.goggles.common.util.EnumTrackID;
import net.ice.goggles.common.util.GoggleUtil;
import net.ice.goggles.common.util.ShaderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PayloadHandler {
    public static class ClientPayloadHandler {

        public static void handleDataOnNetwork(final UsedVisonTrack data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                        if(data.trackID() == 0) {
                            Minecraft.getInstance().gameRenderer.shutdownEffect();
                        }
                        EnumTrackID trackID = GoggleUtil.getVisualTrackIDFromInt(data.trackID());
                        ShaderUtil.load(trackID.PATH, false);
                    })
                    .exceptionally(e -> {
                        context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                        return null;
                    });
        }
    }
    public static class ServerPayloadHandler {
        public static void handleDataOnNetwork(final UsedVisonTrack data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                    })
                    .exceptionally(e -> {
                        context.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                        return null;
                    });
        }
    }
}
