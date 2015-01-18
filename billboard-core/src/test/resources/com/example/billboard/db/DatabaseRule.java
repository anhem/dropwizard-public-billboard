package com.example.billboard.db;

import com.codahale.metrics.MetricRegistry;
import com.example.billboard.BillboardCoreConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.rules.ExternalResource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class DatabaseRule extends ExternalResource {

    private Liquibase liquibase;
    private Handle handle;
    private Database database;
    protected DBI dbi;

    @Override
    public void before() throws Exception {
        Environment environment = new Environment("test", Jackson.newObjectMapper(), null, new MetricRegistry(), null);
        dbi = new DBIFactory().build(environment, getConfiguration().getDataSourceFactory(), "test");
        handle = dbi.open();
        database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(handle.getConnection()));
        migrate();
    }

    @Override
    public void after() {
        try {
            dropAndClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BillboardCoreConfiguration getConfiguration() {
        BillboardCoreConfiguration configuration = new BillboardCoreConfiguration();
        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        dataSourceFactory.setDriverClass("org.h2.Driver");
        dataSourceFactory.setUrl("jdbc:h2:mem:billboard");
        /*
        dataSourceFactory.setDriverClass("com.mysql.jdbc.Driver");
        dataSourceFactory.setUrl("jdbc:mysql://localhost:3306/billboard");
        dataSourceFactory.setUser("billboard");
        dataSourceFactory.setPassword("billboard_passwd");
        */
        return configuration;
    }

    private void migrate() throws LiquibaseException {
        liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("");
    }

    private void dropAndClose() throws DatabaseException, LockException {
        liquibase.dropAll();
        handle.close();
    }
}
