package src.main.java;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(String eventType, String details) {
        for (EventListener l : listeners) {
            l.update(eventType, details);
        }
    }

    public EventManager getEventManager() {
        return this;
    }

    public void subscribe(String eventType, EventListener listener) {
        // In this simple implementation, we ignore eventType filtering
        listeners.add(listener);
    }
}
