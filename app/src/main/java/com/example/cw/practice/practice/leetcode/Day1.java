package com.example.cw.practice.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cw on 2017/4/21.
 */

public class Day1 {

    private void swap(char[] str, int i,int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    //1.倒转一个字符串
    private void reverseStr(String string){
        int i=0;
        char[] str = string.toCharArray();
        int j = str.length-1;
        while (i<j){
            swap(str, i, j);
            i++;
            j--;
        }
    }

//    2.Keep track of the frequency of ASCII characters
    private void printASCIIfre(char[] str){
        int[] frequency = new int[256];
        for (int i=0;i<str.length;i++){
            frequency[str[i]]++;
        }
        for (int j=0;j<256;j++){
            if (frequency[j]>0){
                System.out.println("["+ (char)(j+'a')+ "]"+frequency[j]);
            }
        }
    }

//    3. same as 2 for unicode characters
    private void printUnicodeFre(char[] str){
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0;i<str.length;i++){
            if (map.containsKey(str[i])){
                map.put(str[i], map.get(str[i]) + 1);
            }else {
                map.put(str[i] ,1);
            }
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet()){
            System.out.println((char)entry.getKey() + entry.getValue());
        }
    }

    private void changeStringInJAVA(String str){
        //String is immutable in JAVA
        //StringBuilder StringBuffer mutable
        char[] arr = str.toCharArray();
        arr[0] = 'a';
        System.out.print(arr);
    }

//    4. remove duplicates from sorted Array
//    do not allocate another space for array, must with constant memory
//    [1,2,2] => 2
    private int removeDuplicates(int[] nums){
        if (nums.length == 0) return 0;
        int j=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] != nums[j]) nums[++j] = nums[i];
        }
        return ++j;
    }


//    5.Tow sum , input array is sorted
    private int[] towSum(int[] numbers, int target){
        int[] indice = new int[2];
        int left = 0;
        int right = numbers.length-1;
        while (left<right){
            int v = numbers[left] + numbers[right];
            if (v == target){
                indice[0] = left+1;
                indice[1] = right+1;
            }else if (v<target){
                left++;
            }else if (v>target){
                right--;
            }
        }
        return indice;
    }

//    6.Giving two strings s and t, write a function to determine if t is an anagram of s, only letter
    private boolean isAnagram(String s, String t){
//        int[] sFreqArray = getCharFrequence(s);
//        int[] tFreqArray = getCharFrequence(t);
//        boolean isEqual = true;
//        for (int i=0;i<256;i++){
//            if (sFreqArray[i] != tFreqArray[i]){
//                isEqual = false;
//                break;
//            }
//        }
//        return isEqual;

        int[] alphabet = new int[26];
        for (int i=0;i<s.length();i++){
            alphabet[s.charAt(i)-'a']++;
        }
        for (int i=0;i<t.length();i++){
            alphabet[t.charAt(i)-'a']--;
        }
        for (int i=0;i<26;i++){
            if (alphabet[i] !=0){
                return false;
            }
        }
        return true;
    }

    private int[] getCharFrequence(String s){
        char[] charArray = s.toCharArray();
        int[] intArray = new int[256];
        for (int i=0; i<charArray.length;i++){
            intArray[charArray[i]]++;
        }
        return intArray;
    }

}
