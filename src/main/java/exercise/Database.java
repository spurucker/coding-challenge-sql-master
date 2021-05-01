package exercise;

import exceptions.ConstructorException;
import exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;


public class Database {
    private final Map<String, Table> tables;

    public Database(Map<String, Table> tables){
        if(isNull(tables)){
            throw new ConstructorException("Could not create Database, Tables are mandatory");
        }
        this.tables = tables;
    }

    public List<Row> getValues(String tableName) throws NotFoundException {
        if(isNull(tables.get(tableName))){
            throw new NotFoundException(String.format("Table %s was not found", tableName));
        }
        return tables.get(tableName).getRows();
    }

    public List<Row> getValuesOrderBy(String tableName, String columnName) throws Exception {
        if(isNull(tables.get(tableName))){
            throw new NotFoundException(String.format("Table %s was not found", tableName));
        }
        return tables.get(tableName).getOrderBy(columnName);
    }
}
