package com.zmark.meituan03.meituan714;

/**
 * @author zhengguangchen
 */


public class Proift714 {
//    给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
//
//    你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//
//    返回获得利润的最大值。
//
//    注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
//
//    示例 1:
//
//    输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//    输出: 8
//    解释: 能够达到的最大利润:
//    在此处买入 prices[0] = 1
//    在此处卖出 prices[3] = 8
//    在此处买入 prices[4] = 4
//    在此处卖出 prices[5] = 9
//    总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

//    动态规划解题步骤
//        1.定义状态,用多元数组来定义 如果超过3元还不能，我理解动态规划就不能解决这种问题
//        2.定义状态的转化方程
//          3.定义初始化状态
//          4.定义结束状态
    public int maxProfit(int[] prices, int fee) {
        //1.定义状态  0是不持股 1是持股
        int[][] dp = new int[prices.length][2];
        //3.定义初始化状态
        dp[0][0]=0;
        dp[0][1]=-prices[0]-fee;
        //4.定义结束状态 
        for (int i = 1; i < prices.length; i++) {
            //2.定义状态状态转移方程
            //当天不持股
              //前天持股
              //前天不持股
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //当天持股
            dp[i][1]=Math.max(dp[i-1][0]-prices[i]-fee,dp[i-1][1]);
        }

        return dp[prices.length-1][0];
    }
}
