package com.dwd.leetcode;

class MinStack {

  public static void main(String[] args) {
    MinStack s = new MinStack();
    s.push(-2);
    s.push(-3);
    System.out.println(s.getMin());
  }

  class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }


  Node head;

  /** initialize your data structure here. */
  public MinStack() {

  }

  public void push(int x) {
    Node n = new Node(x);
    if (head == null) head = n;
    else {
      n.next = head;
      head = n;
    }
  }

  public void pop() {
    if (head == null) return;
    Node n = head;
    head = head.next;
  }

  public int top() {
    if (head == null) return -1;
    return head.val;
  }

  public int getMin() {
    if (head==null) return -1;
    int minVal = head.val;
    for(Node tmp = head; tmp != null; tmp = tmp.next){
      if (minVal > tmp.val) {
        minVal = tmp.val;
      }
    }
    return minVal;
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
