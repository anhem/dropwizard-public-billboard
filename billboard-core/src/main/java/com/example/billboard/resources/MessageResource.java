package com.example.billboard.resources;

import com.example.billboard.api.MessageJson;
import com.example.billboard.core.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        return messageService.getAll();
    }
}
