package training.Leetcode;

public class Q44Regex {

    public boolean isMatch(String s, String p) {
        while (p.indexOf("**") != -1) {
            p = p.replace("**", "*");
        }
        boolean[][] record = new boolean[s.length() + 1][p.length() + 1];

        record[0][0] = true;
        for (int i = 1 ; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                record[0][i] = record[0][i - 1];
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    record[i][j] = record[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    record[i][j] = record[i][j-1] || record[i - 1][j];
                }
            }
        }
        return record[s.length()][p.length()];
    }

    public static void main(String[] args) {
        Q44Regex test = new Q44Regex();
        System.out.println(test.isMatch("bca", "?*"));
        System.out.println(test.isMatch("adceb", "*a*b"));
        System.out.println(test.isMatch("aa", "*"));
        System.out.println(test.isMatch("cb", "?a"));
        System.out.println(test.isMatch("acdcb", "a**c?b"));
    }
}
