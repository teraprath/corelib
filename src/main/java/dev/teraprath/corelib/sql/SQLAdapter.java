package dev.teraprath.corelib.sql;

import javax.annotation.Nonnull;
import java.sql.*;

public class SQLAdapter {

    private final SQLAuthentication authentication;
    private Connection connection;

    public SQLAdapter(@Nonnull SQLAuthentication authentication) {
        this.authentication = authentication;
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + authentication.HOST + ":" + authentication.PORT + "/" + authentication.DATABASE + "?autoReconnect=true", authentication.USER, authentication.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.hasConnection()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasConnection() {
        return this.connection != null;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void update(String sql) {
        try {
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        try {
            PreparedStatement st = this.connection.prepareStatement(sql);
            return st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
