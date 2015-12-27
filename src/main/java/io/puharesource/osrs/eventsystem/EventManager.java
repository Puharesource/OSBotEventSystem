package io.puharesource.osrs.eventsystem;

import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The event manager is a Singleton class, that handles all events.
 * It can register/unregister events and listeners and it can call events.
 */
public final class EventManager {
    private static EventManager instance;

    private final Map<Class<? extends Event>, RegisteredEvent> events = new HashMap<>();

    private EventManager() {}

    public static EventManager get() {
        return instance == null ? instance = new EventManager() : instance;
    }

    public Map<Class<? extends Event>, RegisteredEvent> getEvents() {
        return ImmutableMap.copyOf(events);
    }

    public void callEvent(final Event event) {
        if (event == null) throw new IllegalArgumentException("event cannot be null!");

        if (events.containsKey(event.getClass())) {
            events.get(event.getClass()).execute(event);
        }
    }

    public void registerEvent(final Class<? extends Event> eventClass) {
        if (eventClass == null) throw new IllegalArgumentException("eventClass cannot be null!");

        events.put(eventClass, new RegisteredEvent());
    }

    public void unregisterEvent(final Class<? extends Event> eventClass) {
        if (eventClass == null) throw new IllegalArgumentException("eventClass cannot be null!");

        events.remove(eventClass);
    }

    public RegisteredEvent getRegisteredEvent(final Class<? extends Event> eventClass) {
        if (eventClass == null) throw new IllegalArgumentException("eventClass cannot be null!");

        return events.get(eventClass);
    }

    public void registerListener(final EventListener listener) {
        if (listener == null) throw new IllegalArgumentException("listener cannot be null!");

        final Class<? extends EventListener> clazz = listener.getClass();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() != 1) continue;
            final Class<?> eventClass = method.getParameterTypes()[0];
            if (!Event.class.isAssignableFrom(eventClass)) continue;
            if (!method.isAnnotationPresent(EventHandler.class)) continue;

            if (events.containsKey(eventClass)) {
                final EventExecutor executor = new EventExecutor(listener, method, method.getDeclaredAnnotation(EventHandler.class));
                events.get(eventClass).addExecutor(executor);
            } else {
                System.err.println("Event " + eventClass.getName() + " is not registered and therefor the listener will NOT work for that event.");
            }
        }
    }
}
