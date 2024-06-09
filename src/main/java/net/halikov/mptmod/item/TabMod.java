package net.halikov.mptmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabMod {
    public static final CreativeModeTab MPT_TAB = new CreativeModeTab("Mpt tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AMBER.get());
        }
    };
}
