package com.example.billboard.resources;

import com.example.billboard.api.MessageJson;
import com.example.billboard.core.model.Message;
import com.example.billboard.core.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/message")
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GET
    @Path(value = "/list")
    @Produces(APPLICATION_JSON)
    public List<MessageJson> list() {
        List<Message> messages = messageService.getAll();
        List<MessageJson> messageJsons = new ArrayList<>();
        for (Message message : messages) {
            messageJsons.add(MessageJson.create(message));
        }
        return messageJsons;
    }
}
