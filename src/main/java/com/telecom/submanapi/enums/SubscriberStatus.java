package com.telecom.submanapi.enums;

public enum SubscriberStatus {
    ACTIVE,
    SUSPENDED,
    CANCELLED;

    // Parse a string into a SubscriberStatus (case-insensitive). Returns null if unknown.
    public static SubscriberStatus fromString(String status) {
        if (status == null) return null;
        for (SubscriberStatus s : values()) {
            if (s.name().equalsIgnoreCase(status)) return s;
        }
        // allow some common legacy mappings if needed
        if ("create".equalsIgnoreCase(status) || "created".equalsIgnoreCase(status)) {
            return ACTIVE;
        }
        if ("suspend".equalsIgnoreCase(status)) return SUSPENDED;
        if ("cancel".equalsIgnoreCase(status)) return CANCELLED;
        return null;
    }

    // Keep a convenience method to compare ignoring case
    public boolean equalsIgnoreCase(String status) {
        return this.name().equalsIgnoreCase(status);
    }
}