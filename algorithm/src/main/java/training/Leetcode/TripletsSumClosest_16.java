/**
 *
 * TripletsSumClosest.java, 2016—10-03.
 *
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package training.Leetcode;

import java.util.Arrays;

/**
 *
 https://leetcode.com/problems/3sum-closest/
 Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 Return the sum of the three integers. You may assume that each input would have exactly one solution.

 For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 * @author zhaowei
 */
public class TripletsSumClosest_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int absDiff = Integer.MAX_VALUE;//diff with target
        int closestSum = 0;
        for (int i = 0 ; i < length; i++) {
            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int nowDiff = Math.abs(sum - target);
                if (nowDiff < absDiff) {
                    absDiff = nowDiff;
                    closestSum = sum;
                }
                if (sum > target) {
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    return target;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        TripletsSumClosest_16 solution = new TripletsSumClosest_16();
        System.out.println(solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }
}
