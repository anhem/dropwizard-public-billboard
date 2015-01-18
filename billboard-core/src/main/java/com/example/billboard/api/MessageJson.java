package com.example.billboard.api;

import com.example.billboard.core.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class MessageJson {

    private Date created;

    @NotEmpty
    private String subject;
    @NotEmpty
    private String text;
    @NotEmpty
    private String name;

    public static MessageJson create(Message message) {
        return new MessageJson(
                message.getSubject(),
                message.getText(),
                message.getName()
        );
    }

    public MessageJson(@JsonProperty("subject") String subject, @JsonProperty("text") String text, @JsonProperty("name") String name) {
        this.created = new Date();
        this.subject = subject;
        this.text = text;
        this.name = name;
    }

}
