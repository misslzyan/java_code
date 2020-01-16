package com.dwd;

import java.util.*;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/10/12 下午5:20
 */
public class StrToInt {
//
//    private static final char EOF = '\0';
//
//    private String expr;
//    private List<Integer> res;
//    private int i;
//    private char ch;
//
//    public StrToInt(String expr) {
//        this.expr = expr + EOF;
//        this.i = 0;
//    }
//
//    public Set<Integer> res() {
//        if( res == null) {
//            synchronized (this) {
//                if(res == null) {
//                    analyze();
//                }
//            }
//        }
//        return new TreeSet<>(res);
//    }
//
//    private void analyze() {
//        res = new ArrayList<>();
//        for(;;) {
//            getch();
//            if(ch >= '0' && ch <= '9') {
//                res.add(Integer.valueOf(getInt()));
//                continue;
//            }
//            if(ch == '-') {
//                getch();
//                int prev = res.get(res.size()-1);
//                int next = getInt();
//                if(prev > next) {
//                    throw new RuntimeException("The first number: " + prev + " is greater than the second : " + next + ".");
//                }
//                while(prev < next) {
//                    res.add(Integer.valueOf(++prev));
//                }
//                continue;
//            }
//            if(ch == ',') continue;
//            if(ch == EOF) break;
//            throw new RuntimeException("Unknown Character: " + ch);
//        }
//    }
//
//    void getch() {
//        ch = expr.charAt(i++);
//    }
//
//    void ungetch() {
//        ch = expr.charAt(--i);
//    }
//
//    int getInt() {
//        int sum = 0;
//        if(ch < '0' || ch > '9') {
//            throw new RuntimeException("The Character in index: "+(i-1)+" must be an int . Actually is : "+ch);
//        }
//        while(ch >= '0' && ch <= '9') {
//            sum = sum * 10 + ch - '0';
//            getch();
//        }
//        ungetch();
//        return sum;
//    }
//
//
//    static String getMethodName1() {
//        final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//        for(StackTraceElement element : stack) {
//            System.out.println(element.getMethodName());
//        }
//        String methodName = stack[3].getMethodName();
//        return methodName;
//    }
//
//    public static void main(String[] args) {
//
//r();
//
//
//
////        String str = "0-0,,,3-4,,1-2,1,2,,,";
////        StrToInt s = new StrToInt(str);
////        Set<Integer> res = s.res();
////        System.out.println(res);
////        System.out.println(r());
//    }
//
//    public static String r() {
//        System.out.println(getMethodName1());
//        return "\t[-add <source> <nameservice1, nameservice2, ...> <destination> "
//                + "[-readonly] [-order HASH|LOCAL|RANDOM|HASH_ALL] [-bucket <nameservice1:bucketNum1;nameservice2:bucketNum2>]"
//                + " -owner <owner> -group <group> -mode <mode>]";
//    }

		public static void main(String[] args) {
				System.out.println((2 << 14 - 1) + 1);
		}
}
