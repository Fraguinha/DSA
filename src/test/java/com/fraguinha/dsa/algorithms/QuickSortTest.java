package com.fraguinha.dsa.algorithms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.Test;

/**
 * Quick sort tests.
 *
 */
public class QuickSortTest {

  /**
   * Test sort.
   */
  @Test
  public void sort() {
    RandomGenerator generator = RandomGenerator.getDefault();

    int targetListSize = 100;
    int minValue = -100_000;
    int maxValue = 100_001;

    List<Integer> list = new ArrayList<>();
    for (int j = 0; j < targetListSize; j++) {
      list.add(generator.nextInt(minValue, maxValue));
    }

    QuickSort.sort(list);
    for (int j = 1; j < targetListSize; j++) {
      assertTrue(list.get(j - 1).compareTo(list.get(j)) <= 0);
    }
  }

}
