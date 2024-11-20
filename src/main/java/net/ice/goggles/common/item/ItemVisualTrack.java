package net.ice.goggles.common.item;

import net.ice.api.goggles.IVisualGoggles;
import net.ice.api.goggles.IVisualTrack;
import net.ice.goggles.common.util.GoggleUtil;
import net.ice.goggles.common.util.EnumTrackID;
import net.ice.goggles.registry.DataComponentRegistry;
import net.ice.goggles.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemVisualTrack extends Item implements IVisualTrack {

    private final EnumTrackID trackID;

    public ItemVisualTrack(Properties properties, EnumTrackID trackID) {
        super(properties);
        this.trackID = trackID;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(this == ItemRegistry.ITEM_TRACK_NULL.get()) {
            tooltipComponents.add(Component.nullToEmpty(ChatFormatting.GRAY + "Oh wow your game glitched."));
            tooltipComponents.add(Component.nullToEmpty(ChatFormatting.GRAY + "Why? - I don't know."));
            tooltipComponents.add(Component.nullToEmpty(ChatFormatting.GRAY + "Just open an issue in the repo"));
            tooltipComponents.add(Component.nullToEmpty(ChatFormatting.GRAY + "and I'll fix it."));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack helmet = player.getInventory().armor.get(3);
        if(helmet.getItem() instanceof IVisualGoggles) {
            if(!helmet.has(DataComponentRegistry.TRACK_ID)) {
                if(level.isClientSide()) {
                    GoggleUtil.insertVisualTrack(helmet, trackID);
                    return InteractionResultHolder.consume(player.getItemInHand(usedHand));
                }
            }
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public EnumTrackID getTrackID() {
        return trackID;
    }
}
