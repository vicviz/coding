package training.Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 https://oj.leetcode.com/problems/two-sum/


 Given an array of integers, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2

 * Created by vicviz on 2015/2/12.
 */
public class TowSum_1 {
    public int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int index = 0 ; index < nums.length; index ++) {
            int ele = nums[index];
            if (map.containsKey(ele)) {
                result[0] = map.get(ele);
                result[1] = index;
                return result;
            } else {
                int match = target - ele;
                map.put(match, index);
            }
        }
        return result;
    }
}
