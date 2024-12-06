package net.ice.goggles.registry;

import net.ice.goggles.Goggles;
import net.ice.goggles.common.item.ItemVisionAugmentDevice;
import net.ice.goggles.common.item.ItemVisualTrack;
import net.ice.goggles.common.util.EnumTrackID;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Goggles.MODID);
    public static List<RegistryObject<? extends Item>> ITEM_ARRAY = new ArrayList<>();

    public static final RegistryObject<Item> ITEM_VISION_DEVICE = ITEMS.register("visual_augmentation_device", ItemVisionAugmentDevice.Helmet::new);


    public static final RegistryObject<Item> ITEM_TRACK_EMPTY = register("visual_track_empty", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> ITEM_TRACK_NULL = ITEMS.register("visual_track_null", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.DEFAULT));
    public static final RegistryObject<Item> ITEM_TRACK_ANTIALIAS = register("visual_track_antialias", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.ANTIALIAS));
    public static final RegistryObject<Item> ITEM_TRACK_ART = register("visual_track_art", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.ART));
    public static final RegistryObject<Item> ITEM_TRACK_BITS = register("visual_track_bits", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.BITS));
    public static final RegistryObject<Item> ITEM_TRACK_BLOBS = register("visual_track_blobs", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.BLOBS));
    public static final RegistryObject<Item> ITEM_TRACK_BLOBS2 = register("visual_track_blobs_two", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.BLOBS2));
    public static final RegistryObject<Item> ITEM_TRACK_BLUR = register("visual_track_blur", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.BLUR));
    public static final RegistryObject<Item> ITEM_TRACK_BUMPY = register("visual_track_bumpy", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.BUMPY));
    public static final RegistryObject<Item> ITEM_TRACK_COLOR_CONVOLVE = register("visual_track_color_convolve", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.COLOR_CONVOLVE));
    public static final RegistryObject<Item> ITEM_TRACK_CREEPER = register("visual_track_creeper", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.CREEPER));
    public static final RegistryObject<Item> ITEM_TRACK_DECONVERGE = register("visual_track_deconverge", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.DECONVERGE));
    public static final RegistryObject<Item> ITEM_TRACK_DESATURATE = register("visual_track_desaturate", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.DESATURATE));
    public static final RegistryObject<Item> ITEM_TRACK_FLIP = register("visual_track_flip", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.FLIP));
    public static final RegistryObject<Item> ITEM_TRACK_FXAA = register("visual_track_fxaa", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.FXAA));
    public static final RegistryObject<Item> ITEM_TRACK_GREEEN = register("visual_track_green", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.GREEN));
    public static final RegistryObject<Item> ITEM_TRACK_INVERT = register("visual_track_invert", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.INVERT));
    public static final RegistryObject<Item> ITEM_TRACK_NOTCH = register("visual_track_notch", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.NOTCH));
    public static final RegistryObject<Item> ITEM_TRACK_NTSC = register("visual_track_ntsc", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.NTSC));
    public static final RegistryObject<Item> ITEM_TRACK_PENCIL = register("visual_track_pencil", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.PENCIL));
    public static final RegistryObject<Item> ITEM_TRACK_PHOSPHOR = register("visual_track_phosphor", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.PHOSPHOR));
    public static final RegistryObject<Item> ITEM_TRACK_SCAN_PINCUSHION = register("visual_track_scan_pincushion", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.SCAN_PINCUSHION));
    public static final RegistryObject<Item> ITEM_TRACK_SOBEL = register("visual_track_sobel", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.SOBEL));
    public static final RegistryObject<Item> ITEM_TRACK_SPIDER = register("visual_track_spider", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.SPIDER));
    public static final RegistryObject<Item> ITEM_TRACK_WOBBLE = register("visual_track_wobble", () -> new ItemVisualTrack(new Item.Properties().stacksTo(1), EnumTrackID.WOBBLE));

    static <T extends Item> RegistryObject<T> register(String id, Supplier<T> item) {
        RegistryObject<T> DUMMY_ITEM = ITEMS.register(id, item);
        ITEM_ARRAY.add(DUMMY_ITEM);
        return DUMMY_ITEM;
    }
}
