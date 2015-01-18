package com.example.billboard;

public class MigrateAndRunBootstrap {

    public static final String CONFIG = "config.yml";

    public static void main(String[] args) throws Exception {
        BillboardCoreApp.main(new String[]{"db", "migrate", CONFIG});
        BillboardCoreApp.main(new String[]{"server", CONFIG});
    }
}
