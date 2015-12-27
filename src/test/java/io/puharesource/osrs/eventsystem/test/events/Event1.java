package io.puharesource.osrs.eventsystem.test.events;

import io.puharesource.osrs.eventsystem.Event;

public final class Event1 extends Event {
    public final int data1;
    public final int data2;

    public Event1(final int data1, final int data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
}