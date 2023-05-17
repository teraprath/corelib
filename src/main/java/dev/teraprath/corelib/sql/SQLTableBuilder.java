package dev.teraprath.corelib.sql;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class SQLTableBuilder {

    private final String name;
    private final ArrayList<String> columns;
    private String primary;

    public SQLTableBuilder(@Nonnull String name) {
        this.name = name;
        this.columns = new ArrayList();
    }

    public SQLTableBuilder addColumn(@Nonnull String name, @Nonnull DataType type, boolean notNull) {
        return addColumn(name, type, notNull, null, "");
    }

    public SQLTableBuilder addColumn(@Nonnull String name, @Nonnull DataType type, @Nonnull boolean notNull, Object defaultValue) {
        return addColumn(name, type, notNull, defaultValue, "");
    }

    public SQLTableBuilder addColumn(@Nonnull String name, @Nonnull DataType type, boolean notNull, Object defaultValue, Object... params) {
        String column = name + " " + type.name()
                + (params[0] != "" ? "(" + (Arrays.toString(params)).replace("[", "").replace("]", "") + ")" : "")
                + (notNull ? " NOT NULL" : "")
                + (defaultValue == null ? "" : " DEFAULT " + defaultValue);
        this.columns.add(column);
        return this;
    }

    public SQLTableBuilder setPrimary(@Nonnull String columnName) {
        this.primary = columnName;
        return this;
    }

    public String build() {
        String table = "CREATE TABLE IF NOT EXISTS " + this.name;
        if (this.columns.size() > 0) {

            AtomicReference<String> statement = new AtomicReference<>();

            this.columns.forEach(col -> {
                statement.set(statement + col + ", ");
            });

            table = table + " (" + statement;

            if (primary == null) {
                return table + ")";
            }

            return format(table + "PRIMARY KEY (" + primary + "))");


        }
        return format(table);
    }

    private String format(@Nonnull String string) {
        return string.replace("(null", "(");
    }

}
