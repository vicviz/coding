package training.Leetcode;

import java.util.*;

/**
 给定一个没有重复数字的序列，返回其所有可能的全排列。

 示例:

 输入: [1,2,3]
 输出:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 */
public class Q46permute {
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

    public List<List<Integer>> permute(int[] nums) {
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
        return lists;
    }
    public static void main(String[] args) {
        Q46permute test = new Q46permute();
        System.out.println(test.permute(new int[]{1,2, 3}));
    }
}
