package training.Leetcode;

/**
 *122. 买卖股票的最佳时机 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (129)
 * 官方题解
 * 提交记录
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Q122maxProfit {

    /**
     * 如果可以当天卖并且当天买
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        int pre = prices[0];
        for (int i = 1; i < prices.length; i++) {
           if (prices[i] < pre) {
               pre = prices[i];
           } else {
               max += prices[i] - pre;
               pre = prices[i];
           }
        }
        return max;
    }

    /**
     * 如果不能当天卖并且当天买
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] record = new int[prices.length + 1][prices.length + 1];
        for (int i = 0 ;i < record.length; i++) {
            for (int j = 0; j < record[0].length; j++) {
                record[i][j] = -1;
            }
        }
        int max = max(prices,0, prices.length - 1, record);
        return max;
    }

    private int max(int[] prices, int i, int j, int[][] record) {
        if (record[i][j] != -1) {
            return record[i][j];
        }
        int max = 0;
        if (i >= j) {
            record[i][j] = 0;
            return 0;
        }
        if (i == j - 1) {
            int maxNow = prices[j]- prices[i];
            if (max < maxNow) {
                max = maxNow;
            }
            record[i][j] = max;
            return max;
        }

        for (int k = i + 1; k < j; k++) {
            int maxNum = max(prices, i, k, record) + max(prices, k + 1, j, record);
            if (max < maxNum) {
                max = maxNum;
            }
        }
        int one = prices[j] - prices[i];
        if (max < one) {
            max = one;
        }
        one = max(prices, i + 1, j, record);
        if (max < one) {
            max = one;
        }
        record[i][j] = max;
        return max;
    }

    public static void main(String[] args) {
        Q122maxProfit test = new Q122maxProfit();
        int[] m = {7,1,5,3,6,4};
//        int[] m = {7,5,3,2,1};
        System.out.println(test.maxProfit(m));
    }
}