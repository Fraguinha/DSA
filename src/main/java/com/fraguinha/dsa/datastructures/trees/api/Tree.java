package com.fraguinha.dsa.datastructures.trees.api;

import java.util.Optional;

/**
 * Tree Interface.
 *
 */
public interface Tree<E extends Comparable<E>> {

  /**
   * Inserts an element into the tree.
   *
   * @param elem the element to insert.
   */
  public void insert(E elem);

  /**
   * Removes an element from the tree.
   *
   * @param elem the element to delete.
   */
  public void delete(E elem);

  /**
   * Finds an element in the tree.
   *
   * @param elem the element to search.
   */
  public Optional<E> find(E elem);

}
