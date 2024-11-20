package net.ice.goggles.registry;

import net.ice.goggles.Goggles;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Goggles.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("googles_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.goggles"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(Items.AMETHYST_SHARD::getDefaultInstance).displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ItemRegistry.ITEM_VISION_DEVICE);
                                for(DeferredItem<? extends Item> i : ItemRegistry.ITEM_ARRAY) {
                                    output.accept(i);
                                }
                            }
                    )
                    .build()
    );
}
