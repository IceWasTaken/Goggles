package net.ice.goggles.registry;

import net.minecraftforge.eventbus.api.IEventBus;

public class RegistryRegistry {
    public static void registerRegistries(IEventBus eventBus) {
        ItemRegistry.ITEMS.register(eventBus);
        CreativeTabRegistry.CREATIVE_MODE_TABS.register(eventBus);
        ItemRegistry.ITEM_ARRAY.add(ItemRegistry.ITEM_VISION_DEVICE);
        ModifierRegistry.LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
