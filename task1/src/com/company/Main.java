package com.company;

public class Main {

    public static void main(String[] args) {
        TariffClass tariff = new TariffClass();
        CsvFileOutput.readFileToTariff(tariff, "D:/testValue.csv");
        tariff.getManager().addItem(new DirectionItem("1","sd", 2.1));
        CsvFileOutput.saveTariffToFile(tariff,"D:/testValue1.csv");
        System.out.println(tariff.calcCostOfCall("78923", 12));
    }
}
