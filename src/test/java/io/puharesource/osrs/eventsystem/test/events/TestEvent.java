package io.puharesource.osrs.eventsystem.test.events;

import io.puharesource.osrs.eventsystem.CancellableEvent;
import io.puharesource.osrs.eventsystem.Event;

public final class TestEvent extends Event implements CancellableEvent {
    private final long time;
    private boolean cancelled;

    public TestEvent() {
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
