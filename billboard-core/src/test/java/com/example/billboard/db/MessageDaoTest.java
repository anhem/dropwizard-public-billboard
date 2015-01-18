package com.example.billboard.db;

import com.example.billboard.core.model.Message;
import org.joda.time.DateTime;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class MessageDaoTest {

    @ClassRule
    public static DatabaseRule database = new DatabaseRule();
    MessageDao messageDao = database.dbi.onDemand(MessageDao.class);

    @Test
    public void insertAndGetAll() {
        Message message = new Message(0, new DateTime(), "subject", "text", "name");
        messageDao.insert(message);
        List<Message> messages = messageDao.getAll();

        assertEquals(1, messages.size());

        Message readMessage = messages.get(0);
        assertNotEquals(message.getId(), readMessage.getId());
        assertNotNull(message.getCreated());
        assertEquals(message.getSubject(), readMessage.getSubject());
        assertEquals(message.getText(), readMessage.getText());
        assertEquals(message.getName(), readMessage.getName());
    }
}
