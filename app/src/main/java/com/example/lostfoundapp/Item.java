package com.example.lostfoundapp;

public class Item {

    int id;

    String type;
    String name;
    String phone;
    String description;
    String date;
    String location;
    String category;
    String timestamp;

    public Item(int id,
                String type,
                String name,
                String phone,
                String description,
                String date,
                String location,
                String category,
                String timestamp) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.category = category;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
