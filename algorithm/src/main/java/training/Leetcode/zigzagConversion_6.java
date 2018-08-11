/**
 * @(#)Solution.java
 */
package training.Leetcode;

/**
 * 2016/04/11
 * @author zhaowei
 */
public class zigzagConversion_6 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int[][] charMatrix = new int[numRows][length / 2 + 1];
        int i = 0, j = 0;
        int direct = 1;
        for (int k = 0 ; k < length; k ++) {
            Character c = s.charAt(k);
            if (direct == 1) {
                //down
                if (i < numRows - 1) {
                    charMatrix[i][j] = (int)c;
                    i = i + 1;
                } else {
                    charMatrix[i][j] = (int)c;
                    direct =  -1;
                    j++;
                    i--;
                }
            } else if (direct == -1) {
                if (i > 0) {
                    charMatrix[i][j] = (int)c;
                    i = i - 1;
                    j = j + 1;
                } else {
                    charMatrix[i][j] = (int)c;
                    direct = 1;
                    i++;
                }
            }
        }
        for (i = 0 ; i < numRows; i ++) {
            for (j = 0 ; j < length / 2 + 1;j++) {
                int val = charMatrix[i][j];
                if (val != 0) {
                    sb.append((char)val);
               }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "123";
        int numRows = 1;
        zigzagConversion_6 solution = new zigzagConversion_6();
        System.out.println(solution.convert(s, numRows));
    }
}
