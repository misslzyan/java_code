package com.dwd.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;


public  class ZeroEvenOdd_1116 {

  private int n;

  private Semaphore zero = new Semaphore(1);

  private Semaphore odd = new Semaphore(0);

  private Semaphore even = new Semaphore(0);

  public ZeroEvenOdd_1116(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1 ; i <= n ; i++) {
      zero.acquire();
      printNumber.accept(0);
      if ( (i & 1) == 0) {
        even.release();
      }else{
        odd.release();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for (int i = 2 ; i <= n ; i=i+2) {
      even.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1 ; i <= n ; i=i+2) {
      odd.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }


  public static void main(String[] args) {
    ZeroEvenOdd_1116 zeroEvenOdd = new ZeroEvenOdd_1116(5);
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          zeroEvenOdd.zero(x -> System.out.print(x));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          zeroEvenOdd.even(x -> System.out.print(x));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          zeroEvenOdd.odd(x -> System.out.print(x));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }


}

