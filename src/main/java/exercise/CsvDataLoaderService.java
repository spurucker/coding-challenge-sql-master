package exercise;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;

public class CsvDataLoaderService {
    public Table loadInput(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        return loadInput(is);
    }

    public Table loadInput(InputStream inputStream) throws IOException {
        /* EscapeChar and QuoteChar are mandatory, thus I assign characters that would be very unlikely to appear */
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withEscapeChar('Ç')
                .withQuoteChar('≠')
                .withIgnoreQuotations(true)
                .build();
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream, UTF_8))
                .withCSVParser(parser)
                .build();

        return readCSVReader(reader);
    }

    private Table readCSVReader(CSVReader reader) throws IOException {
        List<Row> list = new ArrayList<>();
        String[] line;
        List<String> schema = asList(reader.readNext());

        while ((line = reader.readNext()) != null) {
            Row row = new Row(new HashMap<>());

            for (int i = 0; i < line.length && i < schema.size(); i++) {
                row.put(schema.get(i), new String(line[i].getBytes(), UTF_8));
            }
            list.add(row);
        }
        return new Table(schema, list);
    }
}
