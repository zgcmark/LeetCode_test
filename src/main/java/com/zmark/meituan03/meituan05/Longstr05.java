package com.zmark.meituan03.meituan05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * @author zhengguangchen
 */
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
//        示例 1：
//
//        输入: "babad"
//        输出: "bab"
//        注意: "aba" 也是一个有效答案。
//        示例 2：
//
//        输入: "cbbd"
//        输出: "bb"

public class Longstr05 {
    public String longestPalindrome(String s) {
        //定义状态  dp[i][j]=true  ,true 是 回文, false 不是
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        TreeMap<Integer,String> ans = new TreeMap<>();
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < len; i++) {
                //左右相等
                if (chars[i]==chars[j] && chars[i+1]==chars[j-1] && j-i>2){
                    dp[i][j]=true;
                    ans.put( s.substring(i,j).length(),s.substring(i,j));
                }else {
                    dp[i][j]=dp[i+1][j-1];
                }
            }
        }
        return ans.firstEntry().getValue();
        //定义状态转移方程
//        dp[i][j]=true


    }
}
