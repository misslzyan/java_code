package com.dwd.leetcode;

import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.List;

/**
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateBracket_22 {

  public static void main(String[] args) {
    String s = new String("s");
    cat(s);
    System.out.println(s);
    List<String> strings = generateParenthesis(3);
    strings.forEach(ss -> System.out.println(ss));
    System.out.println("=====");
    strings = generateParenthesis2(3);
    strings.forEach(ss -> System.out.println(ss));
  }

  public static void cat(String s) {
    s = s + "2";
  }

  public static void generate(List<String> resList,String res, int open, int close, int max) {
    // reach to the max length, collect and return.
    if (res.length() == max * 2) {
      resList.add(res);
      return;
    }
    // can add open
    if (open < max) {
      res = res + "(";
      generate(resList, res, open + 1, close, max);
      res = res.substring(0, res.length() - 1);
    }

    // can add close
    if (close < open) {
      res = res + ")";
      generate(resList, res, open, close + 1, max);
      res = res.substring(0, res.length() - 1);
    }
  }

  public static List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    generate(res, "", 0, 0, n);
    return res;
  }

  /**
   * solution 2
   * (a)b
   */
  public static List<String> generateParenthesis2(int n) {
    return generate2(n);
  }

  private static List[] cache = new ArrayList[100];

  public static List<String> generate2(int n) {
    if (cache[n] != null) {
      return cache[n];
    }
    List<String> ans = new ArrayList<>();
    if (n == 0) {
      ans.add("");
    } else {
      for (int i = 0; i < n; i++){
        for(String a : generate2(i)) {
          for(String b : generate2(n - i - 1)){
            ans.add("(" + a + ")" + b);
          }
        }
      }
    }

    cache[n] = ans;
    return ans;
  }
}
