package training.Leetcode;

import java.util.*;

/**给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

 示例:

 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 输出:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 说明：

 所有输入均为小写字母。
 不考虑答案输出的顺序。
 */
public class Q49groupAnagrams {

    private String sortStr(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0 ; i < strs.length; i++) {
            String str = strs[i];
            String key = sortStr(str);
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(str);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> value: map.values()) {
            result.add(value);
        }
        return result;
    }
    public static void main(String[] args) {
        String[] s = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Q49groupAnagrams test = new Q49groupAnagrams();
        System.out.println(test.groupAnagrams(s));
    }
}
