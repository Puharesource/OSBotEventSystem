package io.puharesource.osrs.eventsystem;

/**
 * The event priories are used to tell the event system
 * in which order the event executors are going to be executed.
 *
 * The order in which the EventHandlers are being executed is as followed (left to right):
 * LOWEST - LOW - NORMAL - HIGH - HIGHEST - MONITOR.
 */
public enum EventPriority {
    /**
     * LOWEST is to be used, when you want to allow multiple
     * handlers to edit the final outcome, even after a change from this event.
     */
    LOWEST,

    /**
     * LOW is similar to LOWEST, however, will execute after LOWEST.
     */
    LOW,

    /**
     * NORMAL is the default priority and is similar to LOW, but will execute after LOW.
     */
    NORMAL,

    /**
     * HIGH is similar to NORMAL, but will execute after NORMAL.
     */
    HIGH,

    /**
     * HIGHEST is similar to HIGH, but will excecutes after HIGH.
     * HIGHEST is the last event that should be used to edit the outcome / data.
     * of the event.
     */
    HIGHEST,

    /**
     * MONITOR executes after HIGHEST, however, it shouldn't be used
     * to edit the outcome / data of the event. MONITOR should only
     * be used for logging purposes.
     */
    MONITOR
}
