package com.connorm.martial_ars.registry;

import com.connorm.martial_ars.item.ExampleCosmetic;
import com.connorm.martial_ars.registry.custom.MushroomWandItem;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

import static com.connorm.martial_ars.MartialArs.MODID;
import static com.connorm.martial_ars.MartialArs.prefix;
import static net.minecraft.core.registries.Registries.SOUND_EVENT;

public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(SOUND_EVENT, MODID);

    // ITEMS
    public static final DeferredItem<? extends Item> SourceIngot = (DeferredItem<? extends Item>) ITEMS.register("source_ingot",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.martial_ars.source_ingot.tooltip"));
                    super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
                }
            });
    public static final DeferredItem<Item> SourceMatrix = (DeferredItem<Item>) ITEMS.register("source_matrix",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<MushroomWandItem> MushroomWand = (DeferredItem<MushroomWandItem>) ITEMS.register("mushroom_wand",
            () -> new MushroomWandItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> EnchantersSpear = (DeferredItem<Item>) ITEMS.register("enchanters_spear",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SpearShaft = (DeferredItem<Item>) ITEMS.register("spear_handle",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> SpearHead = (DeferredItem<Item>) ITEMS.register("spear_head",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> EnchantersKunai = (DeferredItem<Item>) ITEMS.register("enchanters_kunai",
            () -> new Item(new Item.Properties().stacksTo(4)));
    // BLOCKS
    public static final DeferredBlock<Block> sourceingot_block = registerBlock("sourceingot_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));
    public static final DeferredItem<Item> BlankInlay = (DeferredItem<Item>) ITEMS.register("blank_inlay",
            () -> new Item(new Item.Properties()));

    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);

    }
public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
    ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
}
   public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
    DeferredBlock<T> toReturn = (DeferredBlock<T>) BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
    }

public static final DeferredHolder<Item, ? extends Item> EXAMPLE;
//this is an example of how to register a sound. You also need to add the sound to the sound.json file, referencing your ogg files, and a texture for the button under textures/sounds.
//this example will use one of the existing sounds randomly
public static DeferredHolder<SoundEvent, SoundEvent> EXAMPLE_FAMILY = SOUNDS.register("example_sound", () -> makeSound("example_sound"));
public static SpellSound EXAMPLE_SPELL_SOUND = new SpellSound(ModRegistry.EXAMPLE_FAMILY, Component.literal("Example"), prefix("example_random_sound"));


static {
    EXAMPLE = ITEMS.register("star_hat", () -> new ExampleCosmetic(new Item.Properties()));
}

static SoundEvent makeSound(@NotNull String name) {
    return SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, name));
}
}
































