package dev.teraprath.corelib.sql;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class SQLAuthentication {

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final String database;

    public SQLAuthentication(@Nonnull String host, @Nonnegative int port, @Nonnull String user, @Nonnull String password, @Nonnull String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDatabase() {
        return this.database;
    }

}
