package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *119. 杨辉三角 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (70)
 * 官方题解
 * 提交记录
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class Q119yanghui2 {



    public List<Integer> getRow(int rowIndex) {
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        if (rowIndex < 0) {
            return Collections.emptyList();
        }
        if (rowIndex == 0) {
            result1.add(1);
            return result1;
        }
        if (rowIndex == 1) {
            result1.add(1);
            result1.add(1);
            return result1;
        }

        int n = rowIndex;
        result1.add(1);
        result1.add(n);
        int[] num_n = c_n(n);
        for (int i = 2; i <= n / 2; i++) {
            int[] num_m = c_n(i);
            int[] num_n_m = c_n(n - i);
            int num = c_n_m(num_n, num_m, num_n_m);
            result1.add(num);
            if (!(i == n / 2 && n % 2 == 0)) {
                result2.add(num);
            }
        }
        for (int i = result2.size() - 1; i >=0; i--) {
            result1.add(result2.get(i));
        }
        if (n > 2) {
            result1.add(n);
        }
        result1.add(1);
        return result1;
    }

    public int[] c_n(int n) {
        int[] nums = new int[34];
        if (n == 2) {
            nums[2] = 1;
            return nums;
        }
        if (n == 3) {
            nums[3] = 1;
            nums[2] = 1;
            return nums;
        }
        for (int k = 2; k <=n; k++) {
            int nowValue = k;
            for (int i = 2 ;i <= nowValue && nowValue != 1; i++) {
                while (nowValue > 1) {
                    if (nowValue % i == 0) {
                        nowValue = nowValue / i;
                        nums[i] ++;
                    } else {
                        break;
                    }
                }
            }
        }
        return nums;
    }

    public int c_n_m(int[] numsN, int[] numsM, int[] numsN_M) {
        int[] newNums = new int[numsN.length];
        for (int i = 2; i < numsN.length; i++) {
            newNums[i] = numsN[i] - numsM[i] - numsN_M[i];
        }
        int result = 1;
        for (int i = 2; i < newNums.length; i++) {
            for (int j = 0; j < newNums[i];j++) {
                result *= i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q119yanghui2 test = new Q119yanghui2();
        System.out.println(test.getRow(5));
    }
}