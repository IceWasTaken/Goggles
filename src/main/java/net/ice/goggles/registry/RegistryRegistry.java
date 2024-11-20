package net.ice.goggles.registry;

import net.neoforged.bus.api.IEventBus;

public class RegistryRegistry {
    public static void registerRegistries(IEventBus eventBus) {
        ItemRegistry.ITEMS.register(eventBus);
        CreativeTabRegistry.CREATIVE_MODE_TABS.register(eventBus);
        DataComponentRegistry.DATA_COMPONENT_TYPES.register(eventBus);
        ItemRegistry.ITEM_ARRAY.add(ItemRegistry.ITEM_VISION_DEVICE);
        ModifierRegistry.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
