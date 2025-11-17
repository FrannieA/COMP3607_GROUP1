
import java.util.*;

/**
 * @author Shania Siew
 * A generic event manager for handling event subscriptions and notifications.
 */
public class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * Subscribes an {@link EventListener} to the specified event type.
     * <p>
     * If the event type has no listeners yet, a new list is created.
     * </p>
     *
     * @param eventType the type of event to subscribe to
     * @param listener  the listener that will be notified when the event occurs
     */
    public void subscribe(String eventType, EventListener listener) {
        listeners.putIfAbsent(eventType, new ArrayList<>());
        listeners.get(eventType).add(listener);
    }

    /**
     * Unsubscribes an {@link EventListener} from a specific event type.
     * <p>
     * If the event type has no listeners, the method has no effect.
     * </p>
     *
     * @param eventType the type of event to unsubscribe from
     * @param listener  the listener to remove
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        if (users != null) {
            users.remove(listener);
        }
    }

    /**
     * Notifies all listeners subscribed to the given event type.
     * <p>
     * Each listener's {@link EventListener#update(String, Object)} method
     * is invoked with the event type and associated event data.
     * </p>
     *
     * @param eventType the event type that occurred
     * @param data      additional information related to the event
     */
    public void notify(String eventType, Object data) {
        List<EventListener> users = listeners.get(eventType);
        if (users != null) {
            for (EventListener listener : users) {
                listener.update(eventType, data);
            }
        }
    }
}
