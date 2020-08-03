package com.dwd.leetcode;

public class AddBinary_67 {

  public static void main(String args[]) {
    String a = "01";
    String b = "10";
    System.out.println(addBinary(a, b));
  }


  public static String addBinary(String a, String b) {
     int c = 0, i = a.length() - 1, j = b.length() - 1;
     StringBuilder res = new StringBuilder();
     while (c > 0 || i >= 0 || j >= 0) {
        int valA = i >= 0? a.charAt(i--) - '0':0;
        int valB = j >= 0? b.charAt(j--) - '0':0;
        int sum = c + valA + valB;
        c = sum >> 1;
        int r = sum & 1;
        res.append(r);
     }
     return res.reverse().toString();
  }
}
