package net.ice.goggles;

import com.mojang.logging.LogUtils;
import net.ice.goggles.common.packet.PayloadHandler;
import net.ice.goggles.common.packet.UsedVisonTrack;
import net.ice.goggles.registry.RegistryRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

@Mod(Goggles.MODID)
public class Goggles
{
    public static final String MODID = "goggles";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Goggles(IEventBus modEventBus, ModContainer modContainer)
    {
        //Register Registry Registers
        RegistryRegistry.registerRegistries(modEventBus);
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                UsedVisonTrack.TYPE,
                UsedVisonTrack.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PayloadHandler.ClientPayloadHandler::handleDataOnNetwork,
                        PayloadHandler.ServerPayloadHandler::handleDataOnNetwork
                )
        );
    }
}
