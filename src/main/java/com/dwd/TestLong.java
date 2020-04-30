//package com.dwd;
//
//import java.util.Random;
//
///**
//	* 描述：
//	*
//	* @author duanweidong
//	* @version 1.0
//	* @date 2020/1/13 下午5:06
//	*/
//public class TestLong {
//		private long a = 0L;
//
//		private static ThreadLocal<Long> tll = new ThreadLocal<>();
//
//		private static Random r = new Random();
//
//		public void add() {
//				a++;
//		}
//
//		public long get() {
//				return a;
//		}
//
//		public static void main(String[] args) {
//				TestLong tl = new TestLong();
//				Thread t1 = new Thread(new Runnable() {
//						@Override
//						public void run() {
//								while (true) {
//										tl.add();
//								}
//						}
//				});
//				Thread t2 = newThread(tl);
//				Thread t3 = newThread(tl);
//				Thread t4 = newThread(tl);
//				Thread t5 = newThread(tl);
//				Thread t6 = newThread(tl);
//				Thread t7 = newThread(tl);
//				Thread t8 = newThread(tl);
//				t1.start();
//				t2.start();
//				t3.start();
//				t4.start();
//				t5.start();
//				t6.start();
//				t7.start();
//				t8.start();
//
//
//		}
//
//		public static Thread newThread(TestLong tl) {
//				return new Thread(() -> {
//						while (true) {
//								long tmp = tl.get();
//								if (tmp <= 0) {
//										System.out.println(tmp);
//										System.exit(-1);
//								}
//								Long prev = tll.get();
//								if (prev != null) {
//										if (prev > tmp) {
//												System.out.println(prev);
//												System.out.println(tmp);
//												System.exit(-1);
//										} else {
//												tll.set(tmp);
//										}
//								} else {
//										tll.set(tmp);
//								}
//						}
//				});
//		}
//}
