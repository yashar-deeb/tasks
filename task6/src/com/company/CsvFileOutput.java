package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CsvFileOutput {
    static public void saveTariffToFile(TariffClass tariff, String nameOfCsvFile) {
        ManagerListDirectionItem manager = tariff.getManager();

        try (FileWriter output = new FileWriter(nameOfCsvFile, false)) {
            Iterator<Map.Entry<String, DirectionItem>> it = manager.getEntrySet().iterator();
            for (int i = 0; i < manager.getSize(); i++) {
                DirectionItem di = it.next().getValue();
                String stringBuilder = di.getPrefix() +
                        "," +
                        di.getNameOfDirection() +
                        "," +
                        di.getPricePerMinute() +
                        ",";
                output.write(stringBuilder);
                output.append('\n');
            }
            output.flush();
        } catch (IOException ex) {
            System.err.println("Invalid Path");
        }
    }

    static public void readFileToTariff(TariffClass tariff, String nameOfCsvFile) {
        ManagerListDirectionItem manager = tariff.getManager();

        try (Scanner fileScanner = new Scanner(new File(nameOfCsvFile))) {
            while (fileScanner.hasNextLine()) {
                String[] values = fileScanner.nextLine().split(",");
                DirectionItem item = new DirectionItem(values[0], values[1], Double.parseDouble(values[2]));
                manager.addItem(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid path");
        }
    }
}
