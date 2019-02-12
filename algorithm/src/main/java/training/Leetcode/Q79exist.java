package training.Leetcode;

import java.util.*;

/**
 * 79. 单词搜索
 *
 *
 *
 *
 * 题目描述
 * 评论 (18)
 * 官方题解
 * 提交记录
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class Q79exist {
    public boolean exist(char[][] board, String word) {
        Map<Character, List<String>> map = new HashMap<>();
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0 ; j < board[0].length; j++) {
                char c = board[i][j];
                List<String> l = map.get(c);
                if (l == null) {
                    l = new ArrayList<>();
                    map.put(c, l);
                }
                l.add(i + "_" + j);
            }
        }
        char first = word.charAt(0);
        List<String> locals = map.get(first);
        if (locals == null || locals.size() == 0) {
            return false;
        }
        for (String local: locals) {
            String[] array = local.split("_");
            int i = Integer.parseInt(array[0]);
            int j = Integer.parseInt(array[1]);
            Map<String, String> used = new HashMap<>();
            used.put(i + "_" + j, "");
            boolean find = search(board, i, j, word, 0, used);
            if (find) {
                return true;
            }
        }
        return false;
    }

    private boolean search(char[][] board, int i, int j, String word, int index, Map<String, String> used) {
        if (index == word.length() -1) {
            return true;
        }
        char next = word.charAt(index + 1);
        Map<String, String> newMapUp = new HashMap<>(used);
        String key = (i - 1) + "_" + j;
        if (i - 1 >= 0 && next == board[i - 1][j] && !newMapUp.containsKey(key)) {
            newMapUp.put(key, "");
            boolean up = search(board, i - 1, j, word, index + 1, newMapUp);
            if (up) {
                return true;
            }
        }

        Map<String, String> newMapDown = new HashMap<>(used);
        key = (i + 1) + "_" + j;
        if (i + 1 < board.length && next == board[i + 1][j] && !newMapDown.containsKey(key)) {
            newMapDown.put(key, "");
            boolean down = search(board, i + 1, j, word, index + 1, newMapDown);
            if (down) {
                return true;
            }
        }
        Map<String, String> newMapRight = new HashMap<>(used);
        key = i + "_" + (j + 1);
        if (j + 1 < board[0].length  && next == board[i][j + 1] && !newMapRight.containsKey(key)) {
            newMapRight.put(key, "");
            boolean right = search(board, i, j + 1, word, index + 1, newMapRight);
            if (right) {
                return true;
            }
        }
        Map<String, String> newMapLeft = new HashMap<>(used);
        key = i + "_" + (j - 1);
        if (j - 1 >= 0 && next == board[i][j - 1] && !newMapLeft.containsKey(key)) {
            newMapLeft.put(key, "");
            boolean left = search(board, i, j - 1, word, index + 1, newMapLeft);
            if (left) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q79exist test = new Q79exist();
//        char[][] m = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'} };
//        System.out.println(test.exist(m, "ABCB"));
        char[][] m = {{'a', 'b'}};
        System.out.println(test.exist(m, "ba"));
    }
}
