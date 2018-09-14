package training.normal.string;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullPermutation {

    private static class Node {
        String value;
        public Node(String value) {
            this.value = value;
        }
    }

    private static void swap(List<Node> list, int i, int j) {
        Node temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static void record(List<Node> list, List<String> result) {
        StringBuilder sb = new StringBuilder();
        for (Node node: list) {
            sb.append(node.value);
        }
        result.add(sb.toString());
    }

    private static List<String> buildAllSubString(String[] words) {
        List<String> result = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        for (String word: words) {
            Node node = new Node(word);
            nodes.add(node);
        }
        record(nodes, result);
        buildAllSubString(nodes, 0, result);
        return result;
    }

    private static void buildAllSubString(List<Node>nodes, int start, List<String> result) {
        for (int i = start + 1; i < nodes.size();i++) {
            swap(nodes, start, i);
            record(nodes, result);
            buildAllSubString(nodes, start + 1, result);
            swap(nodes, start, i);//swap back
        }
        if (start + 1 < nodes.size()) {
            buildAllSubString(nodes, start + 1, result);
        }
    }

    public static void main(String[] args) {
        String[] test = {"word","good","best"};
        List<String> reslut = buildAllSubString(test);
        for (String one: reslut) {
            System.out.println(one);
        }
    }
}
