package com.example.billboard.core.service;

import com.example.billboard.db.MessageDao;

public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
