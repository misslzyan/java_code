package com.dwd.leetcode;

/**
 *  hash表 + 双向链表
 */
public class LRUCache_146 {






  class Node {
    public int key, val;
    public Node prev, next;
    public Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }

  class DoubleLinkedList{
    public Node head, tail;
    public int size;

    public DoubleLinkedList() {
      head = new Node(0, 0);
      tail = new Node(0, 0);
      this.size = 0;
    }
  }
}
