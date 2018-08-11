/**
 *
 * LongestCommonPrefix_14.java, 2016—10-01.
 *
 */
package training.Leetcode;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * https://leetcode.com/problems/longest-common-prefix/
 * 做一棵前缀树(其实只有一条枝)，先找一下最短的串做这个树可以加快速度，后续的字符串一旦不匹配就剪枝。
 * 注意一下为空或者只有一个字符串的情况
 * @author zhaowei
 */
public class LongestCommonPrefix_14 {
    public static class Node {
        public char c;
        public Node next = null;
    }

    private String findShortestStr(String[] strs) {
        String minStr = strs[0];
        for (String s: strs) {
            if (minStr.length() > s.length()) {
                minStr = s;
            }
        }
        return minStr;
    }

    public Node initTree(String minStr) {
        int i = 0;
        Node root = new Node();
        Node now = root;
        while (i < minStr.length()) {
            Node newNode = new Node();
            newNode.c = minStr.charAt(i);
            now.next = newNode;
            now = newNode;
            i++;
        }
        return root;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String minStr = findShortestStr(strs);
        if (minStr.length() == 0) {
            return "";
        }
        Node root = initTree(minStr);
        for (String s: strs) {
            if (s == null || s.length() == 0) {
                return "";
            }
            int i = 0;
            Node now = root;
            while (i < s.length() && now.next != null) {
                char nextChar = s.charAt(i);
                if (now.next.c == nextChar) {
                    now = now.next;
                } else {
                    now.next = null;
                }
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        Node now = root;
        while (now.next != null) {
            sb.append(now.next.c);
            now = now.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix_14 solution = new LongestCommonPrefix_14();
        System.out.println(solution.longestCommonPrefix(new String[]{"a", "abcd"}));
    }
}
