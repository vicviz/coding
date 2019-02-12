package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

 如果不存在最后一个单词，请返回 0 。

 说明：一个单词是指由字母组成，但不包含任何空格的字符串。

 示例:

 输入: "Hello World"
 输出: 5
 */
public class Q58lengthOfLastWord {
    public int lengthOfLastWord(String s) {
        boolean isInWord = false;
        int lastWordNum = 0;
        for (int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isInWord) {
                if (c == ' ') {
                    continue;
                } else {
                    isInWord = true;
                    lastWordNum = 1;
                }
            } else {
                if (c == ' ') {
                    isInWord = false;
                } else {
                    lastWordNum++;
                }
            }
        }
        return lastWordNum;
    }

    public static void main(String[] args) {
        Q58lengthOfLastWord test = new Q58lengthOfLastWord();
        System.out.println(test.lengthOfLastWord("a "));
    }
}
