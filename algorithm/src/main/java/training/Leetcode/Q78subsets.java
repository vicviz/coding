package training.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 78. 子集
 *给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Q78subsets {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            result.add(list);
        }
        if (nums.length <= 1) {
            result.add(Collections.emptyList());
            return result;
        }
        boolean add = true;
        int nowStart = 0;
        while (add == true) {
            add = false;
            int size = result.size();
            for (int i = nowStart ; i < size;i++) {
                List<Integer> list = result.get(i);
                Integer last = list.get(list.size() - 1);
                int start = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == last) {
                        start = j + 1;
                        break;
                    }
                }
                for (int j = start; j < nums.length; j++) {
                    List newList = new ArrayList(list);
                    newList.add(nums[j]);
                    result.add(newList);
                    add = true;
                }
            }
            nowStart = size;
        }
        result.add(Collections.emptyList());
        return result;
    }

    public static void main(String[] args) {
        Q78subsets test = new Q78subsets();
        int[] a = new int[]{4};
        List<List<Integer>> result = test.subsets(a);
        for (List<Integer> l: result) {
            for (Integer i: l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
