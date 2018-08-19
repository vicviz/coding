package training.Leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q13RomanToInt {
    private static int[] intArray = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] charArray = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static Map<String, Integer> map = new HashMap<>(charArray.length);
    static {
        for (int i = 0 ; i < charArray.length; i++) {
            map.put(charArray[i], intArray[i]);
        }
    }

    public int romanToInt(String num) {
        int sum = 0;
        char[] combine = new char[2];
        for (int i = 0 ; i < num.length();) {
            if (i == num.length() - 1) {
                sum += map.get(String.valueOf(num.charAt(i)));
                i += 1;
                continue;
            }
            char c = num.charAt(i);
            char next = num.charAt(i+1);
            combine[0] = c;
            combine[1] = next;

            String combineStr = new String(combine);
            if (map.containsKey(combineStr)) {
                sum += map.get(combineStr);
                i += 2;
            } else {
                sum += map.get(String.valueOf(c));
                i += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Q13RomanToInt q13RomanToInt = new Q13RomanToInt();
        System.out.println("III:" + q13RomanToInt.romanToInt("III"));
        System.out.println("M:" + q13RomanToInt.romanToInt("M"));
        System.out.println("MMMCMXCIX:" + q13RomanToInt.romanToInt("MMMCMXCIX"));
        System.out.println("IV:" + q13RomanToInt.romanToInt("IV"));
        System.out.println("IX:" + q13RomanToInt.romanToInt("IX"));
        System.out.println("LVIII:" + q13RomanToInt.romanToInt("LVIII"));
        System.out.println("MCMXCIV:" + q13RomanToInt.romanToInt("MCMXCIV"));
        System.out.println("I:" + q13RomanToInt.romanToInt("I"));
    }
}
