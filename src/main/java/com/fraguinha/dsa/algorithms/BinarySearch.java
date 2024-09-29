package com.fraguinha.dsa.algorithms;

import java.util.List;
import java.util.Optional;

/**
 * Binary search.
 *
 */
public abstract class BinarySearch {

  /**
   * Binary search.
   *
   * @param <E>     the type of elements.
   * @param list    the list to be searched.
   * @param element the element being searched.
   * @return an optional index of the element in the list if it is present.
   */
  public static <E extends Comparable<E>> Optional<Integer> search(List<E> list, E element) {
    int low = 0;
    int high = list.size() - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (list.get(mid).compareTo(element) == 0) {
        return Optional.of(mid);
      }

      if (list.get(mid).compareTo(element) < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return Optional.empty();
  }

}
