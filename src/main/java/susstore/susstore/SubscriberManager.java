package susstore.susstore;

import java.util.ArrayList;
import java.util.List;
public class SubscriberManager {
    List<Subscriber> subs = new ArrayList<>();

    public SubscriberManager() {
    }

    public void subscribe(Subscriber subscriber) {
        subs.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subs.remove(subscriber);
    }

    public void notify(String eventType) {
        for (Subscriber subscriber : subs) {
            subscriber.update();
        }
    }
}
