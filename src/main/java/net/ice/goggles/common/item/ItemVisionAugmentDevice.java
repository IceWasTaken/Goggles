package net.ice.goggles.common.item;

import net.ice.api.goggles.IVisualGoggles;
import net.ice.goggles.common.util.EnumTrackID;
import net.ice.goggles.common.util.GoggleUtil;
import net.ice.goggles.registry.DataComponentRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ItemVisionAugmentDevice extends ArmorItem implements IVisualGoggles {
    public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

    public ItemVisionAugmentDevice(ArmorItem.Type type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
        properties.setNoRepair();
    }
    @SubscribeEvent
    public static void registerArmorMaterial(RegisterEvent event) {
        event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
            ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 2);
            }), 9, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(), List.of(new ArmorMaterial.Layer(ResourceLocation.parse(""))), 0f, 0f);
            registerHelper.register(ResourceLocation.parse(""), armorMaterial);
            ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static String makeFormattedTooltip(String transKey) {
        return "[" + transKey + "]";
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        EnumTrackID trackID = GoggleUtil.getVisualTrackIDFromItemStack(stack);
        components.add(Component.nullToEmpty(ChatFormatting.GRAY + "Installed Track:"));
        if (stack.has(DataComponentRegistry.TRACK_ID)) {
            components.add(Component.nullToEmpty(trackID.FORMAT + makeFormattedTooltip(trackID.TKEY.getString())));
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

    public static class Helmet extends ItemVisionAugmentDevice {
        public Helmet() {
            super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)));
        }

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return ResourceLocation.parse("goggles:textures/armor/goggles.png");
        }
    }



}
