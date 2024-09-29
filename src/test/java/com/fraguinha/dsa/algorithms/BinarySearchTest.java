package com.fraguinha.dsa.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.Test;

/**
 * Binary search tests.
 *
 */
public class BinarySearchTest {

  /**
   * Test search.
   */
  @Test
  public void search() {
    RandomGenerator generator = RandomGenerator.getDefault();

    int targetSetSize = 1000;
    int minValue = -100_000;
    int maxValue = 100_001;

    Set<Integer> set = new HashSet<>();
    while (set.size() < targetSetSize) {
      set.add(generator.nextInt(minValue, maxValue));
    }

    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);

    for (int i = minValue; i < maxValue; i++) {
      Optional<Integer> result = BinarySearch.search(list, i);

      if (result.isPresent()) {
        assertEquals(Collections.binarySearch(list, i), result.get());
      } else {
        assertTrue(Collections.binarySearch(list, i) < 0);
      }
    }
  }

}
