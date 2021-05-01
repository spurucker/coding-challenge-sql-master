package exercise;

import exceptions.ConstructorException;
import exceptions.ValidationException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Objects.isNull;

@Data
public class Table {
    private final List<String> schema;
    private final List<Row> rows;

    public Table(List<String> schema, List<Row> rows){
        if(isNull(schema)){
            throw new ConstructorException("Cannot create Row, schema is mandatory");
        }
        if(isNull(rows)){
            throw new ConstructorException("Cannot create Row, rows are mandatory");
        }
        this.schema = schema;
        this.rows = rows;
    }

    public List<Row> getOrderBy(String columnName) throws Exception {
        validateColumnName(columnName);
        List<Row> orderedRows = new ArrayList<>(rows);
        orderedRows.sort(comparing(r -> r.get(columnName)));
        return orderedRows;
    }

    private void validateColumnName(String columnName) throws Exception {
        if(!schema.contains(columnName)){
            throw new ValidationException(String.format("%s is an invalid column name", columnName));
        }
    }
}
