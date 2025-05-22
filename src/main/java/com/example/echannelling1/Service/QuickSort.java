package com.example.echannelling1.Service;

import com.example.echannelling1.model.Medicine;

import java.util.List;

public class QuickSort {

    public static void sort(List<Medicine> list) {
        if (list == null || list.size() <= 1) return;
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Medicine> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Medicine> list, int low, int high) {
        double pivot = list.get(high).getPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getPrice() < pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Medicine> list, int i, int j) {
        Medicine temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
