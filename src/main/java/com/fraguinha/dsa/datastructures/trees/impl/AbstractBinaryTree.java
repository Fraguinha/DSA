package com.fraguinha.dsa.datastructures.trees.impl;

import java.util.Optional;

import com.fraguinha.dsa.datastructures.trees.api.Tree;

/**
 * Abstract Binary Tree.
 *
 */
public abstract class AbstractBinaryTree<E extends Comparable<E>> implements Tree<E> {

  /**
   * Tree Node class.
   *
   */
  protected class Node {
    protected E value;

    protected Optional<Node> left;

    protected Optional<Node> right;

    Node(final E elem) {
      this.value = elem;
      this.left = Optional.empty();
      this.right = Optional.empty();
    }
  }

  protected Optional<Node> root = Optional.empty();

  @Override
  public void insert(final E elem) {
    this.root = insert(this.root, Optional.of(new Node(elem)));
  }

  @Override
  public void delete(final E elem) {
    this.root = delete(this.root, Optional.of(new Node(elem)));
  }

  @Override
  public Optional<E> find(final E elem) {
    return find(this.root, Optional.of(new Node(elem)))
        .map(node -> node.value);
  }

  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();

    if (this.root.isPresent()) {
      stringBuilder.append(this.root.get().value).append("\n");
      buildTreeString(this.root.get().right, "", false, stringBuilder);
      buildTreeString(this.root.get().left, "", true, stringBuilder);
    }

