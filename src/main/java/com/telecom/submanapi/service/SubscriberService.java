package com.telecom.submanapi.service;

import com.telecom.submanapi.model.Subscriber;
import org.springframework.stereotype.Service;
import com.telecom.submanapi.exception.ResourceNotFoundHandler;
import com.telecom.submanapi.enums.SubscriberStatus;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SubscriberService {
    // In-memory data store
    private final Map<Long, Subscriber> subscriberStore = new HashMap<>();

    // AtomicLong for generating unique IDs
    private final AtomicLong idGenerator = new AtomicLong(1);
    // Get all subscribers
    public List<Subscriber> getAllSubscribers() {
        return new ArrayList<>(subscriberStore.values());
    }
    // Get subscriber by ID
    public Subscriber getSubscriberById(Long id) {
        Subscriber subscriber = subscriberStore.get(id);
        if (subscriber == null) {
            throw new ResourceNotFoundHandler("Subscriber not found with id: " + id);
        }
        return subscriber;
    }
    // Create new subscriber
    public Subscriber createSubscriber(Subscriber subscriber) {
        Long id = idGenerator.getAndIncrement();
        subscriber.setId(id);
        // Ensure status is set to ACTIVE if caller didn't provide one
        if (subscriber.getStatus() == null) {
            subscriber.setStatus(SubscriberStatus.ACTIVE);
        }
        // Normalize plan as well (Subscriber.setPlan already does normalization)
        subscriberStore.put(id, subscriber);
        return subscriber;
    }
    // Update subscriber
    public Subscriber updateSubscriber(Long id, Subscriber subscriberDetails) {
        Subscriber subscriber = subscriberStore.get(id);
        if (subscriber == null) {
            throw new ResourceNotFoundHandler("Subscriber not found with id: " + id);
        }
        subscriber.setName(subscriberDetails.getName());
        subscriber.setEmail(subscriberDetails.getEmail());
        subscriberStore.put(id, subscriber);
        return subscriber;
    }
    // Delete subscriber
    public boolean deleteSubscriber(Long id) {
        Subscriber subscriber = subscriberStore.get(id);
        if (subscriber == null) {
            throw new ResourceNotFoundHandler("Subscriber not found with id: " + id);
        }
        subscriberStore.remove(id);
        return true;
    }

    // search subscribers by name
    public List<Subscriber> searchSubscribersByName(String name) {
        return subscriberStore.values().stream()
                .filter(subscriber -> subscriber.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    // filter subscribers by email domain
    public List<Subscriber> filterSubscribersByEmailDomain(String domain) {
        return subscriberStore.values().stream()
                .filter(subscriber -> subscriber.getEmail().toLowerCase().endsWith("@" + domain.toLowerCase()))
                .toList();
    }

    // get subscriber count
    public long getSubscriberCount() {
        return subscriberStore.size();
    }

    // get subscriber by plan
    public List<Subscriber> getSubscribersByPlan(String plan) {
        return subscriberStore.values().stream()
                .filter(subscriber -> subscriber.getPlan() != null && subscriber.getPlan().equalsIgnoreCase(plan))
                .toList();
    }

    public List<Subscriber> getSubscribersByStatus(String status) {
        return subscriberStore.values().stream()
                .filter(subscriber -> {
                    if (subscriber.getStatus() == null) return false;
                    return subscriber.getStatus().name().equalsIgnoreCase(status);
                })
                .toList();
    }

    public List<Subscriber> searchByName(String name) {
        return subscriberStore.values().stream()
                .filter(subscriber -> subscriber.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }


}
