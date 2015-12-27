package io.puharesource.osrs.eventsystem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The EventHandler annotation serves to tell the event
 * system that the following method is an event handler.
 * EventHandlers should only be used within classes that
 * implement the EventListener interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * If set to true, the following method will not be executed,
     * if the event has been cancelled by a previous event.
     *
     * DEFAULT: false
     *
     * @return whether the EventHandler should be skipped if the event is cancelled.
     */
    boolean ignoreCancelled() default false;

    /**
     * The priority in which the EventHandler is being executed.
     *
     * DEFAULT: NORMAL
     *
     * @return The priority.
     */
    EventPriority priority() default EventPriority.NORMAL;
}
