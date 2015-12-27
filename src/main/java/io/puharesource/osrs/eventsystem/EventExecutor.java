package io.puharesource.osrs.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An event executor executes the method
 * contained inside of the listener class.
 */
public final class EventExecutor {
    private final EventListener listener;
    private final Method method;
    private final EventHandler handler;

    EventExecutor(final EventListener listener, final Method method, final EventHandler handler) {
        if (listener == null) throw new IllegalArgumentException("listener cannot be null!");
        if (method == null) throw new IllegalArgumentException("method cannot be null!");
        if (handler == null) throw new IllegalArgumentException("handler cannot be null!");

        this.listener = listener;
        this.method = method;
        this.handler = handler;
    }

    public EventHandler getHandler() {
        return handler;
    }

    public void execute(final Event event) {
        if (event == null) throw new IllegalArgumentException("event cannot be null!");

        try {
            method.invoke(listener, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
