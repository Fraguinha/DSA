package com.fraguinha.dsa.algorithms;

import java.util.List;

/**
 * Quick sort.
 *
 */
public class QuickSort {

  /**
   * Sort.
   *
   * @param <E>  the type of elements.
   * @param list the list to be sorted.
   */
  public static <E extends Comparable<E>> void sort(List<E> list) {
    quicksort(list, 0, list.size() - 1);
  }

  /**
   * Quick sort.
   *
   * @param <E>  the type of elements.
   * @param list the list being sorted.
   * @param low  the lower limit of the partition being sorted.
   * @param high the higher limit of the partition being sorted.
   */
  private static <E extends Comparable<E>> void quicksort(List<E> list, int low, int high) {
    if (low < high) {
      int pivot = partition(list, low, high);
      quicksort(list, low, pivot - 1);
      quicksort(list, pivot + 1, high);
    }
  }

  /**
   * Partition.
   *
   * @param <E>  the type of elements.
   * @param list the list being partitioned.
   * @param low  the lower limit of the partition.
   * @param high the higher limit of the partition.
   */
  private static <E extends Comparable<E>> int partition(List<E> list, int low, int high) {
    E pivot = list.get(high);
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (list.get(j).compareTo(pivot) < 0) {
        i += 1;
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
      }
    }

    E temp = list.get(i + 1);
    list.set(i + 1, list.get(high));
    list.set(high, temp);

    return i + 1;
  }

}
