package exercise;

import exceptions.ConstructorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class Row {
    private final Map<String, String> values;

    public Row(Map<String, String> values) throws ConstructorException {
        if(isNull(values)){
            throw new ConstructorException("Cannot create Row, values are mandatory");
        }
        this.values = values;
    }
    public void put(String key, String value) {
        this.values.put(key, value);
    }

    public String get(String key) {
        return this.values.get(key);
    }

    public Map<String, String> getValues(){
        return values;
    }

    public String printRow(){
        List<String> sortedKeys=new ArrayList(values.keySet());
        Collections.sort(sortedKeys);
        return sortedKeys.stream()
            .map(values::get)
            .collect(Collectors.joining(", ", "{", "}"));
    }
}
