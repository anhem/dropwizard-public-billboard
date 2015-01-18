package com.example.billboard.core.service;

import com.example.billboard.core.model.Message;
import com.example.billboard.db.MessageDao;

import java.util.List;

public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getAll() {
        return messageDao.getAll();
    }
}
