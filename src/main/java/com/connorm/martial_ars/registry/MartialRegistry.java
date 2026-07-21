package com.connorm.martial_ars.registry;

import com.alexthw.sauce.registry.ModRegistry;
import com.connorm.martial_ars.item.ExampleCosmetic;
import com.connorm.martial_ars.registry.custom.EnchantersSpearItem;
import com.connorm.martial_ars.registry.custom.KunaiItem;
import com.connorm.martial_ars.registry.custom.ModToolTiers;
import com.connorm.martial_ars.registry.custom.MushroomWandItem;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
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

public class MartialRegistry {

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
    public static final DeferredItem<Item> SpearShaft = (DeferredItem<Item>) ITEMS.register("spear_handle",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<Item> SpearHead = (DeferredItem<Item>) ITEMS.register("spear_head",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final DeferredItem<SwordItem> GeoBlade = (DeferredItem<SwordItem>) ITEMS.register("geo.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> GeoHilt = (DeferredItem<SwordItem>) ITEMS.register("geo.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> AeroBlade = (DeferredItem<SwordItem>) ITEMS.register("aero.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> AeroHilt = (DeferredItem<SwordItem>) ITEMS.register("aero.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> PyroBlade = (DeferredItem<SwordItem>) ITEMS.register("pyro.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> PyroHilt = (DeferredItem<SwordItem>) ITEMS.register("pyro.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> AquaBlade = (DeferredItem<SwordItem>) ITEMS.register("aqua.blade",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));
    public static final DeferredItem<SwordItem> AquaHilt = (DeferredItem<SwordItem>) ITEMS.register("aqua.hilt",
            () -> new SwordItem(ModToolTiers.Martial, new Item.Properties()));


    public static final DeferredItem<Item> KunaiHandle = (DeferredItem<Item>) ITEMS.register("kunai_handle",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> KunaiBlade = (DeferredItem<Item>) ITEMS.register("kunai_blade",
            () -> new Item(new Item.Properties()));
// inlays
public static final DeferredItem<Item> DelayInlay = (DeferredItem<Item>) ITEMS.register("delay_inlay",
        () -> new Item(new Item.Properties()));
public static final DeferredItem<Item> LuckInlay = (DeferredItem<Item>) ITEMS.register("luck_inlay",
        () -> new Item(new Item.Properties()));
public static final DeferredItem<Item> BlankInlay = (DeferredItem<Item>) ITEMS.register("blank_inlay",
        () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AttackInlay = (DeferredItem<Item>) ITEMS.register("attack_inlay",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DiscountInlay = (DeferredItem<Item>) ITEMS.register("discount_inlay",
            () -> new Item(new Item.Properties()));

    // TOOLS

    public static final DeferredItem<KunaiItem> EnchantersKunai = (DeferredItem<KunaiItem>) ITEMS.register("enchanters_kunai",
            () -> new KunaiItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, -2, -1f)
                            .withModifierAdded(PerkAttributes.SPELL_DAMAGE_BONUS,
                                    new AttributeModifier(ArsNouveau.prefix("sword_spell_bonus"),
                                            2.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));

    public static final DeferredItem<EnchantersSpearItem> EnchantersSpear = (DeferredItem<EnchantersSpearItem>) ITEMS.register("enchanters_spear",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 1, -2f)
                            .withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE,
                                    new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MODID, "spear_reach"),
                                            2.0, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(PerkAttributes.SPELL_DAMAGE_BONUS,
                                    new AttributeModifier(ArsNouveau.prefix("sword_spell_bonus"),
                                            4.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> GeoSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.geomancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
            .withModifierAdded(com.alexthw.sauce.registry.ModRegistry.EARTH_POWER,
                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.earth_power"),
                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_EARTH,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.earth"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
            .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> AeroSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.aeromancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.AIR_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.air_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_AIR,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.air"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> AquaSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.aquamancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.WATER_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.aqua_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_WATER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.water"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));
    public static final DeferredItem<EnchantersSpearItem> PyroSword = (DeferredItem<EnchantersSpearItem>) ITEMS.register("sword.pyromancy",
            () -> new EnchantersSpearItem(ModToolTiers.Martial, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.Martial, 2, -2.4f)
                            .withModifierAdded(ModRegistry.FIRE_POWER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.fire_power"),
                                            8.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .withModifierAdded(ModRegistry.MANA_DISCOUNT_WATER,
                                    new AttributeModifier(ArsNouveau.prefix("sauce.perk.mana_discount.water"),
                                            .15f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND))
                    .component(DataComponentRegistry.SPELL_CASTER, new SpellCaster())));


    // BLOCKS
    public static final DeferredBlock<Block> sourceingot_block = registerBlock("sourceingot_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));


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
// SOUNDS
public static final DeferredHolder<Item, ? extends Item> EXAMPLE;
//this is an example of how to register a sound. You also need to add the sound to the sound.json file, referencing your ogg files, and a texture for the button under textures/sounds.
//this example will use one of the existing sounds randomly
public static DeferredHolder<SoundEvent, SoundEvent> EXAMPLE_FAMILY = SOUNDS.register("example_sound", () -> makeSound("example_sound"));
public static SpellSound EXAMPLE_SPELL_SOUND = new SpellSound(MartialRegistry.EXAMPLE_FAMILY, Component.literal("Example"), prefix("example_random_sound"));


static {
    EXAMPLE = ITEMS.register("star_hat", () -> new ExampleCosmetic(new Item.Properties()));
}

static SoundEvent makeSound(@NotNull String name) {
    return SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, name));
}
}
































