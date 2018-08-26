package training.Leetcode;



public class Q29Devid {
    /**
     * 使用位运算做加法
     * @param num1
     * @param num2
     */
    public static int add(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        int sum = num1 ^ num2;
        int carry = (num1 & num2) << 1;
        return add(sum, carry);
    }

    public static int getSign(int num) {
        return (num >>> 31);
    }

    /**
     * 取绝对值
     * @param num
     * @return
     */
    public static int abs(int num) {
        if (getSign(num) == 1) {
            return negative(num);
        }
        return num;
    }
    /**
     * 取相反数
     * @param i
     * @return
     */
    public static int negative(int i) {
        return add(~i, 1);
    }
    /**
     * 减法是取非后再加1将减数变成绝对值的负数的补码。
     * @param num1
     * @param num2
     * @return
     */
    public static int subtraction(int num1, int num2) {
        return add(num1, negative(num2));
    }
    /**
     *
     * @return
     */
    public static int normalDivide(int dividend, int divisor) {
        boolean isPositive = true;
        if (getSign(dividend) != getSign(divisor)) {
            isPositive = false;
        }
        int result = 0;
        int i = 31;
        int absDividend = abs(dividend);
        int absDivisor = abs(divisor);

        while (i >= 0) {
            if ((absDividend >>> i) >= absDivisor) {
                result = add(result, (1 << i));
                absDividend = subtraction(absDividend, absDivisor << i);
            }
            i = subtraction(i, 1);
        }
        if (!isPositive) {
            return negative(result);
        }
        return result;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE) {
            int result = normalDivide(dividend + 1, divisor);
            if (divisor == 2 || divisor == 1) {
                result -= 1;
            } else if (divisor == -2 || divisor == -1) {
                if (result == Integer.MAX_VALUE) {
                    return result;
                }
                result += 1;
            }
            return result;
        }
        return normalDivide(dividend, divisor);
    }

    public static void main(String[] args) {
        Q29Devid q29Devid = new Q29Devid();
//        System.out.println(q29Devid.divide(10, 3));
//        System.out.println(q29Devid.divide(7, -3));
//        System.out.println(q29Devid.divide(-123123, 3));
//        System.out.println(q29Devid.divide(1, 3));
//        System.out.println(q29Devid.divide(2147483647, 1));
        System.out.println(q29Devid.divide(-2147483648, -2));
        System.out.println(q29Devid.divide(-2147483648, -1));
    }
}
