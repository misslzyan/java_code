package com.dwd;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/8/26 下午5:51
 */
public class TestTime {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(System.nanoTime() / 1000000);
        start = System.nanoTime()/1000000;
        Thread.sleep(1000);
        System.out.println(System.nanoTime()/1000000-start);
    }
}
