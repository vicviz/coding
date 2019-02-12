package training.Leetcode;

import java.util.*;

/**
 * 91. 解码方法
 *
 *
 *
 *
 * 题目描述
 * 评论 (33)
 * 官方题解
 * 提交记录
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Q91numDecodings {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.equals("0")) {
            return 0;
        }
        Map<String, Integer> record = new HashMap<>();
        return myNumDecoding(s, record);
    }

    public int myNumDecoding(String s, Map<String, Integer> record) {
        if (record.containsKey(s)) {
            return record.get(s);
        }
        if (s.length() == 0) {
            return 1;
        }
        if (s.length() == 1) {
            char c = s.charAt(0);
            if (c <= '9' && c >= '1') {
                return 1;
            } else {
                return 0;
            }
        }
        String sub2 = s.substring(s.length() - 2);
        String sub1 = sub2.substring(1);
        int num2, num1;
        int sub2Num = toNum(sub2);
        if (sub2.startsWith("0")) {
            num2 = 0;
        } else {
            if (sub2Num <= 26 && sub2Num >=1) {
                num2 = myNumDecoding(s.substring(0, s.length() - 2), record);
            } else {
                num2 = 0;
            }
        }
        int subNum1 = sub1.charAt(0) - '0';
        if (subNum1 <= 9 && subNum1 >=1) {
            num1 = myNumDecoding(s.substring(0, s.length() - 1), record);
        } else {
            num1 = 0;
        }

        int num = num1 + num2;
        record.put(s, num);
        return num;
    }


    private int toNum(String s2) {
        int num = (s2.charAt(0) - '0') * 10 + (s2.charAt(1) - '0');
        return num;
    }

    public static void main(String[] args) {
        Q91numDecodings test = new Q91numDecodings();
        System.out.println(test.numDecodings("2206"));
        System.out.println(test.numDecodings("01"));
    }
}