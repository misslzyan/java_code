package com.dwd;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/8/26 下午4:43
 */
public class Test {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println(generateMashupKey(map));
    }

    protected static String generateMashupKey(final Map<String, String> keys) {
        StringBuilder builder = new StringBuilder();
        for (Object value : keys.values()) {
            if (builder.length() > 0) {
                builder.append("-");
            }
            builder.append(value);
        }
        return builder.toString();
    }
}
