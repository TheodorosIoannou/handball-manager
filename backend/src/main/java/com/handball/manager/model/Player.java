package com.handball.manager.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Player {
    private Long id;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotNull(message = "Position is required")
    private Position position;
    
    @NotNull(message = "Shirt number is required")
    @Min(value = 1, message = "Shirt number must be at least 1")
    @Max(value = 99, message = "Shirt number must be at most 99")
    private Integer shirtNumber;
    
    private boolean active = true;

    public enum Position {
        GOALKEEPER,
        LEFT_WING,
        RIGHT_WING,
        LEFT_BACK,
        RIGHT_BACK,
        CENTER_BACK,
        PIVOT
    }

    // Constructors
    public Player() {}

    public Player(Long id, String firstName, String lastName, Position position, 
                  Integer shirtNumber, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}