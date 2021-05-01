package exercise;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static exercise.RowFixture.userRow2;
import static exercise.SchemaFixture.userSchema;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvDataLoaderServiceTest {
  private final CsvDataLoaderService csvDataLoaderService = new CsvDataLoaderService();

  @Test
  public void loadInput() throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("test_users.csv");

    Table table = csvDataLoaderService.loadInput(inputStream);

    assertThat(table.getSchema())
        .isEqualTo(userSchema());

    assertThat(table.getRows())
        .hasSize(1);

    assertThat(table.getRows().get(0).printRow())
        .isEqualTo(userRow2().printRow());
  }
}
