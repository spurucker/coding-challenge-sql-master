package exercise;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static exercise.Constants.PURCHASE_TABLE;
import static exercise.Constants.USER_ID;
import static exercise.Constants.USER_TABLE;
import static exercise.RowFixture.joinedRows;
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

    @Test
    void innerJoin() throws Exception {
        List<Row> expected = joinedRows();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test_users.csv").getFile());
        CsvDataLoaderService reader = new CsvDataLoaderService();
        Table userTable = reader.loadInput(file);

        file = new File(classLoader.getResource("test_purchases.csv").getFile());
        Table purchaseTable = reader.loadInput(file);

        Database database = new Database(Map.of(USER_TABLE, userTable,
            PURCHASE_TABLE, purchaseTable));

        List<Row> joinedRow = database.innerJoin(USER_TABLE, PURCHASE_TABLE, USER_ID, USER_ID);

        for(int i = 0; i < joinedRow.size(); i++) {
            assertThat(joinedRow.get(i).printRow())
                .isEqualTo(expected.get(i).printRow());
        }
    }

    @Test
    void innerJoinEmpty() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test_users.csv").getFile());
        CsvDataLoaderService reader = new CsvDataLoaderService();
        Table userTable = reader.loadInput(file);

        file = new File(classLoader.getResource("test_purchases.csv").getFile());
        Table purchaseTable = reader.loadInput(file);

        Database database = new Database(Map.of(USER_TABLE, userTable,
            PURCHASE_TABLE, purchaseTable));

        List<Row> joinedRow = database.innerJoin(USER_TABLE, PURCHASE_TABLE, "OTRO", "OTRO");

        assertThat(joinedRow).isEmpty();
    }
}
