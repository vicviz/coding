package training.Leetcode;

import java.util.*;

/**
 * 90. 子集 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (19)
 * 官方题解
 * 提交记录
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class Q90subsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 1) {
            result.add(Collections.emptyList());
            return result;
        }
        Arrays.sort(nums);

        List<Integer> initList = new ArrayList<>(1);
        initList.add(nums[0]);
        result.add(initList);
        Set<String> noRepeat = new HashSet<>();
        noRepeat.add(String.valueOf(nums[0]));

        for (int i = 1 ; i < nums.length; i++) {
            subset(result, nums[i], noRepeat);
        }
        result.add(Collections.emptyList());
        return result;
    }

    private List<List<Integer>> subset(List<List<Integer>> list, int newValue, Set<String> noRepeat) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<Integer> old = list.get(i);
            String newListStr = buildKey(old, newValue);
            if (noRepeat.contains(newListStr)) {
                continue;
            } else {
                List<Integer> newList = new ArrayList<>(old);
                newList.add(newValue);
                list.add(newList);
                noRepeat.add(newListStr);
            }
        }
        if (!noRepeat.contains(String.valueOf(newValue))) {
            List<Integer> initList = new ArrayList<>(1);
            initList.add(newValue);
            list.add(initList);
            noRepeat.add(String.valueOf(newValue));
        }
        return list;
    }

    private String buildKey(List<Integer> list, int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("_");
        }
        sb.append(value).append("_");
        return sb.toString();
    }

    public static void main(String[] args) {
        Q90subsetsWithDup test = new Q90subsetsWithDup();
        int[] a = new int[]{1,2,2};
        List<List<Integer>> result = test.subsetsWithDup(a);
        for (List<Integer> l: result) {
            for (Integer i: l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}