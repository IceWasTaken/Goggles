package net.ice.goggles.common.packet;

import io.netty.buffer.ByteBuf;
import net.ice.goggles.Goggles;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UsedVisonTrack(int trackID) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<UsedVisonTrack> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Goggles.MODID, "trackpacket"));

    public static final StreamCodec<ByteBuf, UsedVisonTrack> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            UsedVisonTrack::trackID,
            UsedVisonTrack::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
