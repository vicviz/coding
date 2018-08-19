package training.Leetcode;

public class Q12IntToRoman {
    private static int[] intArray = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] charArray = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = 0; i < intArray.length; i++) {
                if (num - intArray[i] >= 0) {
                    sb.append(charArray[i]);
                    num = num - intArray[i];
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q12IntToRoman q12IntToRoman = new Q12IntToRoman();
        System.out.println("3:" + q12IntToRoman.intToRoman(3));
        System.out.println("1000:" + q12IntToRoman.intToRoman(1000));
        System.out.println("3999:" + q12IntToRoman.intToRoman(3999));
        System.out.println("4:" + q12IntToRoman.intToRoman(4));
        System.out.println("9:" + q12IntToRoman.intToRoman(9));
        System.out.println("58:" + q12IntToRoman.intToRoman(58));
        System.out.println("1994:" + q12IntToRoman.intToRoman(1994));
        System.out.println("1:" + q12IntToRoman.intToRoman(1));
    }
}
