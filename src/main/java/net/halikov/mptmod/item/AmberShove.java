package net.halikov.mptmod.item;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;

public class AmberShove extends ShovelItem {
    public AmberShove(Tier tier, float attackDamageModifier, float attackSpeedModifier, Item.Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }
}
