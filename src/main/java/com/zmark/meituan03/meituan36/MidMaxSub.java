package com.zmark.meituan03.meituan36;

/**
 * @author zhengguangchen
 */
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
//        示例:
//
//        输入: [-2,1,-3,4,-1,2,1,-5,4],
//        输出: 6
//        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

public class MidMaxSub {
    public int maxSubArray(int[] nums) {
        Integer sum=nums[0];
        Integer subMax=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(subMax>0){
                subMax=subMax+nums[i];
            }else {
                subMax = nums[i];
            }
            sum = Math.max(sum, subMax);
        }
        return sum;
    }


}
