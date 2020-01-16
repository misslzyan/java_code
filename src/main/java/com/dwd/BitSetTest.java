package com.dwd;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.BitSet;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/9/10 下午4:04
 */
public class BitSetTest {

    public static void main(String[] args) throws DecoderException {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(1024);
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
        System.out.println(bitSet.get(0));
        byte[] v = bitSet.toByteArray();
        System.out.println(BitSet.valueOf(v).get(1));
        System.out.println(BitSet.valueOf(v).get(2));
        System.out.println(BitSet.valueOf(v).get(0));
        String str = new String(v);
        System.out.println(str);
        System.out.println(str.length());
        byte[] v2 = str.getBytes();
        System.out.println(BitSet.valueOf(v2).get(1));
        System.out.println(BitSet.valueOf(v2).get(2));
        System.out.println(BitSet.valueOf(v2).get(0));
        String s = Hex.encodeHexString(v);
        System.out.println(s);
        System.out.println(s.length());
        byte[] v3 = Hex.decodeHex(s.toCharArray());
        System.out.println(BitSet.valueOf(v3).get(1));
        System.out.println(BitSet.valueOf(v3).get(2));
        System.out.println(BitSet.valueOf(v3).get(0));
    }
}
