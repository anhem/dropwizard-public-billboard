package com.example.billboard.resources;

import com.example.billboard.api.MessageJson;
import com.example.billboard.core.service.MessageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageResourceTest {

    public static final MessageJson MESSAGE_JSON_1 = new MessageJson("subject", "text", "name");
    public static final MessageJson MESSAGE_JSON_2 = new MessageJson("subject2", "text2", "name2");
    public static final List<MessageJson> MESSAGES = Arrays.asList(MESSAGE_JSON_1, MESSAGE_JSON_2);

    private static MessageService messageService = mock(MessageService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new MessageResource(messageService))
            .build();

    @Before
    public void setup() {
        when(messageService.getAll()).thenReturn(MESSAGES);
    }

    @Test
    public void getAllMessagesReturnTwoMessages() throws IOException {
        String json = resources.client().resource("/message/list").get(String.class);
        List<MessageJson> messages = new ObjectMapper().readValue(json, new TypeReference<List<MessageJson>>() {
        });

        assertEquals(2, messages.size());
        assertEquals(MESSAGE_JSON_1, messages.get(0));
        assertEquals(MESSAGE_JSON_2, messages.get(1));
    }

}
