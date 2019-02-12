package training.Leetcode;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 */
public class Q45Jump {

    /**
     * 贪心算法:取下标和值之和最大的那个位置跳
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int index = 0;
        int len = nums.length;
        int step = 0;
        if (nums.length == 1) {
            return 0;
        }
        while (true) {
            int maxIndex = index;
            int max = 0;
            for (int i = index + 1;i <= (index + nums[index]) && i < len; i ++) {
                if (i == len -1) {
                    return step + 1;
                }
                int value = i + nums[i];
                if (value > max) {
                    max = value;
                    maxIndex = i;
                }
            }
            index = maxIndex;
            step ++;
        }
    }

    /**
     * 动态规则
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int[][] jump = new int[nums.length][nums.length];
        for (int distance = 1; distance < nums.length; distance++) {
            for (int i = 0; (i + distance) < nums.length; i++) {
                if (nums[i] >= distance) {
                    jump[i][i + distance] = 1;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int j = 1; j < distance; j++) {
                        if (jump[i][i + j] != 0 && jump[i+j][i+distance] != 0) {
                            int jumpNum = jump[i][i+j] + jump[i+j][i+distance];
                            if (jumpNum < min) {
                                min = jumpNum;
                            }
                        }
                    }
                    if (min < Integer.MAX_VALUE) {
                        jump[i][i+distance] = min;
                    } else {
                        jump[i][i+distance] = 0;
                    }
                }
            }
        }
        return jump[0][nums.length - 1];
    }

    public static void main(String[] args) {
        Q45Jump test = new Q45Jump();
//        int[] a = new int[]{0};
        int[] a = new int[]{2,0,1,1};

        System.out.println(test.jump(a));
    }
}
