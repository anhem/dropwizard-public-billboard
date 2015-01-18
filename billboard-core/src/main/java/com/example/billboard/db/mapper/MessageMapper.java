package com.example.billboard.db.mapper;

import com.example.billboard.core.model.Message;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements ResultSetMapper<Message> {

    @Override
    public Message map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new Message(
                r.getInt("id"),
                new DateTime(r.getTimestamp("created")),
                r.getString("subject"),
                r.getString("text"),
                r.getString("name")
        );
    }
}
