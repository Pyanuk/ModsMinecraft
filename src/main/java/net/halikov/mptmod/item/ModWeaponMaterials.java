package net.halikov.mptmod.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.halikov.mptmod.MptMod;

import java.util.function.Supplier;

public enum ModWeaponMaterials implements Tier {
    AMBER("amber", 2, 250, 6.0F, 2.0F, 14,
            () -> Ingredient.of(ModItems.AMBER.get()));

    private final String name;
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModWeaponMaterials(String name, int harvestLevel, int maxUses, float efficiency, float attackDamage,
                               int enchantability, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }
    @Override
    public int getUses() {
        return this.maxUses;
    }
    @Override
    public float getSpeed() {
        return this.efficiency;
    }
    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }
    @Override
    public int getLevel() {
        return this.harvestLevel;
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
    public String getName() {
        return MptMod.MOD_ID + ":" + this.name;
    }
}