package com.example.billboard.core.model;

import org.joda.time.DateTime;

public class Message {

    private final int id;
    private final DateTime created;
    private final String subject;
    private final String text;
    private final String name;

    public Message(int id, DateTime created, String subject, String text, String name) {
        this.id = id;
        this.created = created;
        this.subject = subject;
        this.text = text;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public DateTime getCreated() {
        return created;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }
}
