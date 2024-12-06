package net.ice.goggles.common.util;

import net.ice.goggles.Goggles;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Goggles.MODID)
public class ShaderUtil {
    public static boolean ToLoadShader = true;
    public static String localShaderPath = "";
    public static boolean isDefaultShader = true;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onF5Use(InputEvent.Key event) {
        if(event.getKey() == GLFW.GLFW_KEY_F5) {
            ResourceLocation ToReload = new ResourceLocation("goggles", "shaders/"+localShaderPath);
            if(!ToReload.getPath().equals("shaders/")) {
                Minecraft.getInstance().gameRenderer.loadEffect(ToReload);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(TickEvent.ClientTickEvent event) {
        if (isDefaultShader || Objects.equals(localShaderPath, "null")) {
            Minecraft.getInstance().gameRenderer.shutdownEffect();
        }
        if (ToLoadShader & !isDefaultShader) {
            Minecraft.getInstance().gameRenderer.loadEffect(new ResourceLocation("goggles", "shaders/"+localShaderPath));
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
