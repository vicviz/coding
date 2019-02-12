package training.Leetcode;

/**
 66. 加一




 题目描述
 评论 (191)
 官方题解
 提交记录
 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

 你可以假设除了整数 0 之外，这个整数不会以零开头。

 示例 1:

 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。
 示例 2:

 输入: [4,3,2,1]
 输出: [4,3,2,2]
 解释: 输入数组表示数字 4321。

 */
public class Q66plusOne {
    public int[] plusOne(int[] digits) {
        int plus = 0;

        for (int i = digits.length - 1 ; i >= 0; i--) {
            if (i == digits.length - 1) {
                digits[i] = digits[i] + 1 + plus;
            } else {
                digits[i] = digits[i] + plus;
            }
            if (digits[i] == 10) {
                plus = 1;
                digits[i] %= 10;
            } else {
                plus = 0;
            }
        }
        if (plus == 1) {
            int[] newInt = new int[digits.length + 1];
            newInt[0] = 1;
            for (int i = 0 ; i < digits.length; i++) {
                newInt[i + 1] = digits[i];
            }
            return newInt;
        } else {
            return digits;
        }
    }

    public static void main(String[] args) {
        Q66plusOne test = new Q66plusOne();
        int[] digits = new int[]{0};
        int[] result = test.plusOne(digits);
        for (int i = 0 ; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }
}
