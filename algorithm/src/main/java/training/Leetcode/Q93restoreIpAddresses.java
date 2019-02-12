package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 93. 复原IP地址
 *
 * 题目描述
 * 评论 (30)
 * 官方题解
 * 提交记录
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Q93restoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0 || s.length() > 12 || s.length() < 4) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        split(result, s, "", 0);
        return result;
    }

    public void split(List<String> result, String s, String preString, int num) {
        if (num == 3) {
            if (s.length() > 3 || s.length() <= 0) {
                return;
            }
            if (!s.startsWith("0") || s.equals("0")) {
                int value = toNum(s);
                if (value >=0 && value <= 255) {
                    result.add(s + preString);
                }
            }
        }
        if (num == 0 || num == 1 || num == 2) {
            if (s.length() > (4 - num) * 3) {
                return;
            }
            if (s.length() >= 1) {
                String sub1 = s.substring(s.length() - 1);
                split(result, s.substring(0, s.length() - 1), "." + sub1 + preString, num + 1);

            }
            if (s.length() >= 2) {
                String sub2 = s.substring(s.length() - 2);
                if (!sub2.startsWith("0")) {
                    split(result, s.substring(0, s.length() - 2), "." + sub2 + preString, num + 1);
                }
            }

            if (s.length() > 3) {
                String sub3 = s.substring(s.length() - 3);
                if (!sub3.startsWith("0")) {
                    int sub3Num = toNum(sub3);
                    if (sub3Num <= 255) {
                        split(result, s.substring(0, s.length() - 3), "." + sub3 + preString, num + 1);
                    }
                }
            }
        }
    }

    private int toNum(String s2) {
        return Integer.parseInt(s2);
    }


    public static void main(String[] args) {
        Q93restoreIpAddresses test = new Q93restoreIpAddresses();
//        List<String> result = test.restoreIpAddresses("25525511135");
//        List<String> result = test.restoreIpAddresses("0000");
        List<String> result = test.restoreIpAddresses("010010");
        for (String s: result) {
            System.out.println(s);
        }
    }
}