package net.ice.goggles.common.packet;

import net.ice.goggles.common.util.GoggleUtil;
import net.ice.goggles.common.util.ShaderUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class UsedVisonTrack {

    private final int trackID;

    public UsedVisonTrack(int trackID) {
        this.trackID = trackID;
    }

    public UsedVisonTrack(FriendlyByteBuf byteBuf) {
        trackID = byteBuf.readByte();
    }
    public void toBytes(FriendlyByteBuf byteBuf) {
        byteBuf.writeByte(trackID);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() ->
                ShaderUtil.load(GoggleUtil.getVisualTrackIDFromInt(trackID).PATH, GoggleUtil.getVisualTrackIDFromInt(trackID).ID == 0)
        );
    }
}
