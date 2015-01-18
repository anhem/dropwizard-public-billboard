package com.example.billboard.core.service;

import com.example.billboard.api.MessageJson;
import com.example.billboard.core.model.Message;
import com.example.billboard.db.MessageDao;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    MessageDao messageDao = mock(MessageDao.class);
    MessageService messageService;

    public static final Message MESSAGE = new Message(1, new DateTime(), "subject", "text", "name");

    @Before
    public void setup() {
        messageService = new MessageService(messageDao);
    }

    @Test
    public void getAllReturnsListWithOneMessage() {
        when(messageDao.getAll()).thenReturn(Arrays.asList(MESSAGE));
        List<MessageJson> messageJsons = messageService.getAll();

        assertEquals(1, messageJsons.size());

        MessageJson messageJson = messageJsons.get(0);
        assertEquals(MESSAGE.getCreated(), messageJson.getCreated());
        assertEquals(MESSAGE.getSubject(), messageJson.getSubject());
        assertEquals(MESSAGE.getName(), messageJson.getName());
        assertEquals(MESSAGE.getText(), messageJson.getText());
    }
}
