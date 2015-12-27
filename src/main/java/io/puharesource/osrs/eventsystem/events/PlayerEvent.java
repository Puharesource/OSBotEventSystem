package io.puharesource.osrs.eventsystem.events;

import io.puharesource.osrs.eventsystem.Event;
import org.osbot.rs07.api.model.Player;

/**
 * The player event is an event that should handle
 * anything to do with the/a player.
 */
public abstract class PlayerEvent extends Event {
    protected final Player player;

    public PlayerEvent(final Player player) {
        if (player == null) throw new IllegalArgumentException("player cannot be null!");

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
