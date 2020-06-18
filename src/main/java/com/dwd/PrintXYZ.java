package com.dwd;

public class PrintXYZ {

  private static final Object waitx = new Object();
  private static final Object waity = new Object();
  private static final Object waitz = new Object();

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(i + "x");
        synchronized (waitx) {
          waitx.notify();
        }
        synchronized (waitz) {
          try {
            waitz.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        synchronized (waitx) {
          try {
            waitx.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("y");
        synchronized (waity) {
          waity.notify();
        }

      }
    });
    Thread t3 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        synchronized (waity) {
          try {
            waity.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("z");
        synchronized (waitz) {
          waitz.notify();
        }

      }
    });
    t1.start();
    t2.start();
    t3.start();
  }
}
