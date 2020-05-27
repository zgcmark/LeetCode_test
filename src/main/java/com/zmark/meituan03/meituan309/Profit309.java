package com.zmark.meituan03.meituan309;

/**
 * @author zhengguangchen
 */
//
//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
//
//        设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
//
//        你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//        卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
//        示例:
//
//        输入: [1,2,3,0,2]
//        输出: 3
//        解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]


public class Profit309 {

    public int maxProfit(int[] prices) {

        //1.定义状态
        //0 持股  1不持股  2是 冻结    dp[][]=收益
        int[][] dp = new int[prices.length][3];
        //2.定义初始化
        dp[0][0]=-prices[0];
        dp[0][1]=0;
        dp[0][2]=0;
        //3.转义转化方程
        for (int i = 1; i < prices.length; i++) {
            //如果当天持股
              //1.昨天冻结    dp[i-1][2]-prices[i]
              //1.昨天持股  dp[i-1][0]
              //1.不持股  dp[i-1][1]-prices[i] 这个情况不存在的
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][2]-prices[i]);

            //如果当天不持股
             //2.昨天冻结  dp[i-1][2]
             //3.昨天持股  dp[i-1][0]+prices[i]
             //3.昨天不持股  dp[i-1][1]
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);


            //如果当天冻结，昨天肯定不持股
            //当天先买在卖
            //昨天卖  dp[i-1][1]
            dp[i][2]=dp[i-1][1];
        }

        //4.定义结尾
        return Math.max(dp[prices.length-1][1],dp[prices.length-1][2]);
    }

}
