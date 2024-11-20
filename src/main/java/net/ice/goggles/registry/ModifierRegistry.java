package net.ice.goggles.registry;

import com.mojang.serialization.MapCodec;
import net.ice.goggles.Goggles;
import net.ice.goggles.common.datagen.GlobalLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModifierRegistry {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Goggles.MODID);

    public static final Supplier<MapCodec<GlobalLootModifier>> MY_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> GlobalLootModifier.CODEC_SUPPLIER.get());
}
