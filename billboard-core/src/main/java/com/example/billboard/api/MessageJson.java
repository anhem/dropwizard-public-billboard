package com.example.billboard.api;

import com.example.billboard.core.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class MessageJson {

    private DateTime created;

    @NotEmpty
    private String subject;
    @NotEmpty
    private String text;
    @NotEmpty
    private String name;

    public static MessageJson create(Message message) {
        MessageJson messageJson = new MessageJson(
                message.getSubject(),
                message.getText(),
                message.getName()
        );
        messageJson.setCreated(message.getCreated());
        return messageJson;
    }

    public MessageJson(@JsonProperty("subject") String subject, @JsonProperty("text") String text, @JsonProperty("name") String name) {
        this.created = new DateTime();
        this.subject = subject;
        this.text = text;
        this.name = name;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
