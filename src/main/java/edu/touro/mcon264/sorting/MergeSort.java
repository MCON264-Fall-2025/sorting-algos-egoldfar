package edu.touro.mcon264.sorting;

import java.util.Comparator;

public class MergeSort implements Sorter {

    @Override
    public <T> void sort(T[] a, Comparator<? super T> comp) {
        if (a == null || a.length <= 1) {
            return;
        }
        int partAStart = 0;
        int partAEnd = a.length/2;
        int partBStart = partAEnd + 1;
        int partBEnd = a.length - 1;

        recursiveSort(a, partAStart, partAEnd, comp);
        recursiveSort(a, partBStart, partBEnd, comp);
        merge(a, partAStart, partAEnd, partBEnd, comp);
    }

    public <T> void recursiveSort(T[] a, int start, int end, Comparator<? super T> comp) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        recursiveSort(a, start, mid, comp);
        recursiveSort(a, mid + 1, end, comp);
        merge(a, start, mid, end, comp);
    }

    private <T> void merge(T[] a, int start, int mid, int end, Comparator<? super T> comp) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        T[] left = (T[]) new Object[leftSize];
        T[] right = (T[]) new Object[rightSize];

        System.arraycopy(a, start, left, 0, leftSize);
        System.arraycopy(a, mid + 1, right, 0, rightSize);

        int i = 0, j = 0, k = start;
        while (i < leftSize && j < rightSize) {
            if (comp.compare(left[i], right[j]) <= 0) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            a[k++] = left[i++];
        }
        while (j < rightSize) {
            a[k++] = right[j++];
        }
    }

}