package com.minecolonies.core.client.gui;

import com.ldtteam.blockui.controls.Button;
import com.ldtteam.blockui.controls.Text;
import com.ldtteam.structurize.client.gui.WindowSwitchPack;
import com.minecolonies.api.items.ModItems;
import com.minecolonies.api.util.SoundUtils;
import com.minecolonies.core.Network;
import com.minecolonies.core.datalistener.ColonyStoryDataListener;
import com.minecolonies.core.network.messages.server.MarkStoryReadOnItem;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.minecolonies.api.items.ISupplyItem.SUPPLY_OFFSET_DISTANCE;
import static com.minecolonies.api.util.constant.Constants.*;
import static com.minecolonies.api.util.constant.NbtTagConstants.TAG_RANDOM_KEY;
import static com.minecolonies.api.util.constant.WindowConstants.*;

/**
 * Supply Story Window.
 * todo add new art for this later.
 */
public class WindowSupplyStory extends AbstractWindowSkeleton
{
    /**
     * Right click position.
     */
    private final BlockPos pos;

    /**
     * Type of camp/ship.
     */
    private final String type;
    private final InteractionHand hand;

    /**
     * Placing stack.
     */
    private ItemStack stack;

    public WindowSupplyStory(final BlockPos pos, final String type, final ItemStack stack, final InteractionHand hand)
    {
        super(MOD_ID + SUPPLIES_STORY_RESOURCE_SUFFIX);
        mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        if (pos == null)
        {
            this.pos = mc.player.blockPosition().relative(mc.player.getDirection(), SUPPLY_OFFSET_DISTANCE);
        }
        else
        {
            this.pos = pos;
        }
        this.type = type;
        this.stack = stack;
        this.hand = hand;

        registerButton(BUTTON_CANCEL, this::close);
        registerButton(BUTTON_COLONY_SWITCH_STYLE, this::switchPack);
        registerButton(BUTTON_PLACE, this::place);

        final Random random = new Random(stack.getTag().getLong(TAG_RANDOM_KEY));
        List<MutableComponent> story = new ArrayList<>();

        if (stack.getOrCreateTag().getString(PLACEMENT_NBT).equals(INSTANT_PLACEMENT)) // if free dungeon loot nbt tag on item.
        {
            if (stack.getItem() == ModItems.supplyCamp)
            {
                story.add(Component.literal(ColonyStoryDataListener.supplyCampStories.get(random.nextInt(ColonyStoryDataListener.supplyCampStories.size()))));
            }
            else
            {
                story.add(Component.literal(ColonyStoryDataListener.supplyShipStories.get(random.nextInt(ColonyStoryDataListener.supplyShipStories.size()))));
            }
            story.add(Component.empty());
        }

        story.add(Component.translatable("com.minecolonies.core.gui.supplies.guide", Component.translatable(stack.getItem().getDescriptionId())));
        story.add(Component.empty());

        if (stack.getItem() == ModItems.supplyCamp)
        {
            story.add(Component.translatable("com.minecolonies.core.gui.supplycamp.guide"));
        }
        else
        {
            story.add(Component.translatable("com.minecolonies.core.gui.supplyship.guide"));
        }

        this.findPaneOfTypeByID("text", Text.class).setText(story);
        this.findPaneOfTypeByID("place", Button.class).setText(Component.translatable("com.minecolonies.core.gui.supplies.place", Component.translatable(stack.getItem().getDescriptionId())));
    }

    /**
     * Redirect to placement window.
     */
    private void place()
    {
        Network.getNetwork().sendToServer(new MarkStoryReadOnItem(hand));
        new WindowSupplies(pos, type).open();
    }

    /**
     * Switch the structure style pack.
     */
    private void switchPack()
    {
        new WindowSwitchPack(() -> new WindowSupplyStory(pos, type, stack, hand)).open();
    }
}
