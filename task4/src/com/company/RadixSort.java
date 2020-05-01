package com.company;

import java.util.Random;

public class RadixSort {
    public void binaryRadixSort(int[] array) {
        final int binaryRank = 0b10000000000000000000000000000000;
        binaryRadixSort(array, 0, array.length - 1, binaryRank, true);
    }

    public void binaryRadixSort(int[] array, int low, int high, int binaryRank, boolean isNeg) { // isNeg - флаг для работы с отрицательными числами
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int i = low, j = high;
        int firstArg = isNeg ? binaryRank : 0;
        int secondArg = isNeg ? 0 : binaryRank;

        while (i <= j) {
            while (i <= high && (array[i] & binaryRank) == firstArg) {
                i++;
            }

            while (j >= low && (array[j] & binaryRank) == secondArg) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            binaryRadixSort(array, low, j, Math.abs(binaryRank >> 1), false);

        if (high > i)
            binaryRadixSort(array, i, high, Math.abs(binaryRank >> 1), false);
    }

    public void fillArrayWithRandomNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (new Random().nextInt());
        }
    }
}
