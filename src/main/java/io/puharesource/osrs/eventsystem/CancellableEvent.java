package io.puharesource.osrs.eventsystem;

/**
 * The CancellableEvent interface is an interface
 * that is to be implemented when an event can be cancelled
 * from happening.
 */
public interface CancellableEvent {
    boolean isCancelled();
    void setCancelled(boolean cancel);
}
