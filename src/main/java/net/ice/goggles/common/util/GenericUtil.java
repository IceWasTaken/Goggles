package net.ice.goggles.common.util;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.HitResult;

public class GenericUtil {
    public static String makeFormattedTooltip(String transKey) {
        return "[" + translation(transKey) + "]";
    }

    public static String translation(String s, Object... args) {
        return I18n.get(s, args);
    }
}
