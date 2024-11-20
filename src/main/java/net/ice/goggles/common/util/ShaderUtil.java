package net.ice.goggles.common.util;

import net.ice.goggles.Goggles;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

@EventBusSubscriber(modid = Goggles.MODID)
public class ShaderUtil {
    public static boolean ToLoadShader = true;
    public static String localShaderPath = "";
    public static boolean isDefaultShader = true;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onF5Use(InputEvent.Key event) {
        if(event.getKey() == GLFW.GLFW_KEY_F5) {
            ResourceLocation ToReload = ResourceLocation.fromNamespaceAndPath("goggles", "shaders/"+localShaderPath);
            if(!ToReload.getPath().equals("shaders/")) {
                Minecraft.getInstance().gameRenderer.loadEffect(ToReload);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(ClientTickEvent.Post event) {
        if (isDefaultShader || Objects.equals(localShaderPath, "null")) {
            Minecraft.getInstance().gameRenderer.shutdownEffect();
        }
        if (ToLoadShader & !isDefaultShader) {
            Minecraft.getInstance().gameRenderer.loadEffect(ResourceLocation.fromNamespaceAndPath("goggles", "shaders/"+localShaderPath));
            ToLoadShader = false;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void loadAndClose(String ShaderPath, boolean isDefaultIn, Player player) {
        localShaderPath = ShaderPath;
        player.closeContainer();
        isDefaultShader = isDefaultIn;
        ToLoadShader = true;
    }

    @OnlyIn(Dist.CLIENT)
    public static void load(String ShaderPath, boolean isDefaultIn) {
        localShaderPath = ShaderPath;
        isDefaultShader = isDefaultIn;
        ToLoadShader = true;
    }

    @OnlyIn(Dist.CLIENT)
    public static void loadAndCloseUnsafe(String ShaderPath, boolean isDefaultIn) {
        localShaderPath = ShaderPath;
        Minecraft.getInstance().setScreen(null);
        isDefaultShader = isDefaultIn;
        ToLoadShader = true;

    }
}