    return stringBuilder.toString();
  }

  /**
   * Insert element into node.
   *
   * @param node the node to insert into.
   * @param elem the element to be inserted.
   * @return the updated node.
   * @throws IllegalStateException
   */
  protected Optional<Node> insert(final Optional<Node> node, final Optional<Node> elem) {
    if (node.isEmpty()) {
      return elem;
    }

    final Integer difference = Integer.valueOf(elem.get().value.compareTo(node.get().value));

    final Optional<Node> updatedNode = switch (difference) {
      case final Integer i when i < 0 -> {
        node.get().left = insert(node.get().left, elem);
        yield node;
      }
      case final Integer i when i > 0 -> {
        node.get().right = insert(node.get().right, elem);
        yield node;
      }
      case final Integer i when i == 0 -> node;
      default ->
        throw new IllegalStateException("Switch cases are exhaustive");
    };

    return updatedNode;
  }

  /**
   * Delete element from node.
   *
   * @param node the node to delete from.
   * @param elem the element to be deleted.
   * @return the updated node.
   * @throws IllegalStateException
   */
  protected Optional<Node> delete(final Optional<Node> node, final Optional<Node> elem) {
    if (node.isEmpty()) {
      return node;
    }

    final Integer difference = Integer.valueOf(elem.get().value.compareTo(node.get().value));

    final Optional<Node> updatedNode = switch (difference) {
      case final Integer i when i < 0 -> {
        node.get().left = delete(node.get().left, elem);
        yield node;
      }
      case final Integer i when i > 0 -> {
        node.get().right = delete(node.get().right, elem);
        yield node;
      }
      case final Integer i when i == 0 -> {
        final Optional<Node> maxOfLeftSubtree = findMax(node.get().left);

        if (maxOfLeftSubtree.isPresent()) {
          final Optional<Node> parentOfMax = findParent(node, maxOfLeftSubtree);

          if (parentOfMax.get().left == maxOfLeftSubtree) {
            parentOfMax.get().left = maxOfLeftSubtree.get().left;
          } else {
            parentOfMax.get().right = maxOfLeftSubtree.get().left;
          }

          maxOfLeftSubtree.get().left = node.get().left;
          maxOfLeftSubtree.get().right = node.get().right;
        }

        yield maxOfLeftSubtree;
      }
      default -> throw new IllegalStateException("Switch cases are exhaustive");
    };

    return updatedNode;
  }

  /**
   * Find element in node.
   *
   * @param node the node to find from.
   * @param elem the element to be found.
   * @return the optional element.
   * @throws IllegalStateException
   */
  protected Optional<Node> find(final Optional<Node> node, final Optional<Node> elem) {
    if (node.isEmpty()) {
      return node;
    }

    final Integer difference = Integer.valueOf(elem.get().value.compareTo(node.get().value));

    final Optional<Node> result = switch (difference) {
      case final Integer i when i < 0 -> find(node.get().left, elem);
      case final Integer i when i > 0 -> find(node.get().right, elem);
      case final Integer i when i == 0 -> node;
      default -> throw new IllegalStateException("Switch cases are exhaustive");
    };

    return result;
  }

  /**
   * Find parent of node.
   *
   * @param node the node to find the parent of.
   * @return the parent node.
   */
  protected Optional<Node> findParent(final Optional<Node> node, final Optional<Node> child) {
    if (node.isEmpty()) {
      return node;
    }

    final Integer difference = Integer.valueOf(child.get().value.compareTo(node.get().value));

    final Optional<Node> result = switch (difference) {
      case final Integer i when i < 0 -> {
        if (node.get().left.equals(child)) {
          yield node;
        } else {
          yield findParent(node.get().left, child);
        }
      }
      case final Integer i when i > 0 -> {
        if (node.get().right.equals(child)) {
          yield node;
        } else {
          yield findParent(node.get().right, child);
        }
      }
      case final Integer i when i == 0 -> Optional.empty();
      default -> throw new IllegalStateException("Switch cases are exhaustive");
    };

    return result;
  }

  /**
   * Find minimum element in node.
   *
   * @param node the node to find the minimum element in.
   * @return the minimum element.
   */
  protected Optional<Node> findMin(final Optional<Node> node) {
    if (node.isEmpty()) {
      return node;
    }

    if (node.get().left.isPresent()) {
      return findMin(node.get().left);
    }

    return node;
  }

  /**
   * Find maximum element in node.
   *
   * @param node the node to find the maximum element in.
   * @return the maximum element.
   */
  protected Optional<Node> findMax(final Optional<Node> node) {
    if (node.isEmpty()) {
      return node;
    }

    if (node.get().right.isPresent()) {
      return findMax(node.get().right);
    }

    return node;
  }

  /**
   * Height of node.
   *
   * @param node the node to calculate height.
   * @return the node height.
   */
  protected int height(final Optional<Node> node) {
    if (node.isEmpty()) {
      return 0;
    }

    final int leftHeight = height(node.get().left);
    final int rightHeight = height(node.get().left);

    if (leftHeight >= rightHeight) {
      return 1 + leftHeight;
    } else {
      return 1 + rightHeight;
    }
  }

  /**
   * Rotate the node left.
   *
   * @param node the node to be rotated left.
   * @return the updated node.
   */
  protected Optional<Node> rotateLeft(Optional<Node> node) {
    if (node.isEmpty()) {
      return node;
    }

    if (node.get().right.isEmpty()) {
      return node;
    }

    final Optional<Node> a = node;
    final Optional<Node> b = node.get().right;

    node = b;
    a.get().right = b.get().left;
    b.get().left = a;

    return node;
  }

  /**
   * Rotate the node right.
   *
   * @param node the node to be rotated right.
   * @return the updated node.
   */
  protected Optional<Node> rotateRight(Optional<Node> node) {
    if (node.isEmpty()) {
      return node;
    }

    if (node.get().left.isEmpty()) {
      return node;
    }

    final Optional<Node> a = node;
    final Optional<Node> b = node.get().left;

    node = b;
    a.get().left = b.get().right;
    b.get().right = a;

    return node;
  }

  /**
   * Build a string representation of the tree.
   *
   * @param node          the node from which to build the string representation.
   * @param prefix        the characters to the left.
   * @param isTail        whether the node is a tail node.
   * @param stringBuilder the string builder.
   */
  private void buildTreeString(final Optional<Node> node, final String prefix, final boolean isTail,
      final StringBuilder stringBuilder) {
    if (node.isPresent()) {
      stringBuilder.append(prefix);
      stringBuilder.append(isTail ? "└── " : "├── ");
      stringBuilder.append(node.get().value);
      stringBuilder.append("\n");

      final String newPrefix = prefix + (isTail ? "    " : "│   ");

      if (node.get().right.isPresent() && node.get().left.isPresent()) {
        buildTreeString(node.get().right, newPrefix, false, stringBuilder);
        buildTreeString(node.get().left, newPrefix, true, stringBuilder);
      } else if (node.get().right.isPresent()) {
        buildTreeString(node.get().right, newPrefix, true, stringBuilder);
      } else if (node.get().left.isPresent()) {
        buildTreeString(node.get().left, newPrefix, true, stringBuilder);
      }
    }
  }
}
