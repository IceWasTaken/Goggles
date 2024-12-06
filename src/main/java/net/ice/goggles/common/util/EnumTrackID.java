package net.ice.goggles.common.util;

import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public enum EnumTrackID {
    DEFAULT(0, "null", ItemRegistry.ITEM_TRACK_NULL, Component.translatable("track.default"), null),

    ANTIALIAS(1,"antialias.json", ItemRegistry.ITEM_TRACK_ANTIALIAS, Component.translatable("track.antialias"), ChatFormatting.WHITE),
    ART(2, "art.json", ItemRegistry.ITEM_TRACK_ART, Component.translatable("track.art"), ChatFormatting.WHITE),
    BITS(3, "bits.json", ItemRegistry.ITEM_TRACK_BITS, Component.translatable("track.bits"), ChatFormatting.WHITE),
    BLOBS(4, "blobs.json", ItemRegistry.ITEM_TRACK_BLOBS, Component.translatable("track.blobs"), ChatFormatting.WHITE),
    BLOBS2(5, "blobs2.json", ItemRegistry.ITEM_TRACK_BLOBS2, Component.translatable("track.blobs2"), ChatFormatting.WHITE),
    BLUR(6, "blur.json", ItemRegistry.ITEM_TRACK_BLUR, Component.translatable("track.blur"), ChatFormatting.WHITE),
    BUMPY(7, "bumpy.json", ItemRegistry.ITEM_TRACK_BUMPY, Component.translatable("track.bumpy"), ChatFormatting.WHITE),
    COLOR_CONVOLVE(8, "color_convolve.json", ItemRegistry.ITEM_TRACK_COLOR_CONVOLVE, Component.translatable("track.convolve"), ChatFormatting.WHITE),
    CREEPER(9, "creeper.json", ItemRegistry.ITEM_TRACK_CREEPER, Component.translatable("track.creeper"), ChatFormatting.GREEN),
    DECONVERGE(10, "deconverge.json", ItemRegistry.ITEM_TRACK_DECONVERGE, Component.translatable("track.deconverge"), ChatFormatting.WHITE),
    DESATURATE(11, "desaturate.json", ItemRegistry.ITEM_TRACK_DESATURATE, Component.translatable("track.desaturate"), ChatFormatting.WHITE),
    FLIP(13, "flip.json", ItemRegistry.ITEM_TRACK_FLIP, Component.translatable("track.flip"), ChatFormatting.WHITE),
    FXAA(14, "fxaa.json", ItemRegistry.ITEM_TRACK_FXAA, Component.translatable("track.fxaa"), ChatFormatting.WHITE),
    GREEN(15, "green.json", ItemRegistry.ITEM_TRACK_GREEEN, Component.translatable("track.green"), ChatFormatting.GREEN),
    INVERT(16, "invert.json", ItemRegistry.ITEM_TRACK_INVERT, Component.translatable("track.invert"), ChatFormatting.DARK_AQUA),
    NOTCH(17, "notch.json", ItemRegistry.ITEM_TRACK_NOTCH, Component.translatable("track.notch"), ChatFormatting.WHITE),
    NTSC(18, "ntsc.json", ItemRegistry.ITEM_TRACK_NTSC, Component.translatable("track.ntsc"), ChatFormatting.GRAY),
    PENCIL(20, "pencil.json", ItemRegistry.ITEM_TRACK_PENCIL, Component.translatable("track.pencil"), ChatFormatting.LIGHT_PURPLE),
    PHOSPHOR(21, "phosphor.json", ItemRegistry.ITEM_TRACK_PHOSPHOR, Component.translatable("track.phosphor"), ChatFormatting.YELLOW),
    SCAN_PINCUSHION(22, "scan_pincushion.json", ItemRegistry.ITEM_TRACK_SCAN_PINCUSHION, Component.translatable("track.pincushion"), ChatFormatting.WHITE),
    SOBEL(23, "sobel.json", ItemRegistry.ITEM_TRACK_SOBEL, Component.translatable("track.sobel"), ChatFormatting.WHITE),
    SPIDER(24, "spider.json", ItemRegistry.ITEM_TRACK_SPIDER, Component.translatable("track.spider"), ChatFormatting.RED),
    WOBBLE(25, "wobble.json", ItemRegistry.ITEM_TRACK_WOBBLE, Component.translatable("track.wobble"), ChatFormatting.WHITE);

    public final Integer ID;
    public final String PATH;
    public final RegistryObject<Item> ITEM;
    public final MutableComponent TKEY;
    public final ChatFormatting FORMAT;

    EnumTrackID(Integer id, String path, RegistryObject<Item> item, MutableComponent key, ChatFormatting style) {
        this.ID = id;
        this.PATH = path;
        this.ITEM = item;
        this.TKEY = key;
        this.FORMAT = style;
    }
}
