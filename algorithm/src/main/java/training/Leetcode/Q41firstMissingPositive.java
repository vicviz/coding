package training.Leetcode;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间
 */
public class Q41firstMissingPositive {
    /**
     * 使用了o(n)的空间，这个取决于数组长度有多大
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        byte[] bites = new byte[length + 1];
        for (int num: nums) {
            if (num < 1 ||num > length) {
                continue;
            } else {
                bites[num] = 1;
            }
        }
        int i = 1;
        for ( ; i < bites.length; i++) {
            if (bites[i] == 0) {
                return i;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Q41firstMissingPositive test = new Q41firstMissingPositive();
        int[] nums = {1,2,100};
        System.out.println(test.firstMissingPositive(nums));
    }
}
