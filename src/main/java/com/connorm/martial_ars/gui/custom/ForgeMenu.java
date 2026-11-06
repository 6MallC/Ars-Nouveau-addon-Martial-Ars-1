package com.connorm.martial_ars.gui.custom;

import com.connorm.martial_ars.block.entity.ArcaneForgeEntity;
import com.connorm.martial_ars.gui.MartialMenuTypes;
import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ForgeMenu extends AbstractContainerMenu {
    private final ArcaneForgeEntity ent;
    private final Level lvl;

    public ForgeMenu(int containerId, Inventory inv, FriendlyByteBuf extradata) {
        this(containerId, inv, inv.player.level().getBlockEntity(extradata.readBlockPos()));
    }
    public ForgeMenu(int containerId, Inventory inv, BlockEntity blockEntity){
        super(MartialMenuTypes.FORGE_MENU.get(), containerId);
        this.ent = ((ArcaneForgeEntity) blockEntity);
        this.lvl = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addSlot(new SlotItemHandler(this.ent.inventory, 0,14,14));
        this.addSlot(new SlotItemHandler(this.ent.inventory, 1,55,14));
        this.addSlot(new SlotItemHandler(this.ent.inventory, 2,35,47));
        this.addSlot(new SlotItemHandler(this.ent.inventory, 3,122,35));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(lvl,ent.getBlockPos()),
            player, MartialRegistry.ARCANEFORGE.get());
    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
