package com.dwd.leetcode;

public class Solution_23 {

  public static void main(String[] args) {
    Solution_23 s = new Solution_23();
    ListNode[] lists = new ListNode[3];
    lists[0] = s.createNode(new int[] {1 ,4 ,5});
    lists[1] = s.createNode(new int[] {1, 3 ,4});
    lists[2] = s.createNode(new int[] {2,6});
    ListNode  res  = s.mergeKLists(lists);
    s.print(res);
  }
  public void print(ListNode res) {
    while (res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }
  public  ListNode createNode(int[] array) {
    ListNode head = new ListNode(array[0]);
    ListNode res = head;
    for (int i = 1;i<array.length;i++){
      head.next = new ListNode(array[i]);
      head = head.next;
    }
    return res;
  }

  /**
   * Definition for singly-linked list.*/
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
      if(lists == null) return null;
      ListNode head = null, res = null;
      // ListNode res;
      ListNode[] headNode = new ListNode[lists.length];
      headNode = lists;
      while (hashNext(headNode)) {
        ListNode least = getLeast(headNode);
        least.next = null;
        if (head == null) {
          head = least;
          res = head;
        }else{
          head.next = least;
          head = head.next;
          head.next=null;
        }
      }
      return res;
    }

    public boolean hashNext(ListNode[] headNode) {
      for(int i = 0;i < headNode.length; i++) {
        if(headNode[i]!=null) return true;
      }
      return false;
    }

    public ListNode getLeast(ListNode[] headNode) {
      int min = Integer.MAX_VALUE;
      for (int i = 0;i<headNode.length;i++){
        if(headNode[i] != null && headNode[i].val <= min){ min = headNode[i].val;}
      }
      for (int i = 0;i<headNode.length;i++){
        if(headNode[i]!=null && headNode[i].val == min){
          ListNode res = headNode[i];
          headNode[i] = headNode[i].next;
          return res;
        }
      }
      return null;
    }
}
