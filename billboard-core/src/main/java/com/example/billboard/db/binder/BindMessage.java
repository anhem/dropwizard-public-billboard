package com.example.billboard.db.binder;

import com.example.billboard.core.model.Message;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(BindMessage.MessageBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindMessage {

    public static class MessageBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation) {
            return new Binder<BindMessage, Message>() {
                public void bind(SQLStatement q, BindMessage bind, Message message) {
                    q.bind("created", message.getCreated().toDate());
                    q.bind("subject", message.getSubject());
                    q.bind("text", message.getText());
                    q.bind("name", message.getName());
                }
            };
        }
    }
}
