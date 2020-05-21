package com.zmark.meituan03.meituan340;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhengguangchen
 */


public class Hard340 {

//    给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
//
//    示例 1:
//
//    输入: s = "ecebaeceba", k = 2
//    输出: 3
//    解释: 则 T 为 "ece"，所以长度为 3。
//    示例 2:

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (s.length()*k == 0) return 0;




        //<key，最后一次出现的下标>
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        int ans=0;
        int left=0;
        int right=0;
            while (right<s.length()){
            char c = s.charAt(right);
            //这个地方最开始的时候没写，吃了基础不牢固的坑，因为，put可以覆盖值，但是不能覆盖位置
            if (map.containsKey(c)){
                map.remove(c);
            }
            map.put(c,right++);

            if (map.size()>k){
                //需要左边滑动
                Map.Entry<Character, Integer> next = map.entrySet().iterator().next();
                Character key = next.getKey();
                Integer integer = map.get(key);
                map.remove(key);
                left=Math.max(integer+1,left);
            }
            ans=Math.max(right-left,ans);

        }
        return ans;
    }
}
