package io.puharesource.osrs.eventsystem;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The RegisteredEvent class controls the order in which
 * the executors are being executed with the use of EventPriorities.
 * As well as control which executors should be added / removed.
 */
public final class RegisteredEvent {
    private final Map<EventPriority, Set<EventExecutor>> executors = new EnumMap<>(EventPriority.class);

    RegisteredEvent() {
        for (final EventPriority priority : EventPriority.values()) {
            executors.put(priority, new HashSet<>());
        }
    }

    public void execute(final Event event) {
        if (event == null) throw new IllegalArgumentException("event cannot be null!");

        final Consumer<? super EventExecutor> eventExecutor = executor -> executor.execute(event);
        final Predicate<? super EventExecutor> eventFilter = executor -> !(event instanceof CancellableEvent) || executor.getHandler().ignoreCancelled() || !((CancellableEvent) event).isCancelled();

        executors.get(EventPriority.LOWEST).stream().filter(eventFilter).forEach(eventExecutor);
        executors.get(EventPriority.LOW).stream().filter(eventFilter).forEach(eventExecutor);
        executors.get(EventPriority.NORMAL).stream().filter(eventFilter).forEach(eventExecutor);
        executors.get(EventPriority.HIGH).stream().filter(eventFilter).forEach(eventExecutor);
        executors.get(EventPriority.HIGHEST).stream().filter(eventFilter).forEach(eventExecutor);
        executors.get(EventPriority.MONITOR).stream().filter(eventFilter).forEach(eventExecutor);
    }

    public void addExecutor(final EventExecutor executor) {
        if (executor == null) throw new IllegalArgumentException("executor cannot be null!");

        executors.get(executor.getHandler().priority()).add(executor);
    }

    public void removeExecutor(final EventExecutor executor) {
        if (executor == null) throw new IllegalArgumentException("executor cannot be null!");

        executors.get(executor.getHandler().priority()).remove(executor);
    }
}