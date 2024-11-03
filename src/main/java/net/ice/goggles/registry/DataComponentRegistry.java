package net.ice.goggles.registry;

import com.mojang.serialization.Codec;
import net.ice.goggles.Goggles;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class DataComponentRegistry {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Goggles.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TRACK_ID = register("trackid", builder -> builder
            .persistent(Codec.INT)
            .networkSynchronized(ByteBufCodecs.INT)
            .cacheEncoding());

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

}
