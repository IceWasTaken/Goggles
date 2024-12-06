package net.ice.goggles.common.item;

import net.ice.api.goggles.IVisualGoggles;
import net.ice.goggles.common.util.EnumTrackID;
import net.ice.goggles.common.util.GoggleUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;

public class ItemVisionAugmentDevice extends ArmorItem implements IVisualGoggles {
    public ItemVisionAugmentDevice(Type type, Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForType(Type type) {
                return new int[]{13,0,0,0}[type.getSlot().getIndex()] * 15;
            }

            @Override
            public int getDefenseForType(Type type) {
                return new int[]{2, 0, 0, 0}[type.getSlot().getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 9;
            }

            @Override
            public SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_CHAIN;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }

            @Override
            public String getName() {
                return "test";
            }

            @Override
            public float getToughness() {
                return 0;
            }

            @Override
            public float getKnockbackResistance() {
                return 0;
            }
        }, type, properties);
    }

    @OnlyIn(Dist.CLIENT)
    public static String makeFormattedTooltip(String transKey) {
        return "[" + transKey + "]";
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> components, TooltipFlag tooltipFlag) {
        EnumTrackID trackID = GoggleUtil.getVisualTrackIDFromItemStack(stack);

        if(trackID != null) {
            components.add(Component.nullToEmpty(ChatFormatting.GRAY + "Installed Track:"));
            if (stack.hasTag()) {
                components.add(Component.nullToEmpty(trackID.FORMAT + makeFormattedTooltip(trackID.TKEY.getString())));
            } else {
                components.add(Component.nullToEmpty(ChatFormatting.RED + "[EMPTY]"));
            }
            super.appendHoverText(stack, context, components, tooltipFlag);
        }
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
            super(Type.HELMET, new Item.Properties());
        }

        @Override
        public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "goggles:textures/armor/goggles.png";
        }
    }
}
