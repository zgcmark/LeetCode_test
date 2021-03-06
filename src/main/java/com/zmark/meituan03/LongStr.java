package com.zmark.meituan03;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengguangchen
 *
 *
 * 解决数组 字符串 链表  这类型的问题，主要的方法就是  双指针是比较好的解决方案
 * 1.快慢指针 一个快一个慢
 * 2.双向指针，一个左 一个右
 * 3.滑动窗口
 *
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

        //这个是上题学习完后用自己理解的滑动窗口处理
        public int lengthOfLongestSubstring3(String s) {

            HashMap<Character,Integer> dictMap=new HashMap();
            HashMap<Character,Integer> window=new HashMap();
            ArrayList<Character> strList = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                strList.add(s.charAt(i));
            }
            Integer left = 0;
            Integer right = 0;
            //初始化窗口

            for (int i = 0; i < strList.size(); i++) {
                if (window.containsKey(strList.get(i))){
                    break;
                }else {
                    window.put(strList.get(i),i);
                    right++;
                }
            }
            int ans=window.size();
            //开始滑动
            while (right<strList.size()){
                right++;
                if (window.containsKey(strList.get(right))){
                    Integer integer = window.get(strList.get(right));
                    left=integer;
                }
                window.put(strList.get(right),right);
                ans=Math.max(window.size(),ans);
            }

            return ans;

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

        //    输入: "abcabcbb"
//    pwwwewkew
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        public int lengthOfLongestSubstring7(String s) {
            //<字符，出现的下标>
            HashMap<Character, Integer> map = new HashMap<>();
            Integer ans=0;
            Integer left=0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (map.containsKey(c)){
                    left=Math.max(left,map.get(c)+1);
                }
            }
        }



        //    给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。(minimum-window-substring)
//
//    输入: S = "ADOBECODEBANC", T = "ABC"
//        DOBECODEBA NC
//    输出: "BANC"
        public String lengthOfLongestSubstring3(String s, String t) {
            TreeMap<Integer,String> fianlMap=new TreeMap();
            String tstr=t;
            ArrayList<Character> tempList = new ArrayList<>();
            HashSet<Character> tempSet = new HashSet<>();
            HashSet<Character> tempSet2 = new HashSet<>();
            for (int i = 0; i < tstr.length(); i++) {
                tempList.add(tstr.charAt(i));
                tempSet.add(tstr.charAt(i));
            }

            ArrayList<Character> charactersList = new ArrayList<>(s.length());
            for (int i = 0; i < s.length(); i++) {
                charactersList.add(s.charAt(i));
            }
            Integer left=0;
            Integer right=0;

            //优先找到第一个窗口
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (tempSet.contains(c)){
                    tempSet.remove(c);
                }
                    right=i;
                if (tempSet.size()==0){
                    break;
                }
            }
            HashMap<Character, Integer> rescountMap = new HashMap<>();
            List<Character> chuangkouList = charactersList.subList(left, right);
            for (Character character : chuangkouList) {
                if (tempSet2.contains(character)) {
                    if (rescountMap.containsKey(character)) {
                        Integer integer = rescountMap.get(character);
                        rescountMap.put(character, integer++);
                    } else {
                        rescountMap.put(character, 1);
                    }
                }
            }
            for (; right < s.length(); right++) {
                while (!tempSet2.contains(charactersList.get(left))){
                    left++;
                }
                Character i = charactersList.get(left);
                while (rescountMap.get(i)>1){
                    left++;
                    rescountMap.put(i,rescountMap.get(i)-1);
                }
                //左区间已经到头了
                fianlMap.put(right-left+1,s.substring(left,right));
            }
        return fianlMap.lastEntry().getValue();
        }
    }





}
