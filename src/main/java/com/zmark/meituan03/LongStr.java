package com.zmark.meituan03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhengguangchen
 */


public class LongStr {

//    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//    示例 1:
//
//    输入: "abcabcbb"
//    pwwwewkew
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

    //这是我第一次写的答案，我没有考虑到的场景是pww这种的，我只是把start 来进行排查，尾部指针就没有用过
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
            ArrayList<Character> strList = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                strList.add(s.charAt(i));
            }
            Integer start = 0;
            Integer end = 0;
            for (int i = 1; i < strList.size(); i++) {
                if (strList.get(i).equals(strList.get(start))) {
                    String substring = s.substring(start, i);
                    if (resultMap.get(substring) != null && resultMap.get(substring).longValue() < substring.length()) {
                    } else {
                        resultMap.put(substring, substring.length());
                        start = i;
                    }
                }
                end++;
            }
            if (resultMap.size()==0){
                return 0;
            }
            Set<String> collect = resultMap.keySet().stream().sorted((x1, x2) -> x1.length() - x2.length()).collect(Collectors.toSet());
            if (collect.size()==0){
                return 0;
            }
            return collect.iterator().next().length();
        }



        public int lengthOfLongestSubstring2(String s) {
            //字符出现的位置
            Integer ans=0;
            HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
            ArrayList<Character> charactersList = new ArrayList<>(s.length());
            for (int i = 0; i < s.length(); i++) {
                charactersList.add(s.charAt(i));
            }
            Integer start=0;
            for (int end = 0; end < charactersList.size(); end++) {
                if (characterIntegerHashMap.containsKey( charactersList.get(end))){
                    start=Math.max(characterIntegerHashMap.get(charactersList.get(end)),start);
                }
                ans=Math.max(ans,end-start+1);
                characterIntegerHashMap.put(charactersList.get(end),end+1);
            }
            return ans;
        }
    }
}