package net.ice.goggles.common.util;

import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public enum EnumTrackID {
    DEFAULT(0, "null", ItemRegistry.ITEM_TRACK_NULL, GenericUtil.makeFormattedTooltip("track.default"), null),

    ANTIALIAS(1,"antialias.json", ItemRegistry.ITEM_TRACK_ANTIALIAS, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    ART(2, "art.json", ItemRegistry.ITEM_TRACK_ART, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    BITS(3, "bits.json", ItemRegistry.ITEM_TRACK_BITS, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    BLOBS(4, "blobs.json", ItemRegistry.ITEM_TRACK_BLOBS, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    BLOBS2(5, "blobs2.json", ItemRegistry.ITEM_TRACK_BLOBS2, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    BLUR(6, "blur.json", ItemRegistry.ITEM_TRACK_BLUR, GenericUtil.makeFormattedTooltip("track.blur"), ChatFormatting.WHITE),
    BUMPY(7, "bumpy.json", ItemRegistry.ITEM_TRACK_BUMPY, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    COLOR_CONVOLVE(8, "color_convolve.json", ItemRegistry.ITEM_TRACK_COLOR_CONVOLVE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    CREEPER(9, "creeper.json", ItemRegistry.ITEM_TRACK_CREEPER, GenericUtil.makeFormattedTooltip("track.creeper"), ChatFormatting.GREEN),
    DECONVERGE(10, "deconverge.json", ItemRegistry.ITEM_TRACK_DECONVERGE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    DESATURATE(11, "desaturate.json", ItemRegistry.ITEM_TRACK_DESATURATE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    ENTITY_OUTLINE(12, "entity_outline.json", ItemRegistry.ITEM_TRACK_ENTITY_OUTLINE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    FLIP(13, "filp.json", ItemRegistry.ITEM_TRACK_FLIP, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    FXAA(14, "fxaa.json", ItemRegistry.ITEM_TRACK_FXAA, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    GREEN(15, "green.json", ItemRegistry.ITEM_TRACK_GREEEN, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.GREEN),
    INVERT(16, "invert.json", ItemRegistry.ITEM_TRACK_INVERT, GenericUtil.makeFormattedTooltip("track.negative"), ChatFormatting.DARK_AQUA),
    NOTCH(17, "notch.json", ItemRegistry.ITEM_TRACK_NOTCH, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    NTSC(18, "ntsc.json", ItemRegistry.ITEM_TRACK_NTSC, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.GRAY),
    OUTLINE(19, "outline.json", ItemRegistry.ITEM_TRACK_OUTLINE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    PENIL(20, "pencil.json", ItemRegistry.ITEM_TRACK_PENCIL, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.LIGHT_PURPLE),
    PHOSPHOR(21, "phosphor.json", ItemRegistry.ITEM_TRACK_PHOSPHOR, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.YELLOW),
    SCAN_PINCUSHION(22, "scan_pincushion.json", ItemRegistry.ITEM_TRACK_SCAN_PINCUSHION, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    SOBEL(23, "sobel.json", ItemRegistry.ITEM_TRACK_SOBEL, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE),
    SPIDER(24, "spider.json", ItemRegistry.ITEM_TRACK_SPIDER, GenericUtil.makeFormattedTooltip("track.spider"), ChatFormatting.RED),
    WOBBLE(25, "wobble.json", ItemRegistry.ITEM_TRACK_WOBBLE, GenericUtil.makeFormattedTooltip("track.default"), ChatFormatting.WHITE);

    public final Integer ID;
    public final String PATH;
    public final DeferredItem<Item> ITEM;
    public final String TKEY;
    public final ChatFormatting FORMAT;

    EnumTrackID(Integer id, String path, DeferredItem<Item> item, String key, ChatFormatting style) {
        this.ID = id;
        this.PATH = path;
        this.ITEM = item;
        this.TKEY = key;
        this.FORMAT = style;
    }
}
