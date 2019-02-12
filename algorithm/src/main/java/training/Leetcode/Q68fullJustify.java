package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 *
 *
 *
 *
 * 题目描述
 * 评论 (9)
 * 官方题解
 * 提交记录
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class Q68fullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int lineWordNum = 0;
        int lineLen = 0;
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            lineLen += words[i].length();
            lineWordNum++;
            i++;
            if ( (i < words.length && lineLen + words[i].length() + lineWordNum > maxWidth)
                    || (i == words.length)) {
                int spaceNumAll = maxWidth - lineLen;
                int spaceNumEvery = lineWordNum > 1 ? (spaceNumAll / (lineWordNum - 1)) : spaceNumAll;
                int remain = lineWordNum > 1 ? spaceNumAll % (lineWordNum - 1): 0;
                StringBuilder sb = new StringBuilder();
                int remainCount = 0;
                for (int j = 0; j < lineWordNum; j++) {
                    sb.append(words[i  - (lineWordNum - j)]);
                    if (lineWordNum != 1 && j != lineWordNum - 1) {
                        for (int k = 0; k < spaceNumEvery; k++) {
                            sb.append(" ");
                        }
                    }
                    if (lineWordNum == 1) {
                        for (int k = 0; k < spaceNumEvery; k++) {
                            sb.append(" ");
                        }
                    }
                    if (remain != 0 && remainCount < remain) {
                        sb.append(" ");
                        remainCount ++;
                    }
                    if (j == lineWordNum - 1) {
                        break;
                    }
                }
                result.add(sb.toString());
                lineLen = 0;
                lineWordNum = 0;
            }
        }
        String last = result.get(result.size() - 1);
        result.remove(result.size() - 1);
        result.add(cleanLatest(last, maxWidth));
        return result;
    }

    private String cleanLatest(String last, int maxWidth) {
        while (last.indexOf("  ") != -1) {
            last = last.replace("  ", " ");
        }
        StringBuilder sb = new StringBuilder();
        String trim = last.trim();
        sb.append(trim);
        int spaceNum = maxWidth - last.trim().length();
        for (int i = 0; i < spaceNum; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q68fullJustify test = new Q68fullJustify();
        String[] a = new String[]{
//                "What","must","be","acknowledgment","shall","be"
//                "This", "is", "an", "example", "of", "text", "justification."
               "Science","is","what","we","understand","well",
                "enough","to","explain","to","a","computer.",
                "Art","is","everything","else","we","do"
        };
        System.out.println(test.fullJustify(a, 20));
    }
}
