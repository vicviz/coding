package training.Leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 *判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 说明:
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */
public class Q36isValidSudoku {
    private boolean isValidLine(char[][] board, int i,  int begin, int end) {
        Set<Character> characters = new HashSet<>();
        for (int j = begin ; j < end; j++) {
            char c = board[i][j];
            if (c != '.') {
                if (characters.contains(c)) {
                    return false;
                }
                characters.add(c);
            }
        }
        return true;
    }

    private boolean isValidColum(char[][] board, int begin, int end, int j) {
        Set<Character> characters = new HashSet<>();
        for (int i = begin ; i < end; i++) {
            char c = board[i][j];
            if (c != '.') {
                if (characters.contains(c)) {
                    return false;
                }
                characters.add(c);
            }
        }
        return true;
    }

    private boolean isValidInSub(char[][] board, int row, int colum) {
        Set<Character> characters = new HashSet<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = colum; j < colum + 3; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (characters.contains(c)) {
                        return false;
                    }
                    characters.add(c);
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0 ; i < 9; i++) {
            if (!isValidLine(board, i, 0, 9)) {
                return false;
            }
        }

        for (int j = 0 ; j < 9; j++) {
            if (!isValidColum(board, 0, 9, j)) {
                return false;
            }
        }
        for (int i = 0 ; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidInSub(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q36isValidSudoku test = new Q36isValidSudoku();
        char[][] data = {
                {'.','.','.', '.','5','.', '.','1','.'},
                {'.','4','.', '3','.','.', '.','.','.'},
                {'.','.','.', '.','.','3', '.','.','1'},

                {'8','.','.', '.','.','.', '.','2','.'},
                {'.','.','2', '.','7','.', '.','.','.'},
                {'.','1','5', '.','.','.', '.','.','.'},

                {'.','.','.', '.','.','2', '.','.','.'},
                {'.','2','.', '9','.','.', '.','.','.'},
                {'.','.','4', '.','.','.', '.','.','.'}};
        System.out.println(test.isValidSudoku(data));
    }
}
