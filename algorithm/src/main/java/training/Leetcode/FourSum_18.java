/**
 *
 * FourSum.java, 2016—10-05.
 *
 */
package training.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 https://leetcode.com/problems/4sum/
 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 Find all unique quadruplets in the array which gives the sum of target.

 Note: The solution set must not contain duplicate quadruplets.

 For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 * @author zhaowei
 */
public class FourSum_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int length = nums.length;
        for (int i = 0 ; i < length;i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < length;j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int m = j + 1;
                int n = length - 1;
                while (m < n) {
                    if (m > j + 1 && nums[m] == nums[m-1]) {
                        m++;
                        continue;
                    }
                    if (n < length - 1 && nums[n] == nums[n+1]) {
                        n--;
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum == target) {
                        List<Integer> oneResult = new ArrayList<Integer>(4);
                        oneResult.add(nums[i]);
                        oneResult.add(nums[j]);
                        oneResult.add(nums[m]);
                        oneResult.add(nums[n]);
                        result.add(oneResult);
                        m++;
                    } else if (sum < target) {
                        m++;
                    } else if (sum > target) {
                        n--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //[-3,-2,-1,0,0,1,2,3]->[[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        //[1, 0, -1, 0, -2, 2]->[[-1,  0, 0, 1],[-2, -1, 1, 2],[-2,  0, 0, 2]]
        FourSum_18 solution = new FourSum_18();
        System.out.println(solution.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0));
    }
}
