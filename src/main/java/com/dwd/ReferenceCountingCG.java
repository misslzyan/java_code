package com.dwd;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/8/23 下午6:50
 */
public class ReferenceCountingCG {

    public static void main(String[] args) {
        testGC();
    }
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingCG objA = new ReferenceCountingCG();
        ReferenceCountingCG objB = new ReferenceCountingCG();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}
