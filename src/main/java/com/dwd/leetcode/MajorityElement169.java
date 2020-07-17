package com.dwd.leetcode;

import java.util.HashMap;
import java.util.stream.IntStream;

public class MajorityElement169 {

  public static void main(String[] args) {
    int[] array = {3,3,2,2,1,1,3,1,2,2,2,2,2};
    System.out.println(Solution1.majorityElement(array));
    System.out.println(Solution2.majorityElement(array));
  }



  /**
   * 摩尔投票算法
   */
  static class Solution1 {

    public static int majorityElement(int[] array) {
      int count = 0;
      int max = 0;
      for (int i = 0; i < array.length; i++) {
        if (array[max] == array[i]) {
          count++;
        } else {
          count--;
        }
        if (count == 0) {
          max = i + 1;
        }
        if (count > array.length/2) {
          return array[max];
        }
      }
      return array[max];
    }
  }

  /**
   * 利用hash
   */
  static class Solution2 {

    public static int majorityElement(int[] array) {
      HashMap<Integer, Integer> count = new HashMap<>();
      Integer a = 0;
      int cnt = 0;
      for (int i = 0; i < array.length; i++) {
        count.put(array[i], (cnt = (a = count.get(array[i])) == null ? 1 : a + 1));
        if (cnt  > array.length / 2) {
          return array[i];
        }
      }
      throw new RuntimeException("not major.");
    }
  }
}
