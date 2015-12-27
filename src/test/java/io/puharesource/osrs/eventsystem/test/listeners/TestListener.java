package io.puharesource.osrs.eventsystem.test.listeners;

import io.puharesource.osrs.eventsystem.EventHandler;
import io.puharesource.osrs.eventsystem.EventListener;
import io.puharesource.osrs.eventsystem.EventPriority;
import io.puharesource.osrs.eventsystem.test.events.Event1;
import io.puharesource.osrs.eventsystem.test.events.Event2;
import io.puharesource.osrs.eventsystem.test.events.TestEvent;

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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEventFirst(final TestEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEventSecond(final TestEvent event) {
        if (event.isCancelled()) {
            event.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEventLast(final TestEvent event) {
        if (event.getTime() % 2 != 0) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEventLog(final TestEvent event) {
        System.out.println("This event was fired at: " + event.getTime() + " and is an even number!");
    }
}
