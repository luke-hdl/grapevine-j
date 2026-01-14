package com.grapevine.records.player;

import com.grapevine.records.sheet.experience.Experience;

import java.util.Date;

public class Player {
    protected String name;
    protected String id;
    protected String email;
    protected String phone;
    protected String position;
    protected String status;
    protected String address;
    protected Experience experience;
    protected String notes;
    protected Date lastModified;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("ID: ").append(id != null ? id : "").append("\r\n");
        sb.append("Email: ").append(email != null ? email : "").append("\r\n");
        sb.append("Phone: ").append(phone != null ? phone : "").append("\r\n");
        sb.append("Position: ").append(position != null ? position : "").append("\r\n");
        sb.append("Status: ").append(status != null ? status : "").append("\r\n");
        sb.append("Address: ").append(address != null ? address : "").append("\r\n");
        if (experience != null) {
            sb.append("Experience: ").append(experience.toDetailedString());
        }
        sb.append("Notes: ").append(notes != null ? notes : "").append("\r\n");
        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");
        return sb.toString();
    }
}
