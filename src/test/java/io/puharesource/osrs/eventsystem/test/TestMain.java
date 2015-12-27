package io.puharesource.osrs.eventsystem.test;

import io.puharesource.osrs.eventsystem.EventManager;
import io.puharesource.osrs.eventsystem.test.events.Event1;
import io.puharesource.osrs.eventsystem.test.events.Event2;
import io.puharesource.osrs.eventsystem.test.listeners.TestListener;

import java.util.concurrent.ThreadLocalRandom;

public final class TestMain {
    private int i;

    public static void main(String[] args) {
        new TestMain();
    }

    public TestMain() {
        EventManager.get().registerEvent(Event1.class);
        EventManager.get().registerEvent(Event2.class);

        EventManager.get().registerListener(new TestListener());

        while (i <= 100) {
            loop();
        }
    }

    public void loop() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();

        if (random.nextInt(10000000) == 1) {
            System.out.println("> Should call event1");
            EventManager.get().callEvent(new Event1(1, 2));
            i++;
        }

        if (random.nextInt(10000000) == 1) {
            System.out.println("> Should call event2");
            EventManager.get().callEvent(new Event2("One", "Two"));
            i++;
        }
    }
}
