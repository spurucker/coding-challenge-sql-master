package exercise;

import exceptions.ConstructorException;
import exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static exercise.RowFixture.notOrderedUserRows;
import static exercise.RowFixture.orderedUserRows;
import static exercise.SchemaFixture.userSchema;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TableTest {

    @Test
    public void NewTable(){
        new Table(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void NewTableNoSchema(){
        assertThatThrownBy(() -> new Table(null, new ArrayList<>()))
            .isInstanceOf(ConstructorException.class)
            .hasMessage("Cannot create Row, schema is mandatory");
    }

    @Test
    public void NewTableNoRows(){
        assertThatThrownBy(() -> new Table(new ArrayList<>(), null))
            .isInstanceOf(ConstructorException.class)
            .hasMessage("Cannot create Row, rows are mandatory");
    }

    @Test
    public void getOrderBy() throws Exception {
        List<Row> notOrderedUserRows = notOrderedUserRows();
        List<Row> expected = orderedUserRows();

        Table table = new Table(userSchema(), notOrderedUserRows);

        List<Row> got = table.getOrderBy("USER_ID");

        assertThat(got)
            .hasSize(notOrderedUserRows.size());

        for(int i = 0; i < got.size(); i++) {
            assertThat(got.get(i).printRow())
                .isEqualTo(expected.get(i).printRow());
        }
    }

    @Test
    public void getOrderByInvalidColumnName() {
        List<Row> notOrderedUserRows = notOrderedUserRows();

        Table table = new Table(userSchema(), notOrderedUserRows);

        assertThatThrownBy(() -> table.getOrderBy("SOME_OTHER_NAME"))
            .hasMessage("SOME_OTHER_NAME is an invalid column name")
            .isInstanceOf(ValidationException.class);
    }
}
