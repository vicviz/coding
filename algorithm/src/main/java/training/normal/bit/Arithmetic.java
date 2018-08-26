package training.normal.bit;

public class Arithmetic {
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

    public static int getSign(int num) {
        return (num >> 31);
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
     * 不考虑溢出的情况
     * @param num1
     * @param num2
     * @return
     */
    public static int mult(int num1, int num2) {
        int sign1 = getSign(num1);
        int sign2 = getSign(num2);
        boolean isPositive = true;
        if (sign1 != sign2) {
            isPositive = false;
        }
        int absNum1 = abs(num1);
        int absNum2 = abs(num2);
        int result = 0;
        while (absNum2!= 0) {
            if ((absNum2 & 1) == 1) {
                result = add(result, absNum1);
            }
            absNum1 = absNum1 << 1;
            absNum2 = (absNum2 >> 1);
        }
        if (!isPositive) {
            return negative(result);
        }
        return result;
    }

    /**
     *
     * @return
     */
    public static int divide(int dividend, int divisor) {
        boolean isPositive = true;
        if (getSign(dividend) != getSign(divisor)) {
            isPositive = false;
        }
        int result = 0;
        int i = 31;
        int absDividend = abs(dividend);
        int absDivisor = abs(divisor);

        while (i > 0) {
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

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(3));
//        System.out.println(Integer.toBinaryString(-3));//转出来是原码的反码+1，反码是除符号位外各位取反
//        System.out.println(Integer.toBinaryString(~3));//转出来是原码的反码+1，反码是除符号位外各位取反
//        System.out.println(add(1, 2));
//        System.out.println(minus(1, 2));


//        System.out.println(mult(5, 6));
        System.out.println(divide(13, 6));
    }
}
