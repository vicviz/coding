package training.Leetcode;

public class Q43StringMultiply {

    private String revert(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private String multiplyOneNum(String num1, char c, int zeroNum) {
        if (c == 0) {
            return  "";
        }
        int cNum = c - '0';
        StringBuilder sb = new StringBuilder();
        int plus = 0;
        for (int i = num1.length() - 1 ; i >= 0; i--) {
            char num1Char = num1.charAt(i);
            int multValue = (num1Char - '0') * cNum + plus;
            sb.append(multValue % 10);
            plus = multValue / 10;
        }
        if (plus != 0) {
            sb.append(plus);
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0 ; i < zeroNum; i++) {
            all.append("0");
        }
        String oneNumResult = all.append(sb.toString()).toString();
        return oneNumResult;
    }

    private String add(String all, String now) {
        int plus = 0;
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (; i < all.length() && i < now.length(); i++) {
            char a = all.charAt(i);
            char b = now.charAt(i);
            int sum = (a - '0') + (b - '0') + plus;
            result.append(sum % 10);
            plus = sum / 10;
        }
        if (i < all.length()) {
            while (i < all.length()) {
                int sum = all.charAt(i) - '0' + plus;
                result.append(sum % 10);
                plus = sum / 10;
                i++;
            }
        } else if (i < now.length()) {
            while (i < now.length()) {
                int sum = now.charAt(i) - '0' + plus;
                result.append(sum % 10);
                plus = sum / 10;
                i++;
            }
        }
        if (plus > 0) {
            result.append(plus);
        }
        return result.toString();
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String allStr = "";
        int zeroNum = 0;
        for (int i = num2.length() - 1 ; i >= 0; i--) {
            char nowNum2Char = num2.charAt(i);
            String multOneNum = multiplyOneNum(num1, nowNum2Char, zeroNum);
            allStr = add(allStr, multOneNum);
            zeroNum++;
        }
        return revert(allStr);
    }

    public static void main(String[] args) {
        Q43StringMultiply test = new Q43StringMultiply();
        System.out.println(test.multiply("2", "5"));
    }
}
