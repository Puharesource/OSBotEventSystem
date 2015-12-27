package io.puharesource.osrs.eventsystem.test.events;

import io.puharesource.osrs.eventsystem.Event;

public final class Event2 extends Event {
    public final String data1;
    public final String data2;

    public Event2(final String data1, final String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
}