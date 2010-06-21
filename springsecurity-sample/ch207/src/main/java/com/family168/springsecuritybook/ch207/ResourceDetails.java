package com.family168.springsecuritybook.ch207;

public class ResourceDetails {
    private String name;
    private String role;

    public ResourceDetails() {
    }

    public ResourceDetails(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
