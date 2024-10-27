package com.fraguinha.dsa.datastructures.trees.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fraguinha.dsa.datastructures.trees.api.Tree;

/**
 * Binary tree tests.
 *
 */
public class BinaryTreeTest {

  /**
   * Test binary tree.
   */
  @Test
  public void binaryTree() {
    Tree<Integer> tree = new BinaryTree<>();

    System.out.println("Insert 4, 2, 1, 3, 6, 7, 5");
    tree.insert(Integer.valueOf(4));
    tree.insert(Integer.valueOf(2));
    tree.insert(Integer.valueOf(1));
    tree.insert(Integer.valueOf(3));
    tree.insert(Integer.valueOf(6));
    tree.insert(Integer.valueOf(7));
    tree.insert(Integer.valueOf(5));
    System.out.println(tree);
    assertEquals("4\n├── 6\n│   ├── 7\n│   └── 5\n└── 2\n    ├── 3\n    └── 1\n", tree.toString());

    System.out.println("Delete 4");
    tree.delete(Integer.valueOf(4));
    System.out.println(tree);
    assertEquals("3\n├── 6\n│   ├── 7\n│   └── 5\n└── 2\n    └── 1\n", tree.toString());

    System.out.println("Insert 4");
    tree.insert(Integer.valueOf(4));
    System.out.println(tree);
    assertEquals("3\n├── 6\n│   ├── 7\n│   └── 5\n│       └── 4\n└── 2\n    └── 1\n", tree.toString());

    System.out.println("Delete 6");
    tree.delete(Integer.valueOf(6));
    System.out.println(tree);
    assertEquals("3\n├── 5\n│   ├── 7\n│   └── 4\n└── 2\n    └── 1\n", tree.toString());
  }

}
