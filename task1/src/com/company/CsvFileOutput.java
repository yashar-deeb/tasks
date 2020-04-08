package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CsvFileOutput {
    static public void saveTariffToFile(TariffClass tariff, String nameOfCsvFile) {
        ManagerListDirectionItem manager = tariff.getManager();

        try (FileWriter output = new FileWriter(nameOfCsvFile, false)) {
            for (int i = 0; i < manager.getSize(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ManagerDirectionItem.getPrefix(manager.getItem(i)));
                stringBuilder.append(",");
                stringBuilder.append(ManagerDirectionItem.getNameOfDirection(manager.getItem(i)));
                stringBuilder.append(",");
                stringBuilder.append(ManagerDirectionItem.getPricePerMinute(manager.getItem(i)));
                stringBuilder.append(",");
                output.write(stringBuilder.toString());
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
