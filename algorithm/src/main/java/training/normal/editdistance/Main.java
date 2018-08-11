package training.normal.editdistance;

/**
 * @author viz
 * 2014.04.03
 */
public class Main {

    /**
     * return distance between s1 and s2.
     * @param s1
     * @param s2
     * @return edit distance
     */
    private static int editDistance(String s1, String s2) {
        if (s1.length() < s2.length()) {
             String temp = s1;
             s1 = s2;
             s2 = temp;
        }
        if ( (s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0) ) {
            return 0;
        } else if (s1 == null || s1.length() == 0) {
            return s2.length();
        } else if (s2 == null || s2.length() == 0) {
            return s1.length();
        } else {
            int min = Integer.MAX_VALUE;
            if (s1.length() > s2.length()) {
                for (int i = 0 ; i < s1.length();i++) {
                    String newString = s1.substring(0,i) + s1.substring(i + 1);
                    int now = editDistance(newString, s2) + 1;
                    if (now < min) {
                        min = now;
                    }
                }
                return min;
            } else {
                if (s1.equals(s2)) {
                    return 0;
                } else {
                    for (int i = 0 ; i < s2.length();i++) {
                        String newString = s2.substring(0,i) + s2.substring(i + 1);
                        int now = editDistance(s1, newString);
                        if (now < min) {
                            min = now;
                        }
                    }
                    return min;
                }
            }
        }
    }

    /**
     * return distance between s1 and s2.
     * @param s1
     * @param s2
     * @return edit distance
     */
    private static int editDistanceDP(String s1, String s2) {
        if ( (s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0) ) {
            return 0;
        } else if (s1 == null || s1.length() == 0) {
            return s2.length();
        } else if (s2 == null || s2.length() == 0) {
            return s1.length();
        }

        int[][] d = new int[s1.length()][s2.length()];
        int i = 0;
        int j = 0;
        for (i = 0 ; i < s1.length();i ++) {
           for (j = 0; j < s2.length();j++) {
               if (i == 0) {
                   d[i][j] = j + 1;
                   continue;
               }
               if (j == 0) {
                   d[i][j] = i + 1;
                   continue;
               }

               if (i == j) {
                   if (s1.charAt(i) == s2.charAt(j)) {
                       d[i][j] = d[i-1][j-1];
                   }
               }

               if (d[i - 1][j] + 1 > d[i][j - 1]) {
                   d[i][j] = d[i][j-1];
               } else {
                   d[i][j] = d[i-1][j];
               }
               if (d[i][j] > d[i - 1][j-1] + 1) {
                   d[i][j] = d[i - 1][j-1] + 1;
               }
           }
        }
        return d[i - 1][j - 1];
    }

    public static void main(String[] args) {
        String test1 = "aabc";
        String test2 = "ac";

        System.out.println(editDistance(test1, test2));
        System.out.println(editDistanceDP(test1, test2));

    }
}
