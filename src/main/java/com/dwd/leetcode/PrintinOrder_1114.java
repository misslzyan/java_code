package com.dwd.leetcode;

import java.util.function.Supplier;

public class PrintinOrder_1114 {

  public static void main(String[] args) {
    Foo f = new Foo();
    f.setCnt(1);
    Thread t1 = new Thread(() -> loop(() -> f.first()));
    Thread t2 = new Thread(() -> loop(() -> f.second()));
    Thread t3 = new Thread(() -> loop(() -> f.third()));
    t1.start();
    t2.start();
    t3.start();
  }

  public static void loop(Supplier<Integer> fun) {
    for (int i = 0; i < 10; i++) {
      fun.get();
    }
  }



  static class Foo {

    private int cnt = 1;

    public void setCnt(int cnt) {
      this.cnt = cnt;
    }

    public Foo() {

    }

    public synchronized int first(){
      while (cnt != 1) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("first");
      cnt++;
      notifyAll();
      return 0;
    }

    public synchronized int second() {
      while (cnt != 2) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("second");
      cnt++;
      notifyAll();
      return 0;
    }

    public synchronized int third(){
      while (cnt != 3) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("third.");
      cnt = 1;
      notifyAll();
      return 0;
    }
  }
}
