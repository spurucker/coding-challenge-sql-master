package exercise;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static exercise.RowFixture.orderedUserRows;
import static org.assertj.core.api.Assertions.assertThat;

class IntegrationTest {

    @Test
    void getOrderedUsers() throws Exception {
        List<Row> expected = orderedUserRows();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("users.csv").getFile());
        CsvDataLoaderService reader = new CsvDataLoaderService();
        Table table = reader.loadInput(file);

        List<Row> orderedRows = table.getOrderBy("USER_ID");

        List<Row> oldRows = table.getRows();

        assertThat(oldRows).isEqualTo(oldRows);
        for(int i = 0; i < orderedRows.size(); i++) {
            assertThat(orderedRows.get(i).printRow())
                .isEqualTo(expected.get(i).printRow());
        }
    }

}
