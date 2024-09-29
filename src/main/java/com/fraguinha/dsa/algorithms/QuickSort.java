package com.fraguinha.dsa.algorithms;

import java.util.List;

/**
 * QuickSort<br>
 * <br>
 * Delete and re-implement these methods, then run unit tests
 */
public class QuickSort {

  /**
   * Sort
   *
   * @param <E>  the type of elements
   * @param list the list to be sorted
   */
  public static <E extends Comparable<E>> void sort(List<E> list) {
    quicksort(list, 0, list.size() - 1);
  }

  /**
   * QuickSort
   *
   * @param <E>  the type of elements
   * @param list the list being sorted
   * @param low  the lower limit of the partition being sorted
   * @param high the higher limit of the partition being sorted
   */
  private static <E extends Comparable<E>> void quicksort(List<E> list, int low, int high) {
    if (low < high) {
      int pivot = partition(list, low, high);
      quicksort(list, low, pivot - 1);
      quicksort(list, pivot + 1, high);
    }
  }

  /**
   * Partition
   *
   * @param <E>  the type of elements
   * @param list the list being partitioned
   * @param low  the lower limit of the partition
   * @param high the higher limit of the partition
   */
  private static <E extends Comparable<E>> int partition(List<E> list, int low, int high) {
    E pivot = list.get(high);

    while (true) {
      while (list.get(low).compareTo(pivot) < 0) {
        low += 1;
      }

      while (list.get(high).compareTo(pivot) > 0) {
        high -= 1;
      }

      if (low >= high) {
        return high;
      }

      E temp = list.get(low);
      list.set(low, list.get(high));
      list.set(high, temp);
    }
  }

}
