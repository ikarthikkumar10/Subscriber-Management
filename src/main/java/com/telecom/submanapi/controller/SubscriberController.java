package com.telecom.submanapi.controller;

import com.telecom.submanapi.model.Subscriber;
import com.telecom.submanapi.service.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    // GET all subscribers
    @GetMapping
    public ResponseEntity<List<Subscriber>> getAllSubscribers() {
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        return ResponseEntity.ok(subscribers);
    }

    // GET subscriber by ID
    @GetMapping("/{id}")
    public ResponseEntity<Subscriber> getSubscriberById(@PathVariable Long id) {
        Subscriber subscriber = subscriberService.getSubscriberById(id);
        if (subscriber == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subscriber);
    }
    // POST create subscriber
    @PostMapping
    public ResponseEntity<Subscriber> createSubscriber(@Valid @RequestBody Subscriber subscriber) {
        Subscriber created = subscriberService.createSubscriber(subscriber);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    // PUT update subscriber
    @PutMapping("/{id}")
    public ResponseEntity<Subscriber> updateSubscriber(
            @PathVariable Long id,
            @Valid @RequestBody Subscriber subscriber) {
        Subscriber updated = subscriberService.updateSubscriber(id, subscriber);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE subscriber
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        boolean deleted = subscriberService.deleteSubscriber(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // GET subscribers by status
    @GetMapping("/status")
    public ResponseEntity<List<Subscriber>> getSubscribersByStatus(@RequestParam String status) {
        List<Subscriber> subscribers = subscriberService.getSubscribersByStatus(status);
        return ResponseEntity.ok(subscribers);
    }

    // GET subscribers by plan
    @GetMapping("/plan")
    public ResponseEntity<List<Subscriber>> getSubscribersByPlan(@RequestParam String plan) {
        List<Subscriber> subscribers = subscriberService.getSubscribersByPlan(plan);
        return ResponseEntity.ok(subscribers);
    }

    // GET subscriber count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getSubscriberCount() {
        long count = subscriberService.getSubscriberCount();
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    // Search subscribers by name
    // GET /api/subscribers/search?name=John
    @GetMapping("/search")
    public ResponseEntity<List<Subscriber>> searchSubscribers(
            @RequestParam String name) {
        List<Subscriber> results = subscriberService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    // Additional endpoints can be added here as needed



}
