import exercise.CsvDataLoaderService;
import exercise.Database;
import exercise.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class EbayApplication {
    public static void main(String[] args) {
        System.out.println("Hello World");

        try {
            CsvDataLoaderService reader = new CsvDataLoaderService();

            InputStream userFile = new FileInputStream(args[0]);
            Table userTable = reader.loadInput(userFile);

            InputStream purchaseFile = new FileInputStream(args[1]);
            Table purchaseTable = reader.loadInput(purchaseFile);

            Database database = new Database(Map.of(
                Constants.USER_TABLE, userTable,
                Constants.PURCHASE_TABLE, purchaseTable));

            System.out.println("----------------------USER TABLE--------------------------");
            database.getValues(Constants.USER_TABLE)
                .forEach(r -> System.out.println(r.printRow()));

            System.out.println("----------------------ORDERED USER TABLE--------------------------");
            database.getValuesOrderBy(Constants.USER_TABLE, Constants.USER_ID)
                .forEach(r -> System.out.println(r.printRow()));

            System.out.println("--------------------PURCHASE TABLE----------------------------");
            database.getValues(Constants.PURCHASE_TABLE)
                .forEach(r -> System.out.println(r.printRow()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
