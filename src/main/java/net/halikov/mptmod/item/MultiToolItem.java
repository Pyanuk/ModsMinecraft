package net.halikov.mptmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;


public class MultiToolItem extends DiggerItem {
    private static final float ATTACK_DAMAGE = 3.0F;
    private static final float ATTACK_SPEED = -2.4F;

    public MultiToolItem(Tier tier, Item.Properties properties) {
        super(ATTACK_DAMAGE, ATTACK_SPEED, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockState) {
        return blockState.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                blockState.is(BlockTags.MINEABLE_WITH_SHOVEL) ||
                blockState.is(BlockTags.MINEABLE_WITH_AXE) ||
                blockState.is(BlockTags.MINEABLE_WITH_HOE);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL) ||
                state.is(BlockTags.MINEABLE_WITH_AXE) ||
                state.is(BlockTags.MINEABLE_WITH_HOE)) {
            return this.speed;
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (entity) -> {
            entity.broadcastBreakEvent(entity.getUsedItemHand());
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, net.minecraft.core.BlockPos pos, LivingEntity entity) {
        if (this.isCorrectToolForDrops(state)) {
            stack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(entity.getUsedItemHand()));
        }
        return true;
    }


    @Override
    public boolean canAttackBlock(BlockState state, Level world, net.minecraft.core.BlockPos pos, net.minecraft.world.entity.player.Player player) {
        return !player.isCreative();
    }



    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        net.minecraft.core.BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);

        if (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK)) {
            BlockState farmland = Blocks.FARMLAND.defaultBlockState();
            world.setBlock(pos, farmland, 11);
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), (player) -> {
                player.broadcastBreakEvent(context.getHand());
            });
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }
}