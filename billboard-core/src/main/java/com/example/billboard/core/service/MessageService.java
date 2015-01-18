package com.example.billboard.core.service;

import com.example.billboard.api.MessageJson;
import com.example.billboard.core.model.Message;
import com.example.billboard.db.MessageDao;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<MessageJson> getAll() {
        List<Message> messages = messageDao.getAll();
        List<MessageJson> messageJsons = new ArrayList<>();
        for (Message message : messages) {
            messageJsons.add(MessageJson.create(message));
        }
        return messageJsons;
    }
}
