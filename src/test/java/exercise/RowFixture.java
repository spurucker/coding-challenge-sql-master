package exercise;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class RowFixture {

    public static List<Row> joinedRows(){
        return asList(joinedRow());
    }

    public static List<Row> notOrderedUserRows(){
        return asList(
            userRow2(),
            userRow1(),
            userRow4(),
            userRow3(),
            userRow5()

        );
    }

    public static List<Row> orderedUserRows(){
        return asList(
            userRow1(),
            userRow2(),
            userRow3(),
            userRow4(),
            userRow5()

        );
    }

    public static Row userRow1(){
        return new Row(Map.of("USER_ID", "1",
            "NAME", "andre",
            "EMAIL", "andre@bar.de"));
    }

    public static Row userRow2(){
        return new Row(Map.of("USER_ID", "2",
            "NAME", "manuel",
            "EMAIL", "manuel@foo.de"));
    }

    public static Row userRow3(){
        return new Row(Map.of("USER_ID", "3",
            "NAME", "swen",
            "EMAIL", "swen@foo.de"));
    }

    public static Row userRow4(){
        return new Row(Map.of("USER_ID", "4",
            "NAME", "lydia",
            "EMAIL", "lydia@bar.de"));
    }

    public static Row userRow5(){
        return new Row(Map.of("USER_ID", "5",
            "NAME", "paul",
            "EMAIL", "paul@foo.de"));
    }

    public static Row joinedRow(){
        return new Row(Map.of("USER_ID", "2",
            "NAME", "manuel",
            "EMAIL", "manuel@foo.de",
            "AD_ID", "1",
            "TITLE", "car-1"));
    }
}
