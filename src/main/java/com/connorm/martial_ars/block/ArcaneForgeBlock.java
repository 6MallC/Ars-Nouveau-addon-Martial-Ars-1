package com.connorm.martial_ars.block;

import com.connorm.martial_ars.block.entity.ArcaneForgeEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ArcaneForgeBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(1,1,1,15,12,15);
    public static final VoxelShape SHAPE2 = Block.box(-8,12,-4,24,20,15);
    public static final VoxelShape BLOCK = Shapes.or(SHAPE,SHAPE2);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
public static final MapCodec<ArcaneForgeBlock> CODEC = simpleCodec(ArcaneForgeBlock::new);
    public ArcaneForgeBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
        return BLOCK;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    @Nullable
    public BlockState getStateforPlacement(BlockPlaceContext context){
        Direction direction = context.getHorizontalDirection();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }
@Override
protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING);
}
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ArcaneForgeEntity(blockPos, blockState);
    }
    @Override
    protected RenderShape getRenderShape(BlockState state){
        return RenderShape.MODEL;
    }
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos ,BlockState newState, boolean movedbypiston){
        if(state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof ArcaneForgeEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedbypiston);
    }
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof ArcaneForgeEntity forge) {
            if (!level.isClientSide()) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(forge, Component.literal("arcaneforge")), pos);
                return ItemInteractionResult.SUCCESS;
            }

        }
        return ItemInteractionResult.SUCCESS;
    }
}
