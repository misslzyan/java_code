package com.dwd.leetcode;


import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_336 {


  /** 暴力解法，时间不过关 */
  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> lists = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      for (int j = 0; j < words.length; j++) {
        if (i == j) continue;
        String wordRight = words[j];
        if (isPalindrome(word, wordRight)) {
          List<Integer> arrayList = new ArrayList<>();
          arrayList.add(i);
          arrayList.add(j);
          lists.add(arrayList);
        }
      }

    }
    return lists;
  }

  public boolean isPalindrome(String a, String b) {
    String c = a + b;
    int i = 0;
    int j = c.length() - 1;
    for (; i < j;i++,j--){
      if (c.charAt(i) != c.charAt(j)){
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Solution_336 m = new Solution_336();
    Solution_336_1 s = m.new Solution_336_1();
    String[] words = new String[]{"a", ""};
    List<List<Integer>> lists = s.palindromePairs(words);
    lists.forEach(i -> System.out.println(i));
  }

  class Solution_336_1 {
    class Node {
      int[] ch = new int[26];
      int flag; // 表示该词的序号
      public Node() {
        this.flag = -1;
      }
    }
    private List<Node> tree = new ArrayList<>();
    public List<List<Integer>> palindromePairs(String[] words) {
      Set<List<Integer>> lists = new HashSet<>();
      tree.add(new Node());
      int len = words.length;
      //初始化 字典树
      for (int i = 0; i < len; i++) {
        insert(words[i], i);
      }

      for (int i = 0; i < len; i++) {
        int m = words[i].length();
        for (int j = 0; j <= m; j++){
          int f = -1;
          if (isPalindrome(words[i], 0, j  ) && (f = search(words[i], j, m )) != -1){
            if (f != i) {
              lists.add(Arrays.asList(f, i));
            }
          }
          if ((isPalindrome(words[i], j , m )) && ((f = search(words[i], 0, j  ))!= -1)) {
            if (f != i) {
              lists.add(Arrays.asList(i, f));
            }
          }
        }
      }
      return new ArrayList<>(lists);

    }

    public int search(String s, int from , int to) {
      int i = 0, c = 0;
      for (; to > from; to--) {
        c = s.charAt(to-1) - 'a';
        if (tree.get(i).ch[c] == 0) {
          return -1;
        }
        i = tree.get(i).ch[c];
      }
      return tree.get(i).flag;
    }

    public boolean isPalindrome(String s, int left, int right) {
      while (left < right) {
        if (s.charAt(left) != s.charAt(right - 1)) {
          return false;
        }
        left++;
        right--;
      }
      return true;
    }

    public void insert(String s, int c) {
      int len = s.length();
      int add = 0;
      for (int i = 0; i < len; i++) {
        int m = s.charAt(i) - 'a';
        if (tree.get(add).ch[m] == 0) {
          tree.add(new Node());
          tree.get(add).ch[m] = tree.size() - 1;
        }
        add = tree.get(add).ch[m];
      }
      tree.get(add).flag = c; // 标识当前字符串的下标。
    }

  }
}
