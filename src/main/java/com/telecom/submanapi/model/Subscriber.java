package com.telecom.submanapi.model;

import com.telecom.submanapi.enums.SubscriberStatus;
import jakarta.validation.constraints.*;

public class Subscriber {

private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Plan is mandatory")
    @Pattern(regexp = "^(?i)(Basic|Premium|Enterprise)$", message = "Plan must be one of Basic, Premium, or Enterprise")
    private String plan;


    // Default to ACTIVE when not provided
    private SubscriberStatus status = SubscriberStatus.ACTIVE;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        if (plan == null) {
            this.plan = null;
            return;
        }
        // normalize plan to capitalize first letter (Basic/Premium/Enterprise)
        String normalized = plan.trim().toLowerCase();
        switch (normalized) {
            case "basic":
                this.plan = "Basic";
                break;
            case "premium":
                this.plan = "Premium";
                break;
            case "enterprise":
                this.plan = "Enterprise";
                break;
            default:
                this.plan = plan;
        }
    }

    public SubscriberStatus getStatus() {
        return status;
    }
    public void setStatus(SubscriberStatus status) {
        if (status == null) {
            // keep existing default
            return;
        }
        this.status = status;
    }
}
