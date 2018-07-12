package com.sambafedior.upscore.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
public class User {

    private String name;
    private String firstName;
    private String phone;
    private String password;
    private String email;
    private boolean isActive;
    private boolean isReported;
    private boolean isBlocked;
    private Map<String, String> createdAt;
    private Map<String, String>  updatedAt;

    public User() {
    }

    public User(String name, String firstName, String phone, String password, String email, boolean isActive, boolean isReported, boolean isBlocked, Map<String, String> createdAt, Map<String, String> updatedAt) {
        this.name = name;
        this.firstName = firstName;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.isReported = isReported;
        this.isBlocked = isBlocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isReported() {
        return isReported;
    }

    public void setReported(boolean reported) {
        isReported = reported;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Map<String, String> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Map<String, String> createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, String> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Map<String, String> updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", isReported=" + isReported +
                ", isBlocked=" + isBlocked +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
