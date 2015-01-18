package com.example.billboard.db;

import com.example.billboard.core.model.Message;
import com.example.billboard.db.mapper.MessageMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(MessageMapper.class)
public interface MessageDao {

    @SqlUpdate("insert into message (created, subject, text, name) values (:created, :subject, :text, :name)")
    void insert(@BindBean Message message);

    @SqlQuery("select * from message order by created desc")
    List<Message> getAll();
}
