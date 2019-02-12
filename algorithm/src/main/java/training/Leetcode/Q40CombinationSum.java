package training.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q40CombinationSum {

    private void find(List<List<Integer>> result, List<Integer> list, int[] candidates, int remain, int cur) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = cur; i < candidates.length; i++) {
            if (i > cur && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            find(result, list, candidates, remain - candidates[i] , i + 1);
            ((LinkedList)list).removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        find(result, list, candidates, target, 0);
        return result;
    }


    public static void main(String[] args) {
        Q40CombinationSum test = new Q40CombinationSum();
//        int[] candidates = {10,1,2,7,6,1,5};
        int[] candidates = {1,1,2,2};
        int target = 6;
        List<List<Integer>> result = test.combinationSum2(candidates, target);
        for (List<Integer> list: result) {
            System.out.print("[");
            for (Integer num: list) {
                System.out.print(num + ",");
            }
            System.out.print("]\n");
        }
    }
}
