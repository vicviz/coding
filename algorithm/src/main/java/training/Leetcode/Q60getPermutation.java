package training.Leetcode;

/**
 *  第k个排列
 *
 * 题目描述
 * 评论 (45)
 * 官方题解
 * 提交记录
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Q60getPermutation {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n + 1];
        int allNum = 1;

        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            allNum *= i;
            nums[i] = allNum;
        }
        StringBuilder sb = new StringBuilder();
        findPermutation(n, k - 1, nums, sb);
        return sb.toString();
    }


    private void findPermutation(int n, int k, int[] nums, StringBuilder sb) {
        if (n == 0) {
            return;
        }
        String alreadyNums = sb.toString();
        int[] tenNum = new int[10];
        for (int i = 0 ; i < alreadyNums.length(); i++) {
            tenNum[alreadyNums.charAt(i) - '0'] = 1;
        }
        StringBuilder remainNumSB = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            if (tenNum[i] == 0) {
                remainNumSB.append(i);
            }
        }
        int group = k / nums[n - 1];
        char c = remainNumSB.charAt(group);
        int remain = k % nums[n -1];
        sb.append(c);
        findPermutation(n - 1, remain, nums, sb);
    }

    public static void main(String[] args) {
        Q60getPermutation test = new Q60getPermutation();
        System.out.println(test.getPermutation(1, 1));
        System.out.println(test.getPermutation(2, 1));
        System.out.println(test.getPermutation(2, 2));
        System.out.println(test.getPermutation(3, 1));
        System.out.println(test.getPermutation(3, 3));
        System.out.println(test.getPermutation(4, 9));
    }
}
