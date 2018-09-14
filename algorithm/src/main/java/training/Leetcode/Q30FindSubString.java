package training.Leetcode;


import java.util.*;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1:
 *
 * 输入:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出: [0,9]
 * 解释: 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2:
 *
 * 输入:
 *   s = "wordgoodstudentgoodword",
 *   words = ["word","student"]
 * 输出: []
 *
 * 思路:
 * 1. 所给的子串是等长的，使用一个个的窗口来匹配。而窗口内只需要最开头参差不齐就可以了
 */
public class Q30FindSubString {

    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) {
            return Collections.emptyList();
        }
        int allLength = 0;
        int len = words[0].length();
        allLength += len * words.length;
        if (s == null || s.length() < allLength) {
            return Collections.emptyList();
        }
        Set<Integer> result = new HashSet<>();
        for (int i = 0 ; i <= (s.length() - allLength) && i < len;i++) {
            checkMatch(s, i, len, words, result);
        }
        return new ArrayList<>(result);
    }

    private void checkMatch(String s, int start, int len, String[] words, Set<Integer> result) {
        int index = start + len;
        Map<String, Integer> dynamicMap = new HashMap<>(words.length);
        for (String word: words) {
            dynamicMap.put(word, dynamicMap.getOrDefault(word, 0) + 1);
        }
        while (index <= s.length()) {
            String word = s.substring(index - len, index);
            if (dynamicMap.containsKey(word)) {
                if (dynamicMap.containsKey(word)) {
                    Integer num = dynamicMap.get(word);
                    if (num == 1) {
                        dynamicMap.remove(word);
                    } else {
                        dynamicMap.put(word, num - 1);
                    }
                }
                if (dynamicMap.isEmpty()) {
                    result.add(start);
                    String headWord = s.substring(start, start + len);
                    dynamicMap.put(headWord, dynamicMap.getOrDefault(headWord, 0) + 1);
                    start += len;
                }
                index += len;
            } else {
                String headWord = s.substring(start, start + len);
                start += len;
                dynamicMap.put(headWord, dynamicMap.getOrDefault(headWord, 0) + 1);
            }
        }
    }

    public static void main(String[] args) {
//        String[] test = {"word","good","best","word"};
//        Q30FindSubString q30FindSubString = new Q30FindSubString();
//        List<Integer> result = q30FindSubString.findSubstring("wordgoodgoodgoodbestword", test);
//        for (Object a: result) {
//            System.out.print(a + ",\t");
//        }
//
//        String[] test = {"word","good","best","good"};
//        Q30FindSubString q30FindSubString = new Q30FindSubString();
//        List<Integer> result = q30FindSubString.findSubstring("wordgoodgoodgoodbestword", test);
//        for (Object a: result) {
//            System.out.print(a + ",\t");
//        }


        String[] test = {"bar","foo"};
        Q30FindSubString q30FindSubString = new Q30FindSubString();
        List<Integer> result = q30FindSubString.findSubstring("foobarfoobar", test);
        for (Object a: result) {
            System.out.print(a + ",\t");
        }

    }
}
