package com.example.billboard.db.binder;

import com.example.billboard.core.model.Message;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: andreas
 * Date: 2015-01-18
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
@BindingAnnotation(MessageBinder.MessageBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})

public @interface MessageBinder
{

    public static class MessageBinderFactory implements BinderFactory
    {
        public Binder build(Annotation annotation)
        {
            return new Binder<MessageBinder, Message>()
            {
                public void bind(SQLStatement q, MessageBinder bind, Message arg)
                {
                    q.bind("ident", arg.getId());
                    q.bind("nom", arg.getName());
                }
            };
        }
    }
}
