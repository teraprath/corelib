package dev.teraprath.corelib.sql;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class SQLAuthentication {

    public String host = "localhost";
    public int port = 3306;
    public String user = "root";
    public String password = "password";
    public String database = "database";

    public SQLAuthentication() {
        return;
    }

    public SQLAuthentication(@Nonnull String host, @Nonnegative int port, @Nonnull String user, @Nonnull String password, @Nonnull String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

}

