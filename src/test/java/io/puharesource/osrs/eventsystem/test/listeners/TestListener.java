package io.puharesource.osrs.eventsystem.test.listeners;

import io.puharesource.osrs.eventsystem.EventHandler;
import io.puharesource.osrs.eventsystem.EventListener;
import io.puharesource.osrs.eventsystem.test.events.Event1;
import io.puharesource.osrs.eventsystem.test.events.Event2;

public final class TestListener implements EventListener {
    @EventHandler
    public void onEvent1(final Event1 event) {
        System.out.println("Event1 fired! ");
        System.out.print("data1: ");
        System.out.println(event.data1);
        System.out.print("data2: ");
        System.out.println(event.data2);
        System.out.println("----------------------------");
    }

    @EventHandler
    public void onEvent2(final Event2 event) {
        System.out.println("Event2 fired! ");
        System.out.print("data1: ");
        System.out.println(event.data1);
        System.out.print("data2: ");
        System.out.println(event.data2);
        System.out.println("----------------------------");
    }
}
