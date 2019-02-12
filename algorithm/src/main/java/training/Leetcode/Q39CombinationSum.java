package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q39CombinationSum {

    public static class Nodes {
        private int sum;
        private List<Integer> nodes = new ArrayList<>();

        public void addNum(int num) {
            sum += num;
            nodes.add(num);
        }

        public Nodes cloneNode() {
            Nodes newNodes = new Nodes();
            for (Integer num: nodes) {
                newNodes.addNum(num);
            }
            return newNodes;
        }
    }

    private Nodes buildNewNodes(Nodes node, int nowNum, int times) {
        Nodes newNode = node.cloneNode();
        for (int i = 0; i < times; i++) {
            newNode.addNum(nowNum);
        }
        return newNode;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Nodes> list = new ArrayList<>();
        List<Nodes> result = new ArrayList<>();
        list.add(new Nodes());

        for (int i = 0 ; i < candidates.length;i++) {
            int nowNum = candidates[i];
            List<Nodes> newList = new ArrayList<>();
            for (Nodes nodes: list) {
                Nodes newNode = nodes.cloneNode();
                newList.add(newNode);
                int times = 0;
                while (newNode.sum < target) {
                    times++;
                    newNode = buildNewNodes(nodes, nowNum, times);
                    if (newNode.sum == target) {
                        result.add(newNode);
                        break;
                    } else if (newNode.sum < target) {
                        newList.add(newNode);
                    }
                }
            }
            list = newList;
        }
        List<List<Integer>> print = new ArrayList<>();
        for (Nodes nodes: result) {
            print.add(nodes.nodes);
        }
        return print;
    }

    public static void main(String[] args) {
        Q39CombinationSum test = new Q39CombinationSum();
        int[] candidates = {2,3,5};
        int target = 8;
        List<List<Integer>> result = test.combinationSum(candidates, target);
        for (List<Integer> list: result) {
            System.out.print("[");
            for (Integer num: list) {
                System.out.print(num + ",");
            }
            System.out.print("]\n");
        }
    }
}
