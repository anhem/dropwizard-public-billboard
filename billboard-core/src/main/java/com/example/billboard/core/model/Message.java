package com.example.billboard.core.model;

import java.util.Date;

public class Message {

    private final int id;
    private final Date created;
    private final String subject;
    private final String text;
    private final String name;

    public Message(int id, Date created, String subject, String text, String name) {
        this.id = id;
        this.created = created;
        this.subject = subject;
        this.text = text;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
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
