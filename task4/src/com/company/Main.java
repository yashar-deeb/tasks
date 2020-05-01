package com.company;

import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        final int[] amountArray = {10000, 20000, 50000, 100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000,
                900000, 1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 9000000, 10000000};
        final int length = amountArray.length;
        long[] firstData = new long[length];
        long[] secondData = new long[length];
        RadixSort radixSort = new RadixSort();

        for (int i = 0; i < length; i++) {
            int[] firstTestArray = new int[amountArray[i]];
            radixSort.fillArrayWithRandomNumbers(firstTestArray);
            int[] secondTestArray = firstTestArray.clone();

            long radixTime = new Date().getTime();
            radixSort.binaryRadixSort(firstTestArray);
            radixTime = new Date().getTime() - radixTime;

            firstData[i] = radixTime;

            long sortTime = new Date().getTime();
            Arrays.sort(secondTestArray);
            sortTime = new Date().getTime() - sortTime;

            secondData[i] = sortTime;
        }

        XYLineChart_AWT chart = new XYLineChart_AWT("", "", firstData, secondData, amountArray);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
