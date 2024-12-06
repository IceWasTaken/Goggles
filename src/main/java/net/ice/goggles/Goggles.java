package net.ice.goggles;

import com.mojang.logging.LogUtils;
import net.ice.goggles.common.packet.PayloadHandler;
import net.ice.goggles.registry.RegistryRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Goggles.MODID)
public class Goggles
{
    public static final String MODID = "goggles";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Goggles()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegistryRegistry.registerRegistries(modEventBus);
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
        PayloadHandler.register();
    }
}
