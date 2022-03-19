package com.toom.event_system.Common.Utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 生成数学文本算式
     *
     * @param a 数字
     * @param b 数字
     * @return 字符
     */
    public static Map<String, String> mathTextCreator(int a, int b) {
        Random random = new SecureRandom();

        // 生成随机操作，操作范围：[0,4)，分别表示： +  -  *  /
        int op = random.nextInt(4);

        // 定义计算的结果
        Integer result = 0;

        // 定义构建的算式字符串
        StringBuilder resultString = new StringBuilder();

        // 运算符：-
        if (1 == op) {
            if (a >= b) {
                result = a - b;
                resultString.append(a).append("-").append(b).append("=?@").append(result);
            } else {
                result = b - a;
                resultString.append(b).append("-").append(a).append("=?@").append(result);
            }
        }

        // 运算符：*
        else if (2 == op) {
            result = a * b;
            resultString.append(a).append("*").append(b).append("=?@").append(result);
        }

        // 运算符：/
        else if (3 == op) {
            if (a != 0 && b % a == 0) {
                result = b / a;
                resultString.append(b).append("/").append(a).append("=?@").append(result);
            } else if (b != 0 && a % b == 0) {
                result = a / b;
                resultString.append(a).append("/").append(b).append("=?@").append(result);
            } else {
                return mathTextCreator(a, b);
            }
        }

        // 运算符：+
        else {
            result = b + a;
            resultString.append(a).append("+").append(b).append("=?@").append(result);
        }
        Map<String, String> ret = new HashMap<String, String>();
        ret.put("resultCode", result.toString());
        ret.put("resultString", resultString.toString());
        return ret;
    }
}
