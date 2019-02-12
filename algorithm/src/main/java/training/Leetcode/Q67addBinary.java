package training.Leetcode;

/**
 67. 二进制求和




 题目描述
 评论 (102)
 官方题解
 提交记录
 给定两个二进制字符串，返回他们的和（用二进制表示）。

 输入为非空字符串且只包含数字 1 和 0。

 示例 1:

 输入: a = "11", b = "1"
 输出: "100"
 示例 2:

 输入: a = "1010", b = "1011"
 输出: "10101"

 */
public class Q67addBinary {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int plus = 0;
        int len = i > j ? i + 1: j + 1;
        char[] s = new char[len];
        int nowIndex = s.length - 1;
        while (i >=0 && j >= 0) {
            int value = a.charAt(i) - '0' + b.charAt(j) - '0' + plus;
            plus = value / 2;
            s[nowIndex] = (char) ('0' + (value % 2));
            i--;
            j--;
            nowIndex--;
        }
        while (i >= 0) {
            int value = a.charAt(i) - '0' + plus;
            plus = value / 2;
            s[nowIndex] = (char) ('0' + (value % 2));
            nowIndex--;
            i--;
        }
        while (j >= 0) {
            int value = b.charAt(j) - '0' + plus;
            plus = value / 2;
            s[nowIndex] = (char) ('0' + (value % 2));
            nowIndex--;
            j--;
        }
        String result = new String(s);
        if (plus == 1) {
            return "1" + result;
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        Q67addBinary test = new Q67addBinary();
        System.out.println(test.addBinary("1010", "1011"));
    }
}
