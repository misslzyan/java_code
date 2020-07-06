package com.dwd.leetcode;

import java.util.concurrent.SynchronousQueue;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.dwd.leetcode.PrintinOrder_1114.loop;

public class PrintFooBarAlternately_1115 {

  public static void main(String[] args) {
    Foo f = new Foo(10);
    Thread t1 = new Thread(() -> loop(() -> f.foo()));
    Thread t2 = new Thread(() -> loop(() -> f.bar()));
    t1.start();
    t2.start();
  }

  static class Foo {

    private int n;

    private final SynchronousQueue<String> queue = new SynchronousQueue();

    public Foo(int n) {
      this.n = n;
    }

    public void loop(Supplier fun) {
      IntStream.range(1, 10).forEach(i -> fun.get());
    }

    public int foo() {
      System.out.println("foo");
      try {
        queue.put("foo");
        queue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 0;
    }

    public int bar() {
      try {
        queue.take();
        System.out.println("bar");
        queue.put("bar");
      } catch (Exception e) {
        e.printStackTrace();

      }
      return 0;
    }
  }
}
