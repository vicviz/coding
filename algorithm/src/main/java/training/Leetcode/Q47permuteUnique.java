package training.Leetcode;

import java.util.*;

/**
 给定一个可包含重复数字的序列，返回所有不重复的全排列。

 示例:

 输入: [1,1,2]
 输出:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */
public class Q47permuteUnique {
    private int factorial(int size) {
        int num = 1;
        for (int i = 1; i <= size; i++) {
             num *= i;
        }
        return num;
    }

    List<List<Integer>> insert(LinkedList<Integer> list, int num) {
        List<List<Integer>> newLists = new ArrayList<>(list.size() + 1);
        for (int i = 0; i <= list.size(); i++) {
            LinkedList linkedList = (LinkedList) list.clone();
            linkedList.add(i, num);
            newLists.add(linkedList);
        }
        return newLists;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length == 0) {
            return lists;
        }
        LinkedList<Integer> one = new LinkedList<>();
        one.add(nums[0]);
        lists.add(one);
        if (nums.length == 1) {
            return lists;
        }
        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> newLists = new ArrayList<>(factorial(i));
            for (int j = 0; j < lists.size();j++) {
                LinkedList<Integer> list = (LinkedList<Integer>) lists.get(j);
                newLists.addAll(insert(list, nums[i]));
            }
            lists = newLists;
        }
        return clean(lists);
    }

    public List<List<Integer>> clean(List<List<Integer>> lists) {
        Map<String, String> repeat = new HashMap<>();
        List<List<Integer>> newLists = new ArrayList<>();
        for (List<Integer> list: lists) {
            StringBuilder sb = new StringBuilder();
            for (Integer num: list) {
                sb.append(num).append("_");
            }
            String key = sb.toString();
            if (repeat.containsKey(key)) {
                continue;
            } else {
                repeat.put(key, "");
                newLists.add(list);
            }
        }
        return newLists;
    }
}
