package io.puharesource.osrs.eventsystem.test;

import io.puharesource.osrs.eventsystem.EventManager;
import io.puharesource.osrs.eventsystem.test.events.Event1;
import io.puharesource.osrs.eventsystem.test.events.Event2;
import io.puharesource.osrs.eventsystem.test.listeners.TestListener;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(version = 1, author = "Puharesource", logo = "", name = "TestScript", info = "Showing you how to use the events system in a script.")
public final class TestScript extends Script {
    @Override
    public void onStart() throws InterruptedException {
        EventManager.get().registerEvent(Event1.class);
        EventManager.get().registerEvent(Event2.class);

        EventManager.get().registerListener(new TestListener());
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (random(0, 10) == 5) {
            EventManager.get().callEvent(new Event1(1, 2));
        }

        if (random(0, 10) == 5) {
            EventManager.get().callEvent(new Event2("One", "Two"));
        }

        return random(200, 300);
    }
}
