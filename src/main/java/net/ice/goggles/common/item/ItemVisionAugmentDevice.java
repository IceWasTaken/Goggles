package net.ice.goggles.common.item;

import net.ice.api.goggles.IVisualGoggles;
import net.ice.goggles.Goggles;
import net.ice.goggles.common.util.EnumTrackID;
import net.ice.goggles.common.util.GoggleUtil;
import net.ice.goggles.common.util.ShaderUtil;
import net.ice.goggles.registry.DataComponentRegistry;
import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EventBusSubscriber(modid = Goggles.MODID)
public class ItemVisionAugmentDevice extends Item implements Equipable, IVisualGoggles {
    public ItemVisionAugmentDevice(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        EnumTrackID trackID = GoggleUtil.getVisualTrack(stack);
        components.add(Component.nullToEmpty(ChatFormatting.GRAY + "Installed Track:"));
        if (stack.has(DataComponentRegistry.TRACK_ID)) {
            components.add(Component.nullToEmpty(trackID.FORMAT + trackID.TKEY));
        } else {
            components.add(Component.nullToEmpty(ChatFormatting.RED + "[EMPTY]"));
        }
        super.appendHoverText(stack, context, components, tooltipFlag);
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(player.isCrouching()) {
            GoggleUtil.removeVisualTrack(player.getItemInHand(usedHand), player);
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }
        return this.swapWithEquipmentSlot(this, level, player, usedHand);
    }

    @SubscribeEvent
    private static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (event.getSlot().isArmor()) {
            if(event.getEntity() instanceof Player) {
                if(event.getFrom().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    if(event.getFrom().has(DataComponentRegistry.TRACK_ID)) {
                        ShaderUtil.load(null, true);
                        return;
                    }
                }
                if(event.getTo().getItem() == ItemRegistry.ITEM_VISION_DEVICE.get()) {
                    EnumTrackID trackID = GoggleUtil.getVisualTrack(event.getTo());
                    if(trackID.PATH != null) {
                        ShaderUtil.load(trackID.PATH, trackID.ID == 0);
                    }
                }
            }
        }
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.parse("worldmanip:textures/models/armor/Untitled.png");
    }
}
