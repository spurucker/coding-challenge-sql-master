package exercise;

import exceptions.ConstructorException;
import exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;


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

    public List<Row> innerJoin(String tableLeft, String tableRight, String columnNameLeft, String columnNameRight){
        List<Row> result = new ArrayList<>();
        Table left = tables.get(tableLeft);
        Table right = tables.get(tableRight);
        left.getRows().forEach(
            lr-> right.getRows().forEach(rr -> {
                if(nonNull(lr.getValues().get(columnNameLeft))
                    && lr.getValues().get(columnNameLeft).equalsIgnoreCase(rr.get(columnNameRight)))
                {
                    Map<String, String> newValue = new HashMap<>();
                    newValue.putAll(rr.getValues());
                    newValue.putAll(lr.getValues());
                    result.add(new Row(newValue));
                }
        }));

        return result;
    }
}
