/**
 *
 * TripletsSum_15.java, 2016—10-02.
 *
 */
package training.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/3sum/
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
[-1, 0, 1],
[-1, -1, 2]
]

 注意事项：非常容易超时。不能使用N^2*logN的方法。只能使用N^2的，也就是对一个数，再找后面的数相加和为这个数的相反数。找的时候使用前后指针移动的方法。
 另外对3个数都需要注意如果是相同的，则不再计算,可见代码中的same注释的部分。否则会超时
 对于重复，我用一个key来判断
 * @author zhaowei
 */
public class TripletsSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int length = nums.length;
        Set<String> noRepeativeSet = new HashSet<String>();

        for (int i = 0 ; i < length; i++) {
            if (i > 0) {
                // same
                if (nums[i] == nums[i-1]) {
                    continue;
                }
            }
            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                if (j > i + 1) {
                    // same
                    if (nums[j] == nums[j-1]) {
                        j++;
                        continue;
                    }
                }
                if (k + 1 < length) {
                    // same
                    if (nums[k] == nums[k+1]) {
                        k--;
                        continue;
                    }
                }
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    String key = buildKey(nums[i], nums[j], nums[k]);
                    if (!noRepeativeSet.contains(key)) {
                        noRepeativeSet.add(key);
                        List<Integer> one = new ArrayList<Integer>(3);
                        one.add(nums[i]);
                        one.add(nums[j]);
                        one.add(nums[k]);
                        result.add(one);
                    }
                    j++;
                }
            }
        }
        return result;
    }

    private static String buildKey(int one, int two, int three) {
        int[] array = new int[3];
        array[0] = one;
        array[1] = two;
        array[2] = three;
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]).append("_").append(array[1]).append("_").append(array[2]);
        return sb.toString();
    }


    public static void main(String[] args) {
        TripletsSum_15 solution = new TripletsSum_15();

        System.out.println(solution.threeSum(new int[]{-2,0,0,2,2}));
//        System.out.println(solution.threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(solution.threeSum(new int[]{7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,
//                -14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,
//                11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,
//                -2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6}));
    }
}
