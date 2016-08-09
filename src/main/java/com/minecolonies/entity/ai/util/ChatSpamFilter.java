package com.minecolonies.entity.ai.util;

import com.minecolonies.entity.EntityCitizen;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

/**
 * Send chat messages without worry about spam.
 */
public class ChatSpamFilter
{
    /**
     * The timeout in ticks to wait initially
     */
    protected static final int BASE_TIMEOUT  = 30 * 20;
    /**
     * The maximum delay to wait
     */
    protected static final int MAX_TIMEOUT   = 20 * 60 * 10;
    /**
     * The number to multiply timeout with every time
     */
    private static final   int POWER_TIMEOUT = 2;
    private final EntityCitizen worker;
    private int    speechDelay       = 0;
    private String speechDelayString = "";
    private int    speechRepeat      = 0;

    public ChatSpamFilter(final EntityCitizen worker)
    {
        this.worker = worker;
    }

    /**
     * Request an Item without spamming the chat.
     *
     * @param chat the Item Name
     */
    public void requestWithoutSpam(@NotNull String chat)
    {
        talkWithoutSpam("entity.miner.messageNeedBlockAndItem", chat);
    }

    /**
     * Send a chat message as often as you like.
     * It will be shown in certain delays.
     * Helpful for requesting items.
     *
     * @param key  the translation key
     * @param chat the chat message
     */
    public void talkWithoutSpam(String key, String... chat)
    {
        String curstring = key + Arrays.toString(chat);
        if (Objects.equals(speechDelayString, curstring))
        {
            if (speechDelay > 0)
            {
                speechDelay--;
                return;
            }
            speechRepeat++;
        }
        else
        {
            speechDelay = 0;
            speechRepeat = 0;
        }
        worker.sendLocalizedChat(key, (Object[]) chat);
        speechDelayString = key + Arrays.toString(chat);
        speechDelay = Math.min((int) (BASE_TIMEOUT * Math.pow(POWER_TIMEOUT, speechRepeat)), MAX_TIMEOUT);
    }
}
