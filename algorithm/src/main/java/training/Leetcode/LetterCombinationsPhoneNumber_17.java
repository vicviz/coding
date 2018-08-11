/**
 * LetterCombinationsPhoneNumber_17.java, 2016—10-03.
 */
package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.

 0->""
 1->""
 2->"abc"
 3->"def"
 4->"ghi"
 5->"jkl"
 6->"mno"
 7->"pgrs"
 8->"tuv"
 9->"wxyz"
 * @author zhaowei
 */
public class LetterCombinationsPhoneNumber_17 {
    private static Map<Character, Set<Character>> numToCharMap = new HashMap<Character, Set<Character>>(9);
    static {
        Set s2 = new HashSet<Character>(3);
        s2.add('a');
        s2.add('b');
        s2.add('c');
        Set s3 = new HashSet<Character>(3);
        s3.add('d');
        s3.add('e');
        s3.add('f');
        Set s4 = new HashSet<Character>(3);
        s4.add('g');
        s4.add('h');
        s4.add('i');
        Set s5 = new HashSet<Character>(3);
        s5.add('j');
        s5.add('k');
        s5.add('l');
        Set s6 = new HashSet<Character>(3);
        s6.add('m');
        s6.add('n');
        s6.add('o');
        Set s7 = new HashSet<Character>(4);
        s7.add('p');
        s7.add('q');
        s7.add('r');
        s7.add('s');
        Set s8 = new HashSet<Character>(3);
        s8.add('t');
        s8.add('u');
        s8.add('v');
        Set s9 = new HashSet<Character>(4);
        s9.add('w');
        s9.add('x');
        s9.add('y');
        s9.add('z');
        numToCharMap.put('2', s2);
        numToCharMap.put('3', s3);
        numToCharMap.put('4', s4);
        numToCharMap.put('5', s5);
        numToCharMap.put('6', s6);
        numToCharMap.put('7', s7);
        numToCharMap.put('8', s8);
        numToCharMap.put('9', s9);
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        LinkedList<String> result = new LinkedList<>();
        for (char c: digits.toCharArray()) {
            Set<Character> nowLetters = numToCharMap.get(c);
            if (nowLetters == null) {
                continue;
            }
            int size = result.size();
            if (size == 0) {
                for (Character letter: nowLetters) {
                    result.addLast("" + letter);
                }
            } else {
                int i = 0;
                while (i < size) {
                    String str = result.removeFirst();
                    for (Character letter: nowLetters) {
                        result.addLast(str + letter);
                    }
                    i++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber_17 solution = new LetterCombinationsPhoneNumber_17();
        System.out.println(solution.letterCombinations(""));
    }
}
