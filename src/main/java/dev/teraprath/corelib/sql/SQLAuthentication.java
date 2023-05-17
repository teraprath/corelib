package dev.teraprath.corelib.sql;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class SQLAuthentication {

    public String HOST = "localhost";
    public int PORT = 3306;
    public String USER = "root";
    public String PASSWORD = "password";
    public String DATABASE = "database";

    public SQLAuthentication() {
        return;
    }

    public SQLAuthentication(@Nonnull String host, @Nonnegative int port, @Nonnull String user, @Nonnull String password, @Nonnull String database) {
        this.HOST = host;
        this.PORT = port;
        this.USER = user;
        this.PASSWORD = password;
        this.DATABASE = database;
    }

}

