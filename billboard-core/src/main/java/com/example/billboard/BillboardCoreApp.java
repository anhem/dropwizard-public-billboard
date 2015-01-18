package com.example.billboard;

import com.example.billboard.core.service.MessageService;
import com.example.billboard.db.MessageDao;
import com.example.billboard.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class BillboardCoreApp extends Application<BillboardCoreConfiguration> {

    public static void main(String[] args) throws Exception {
        new BillboardCoreApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<BillboardCoreConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<BillboardCoreConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BillboardCoreConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(BillboardCoreConfiguration config, Environment environment) throws Exception {
        DBIFactory factory = new DBIFactory();
        DBI dbi = factory.build(environment, config.getDataSourceFactory(), "sql");

        //Daos
        MessageDao messageDao = dbi.onDemand(MessageDao.class);

        //Services
        MessageService messageService = new MessageService(messageDao);

        //Resources
        MessageResource messageResource = new MessageResource(messageService);

        environment.jersey().register(messageResource);
    }
}
