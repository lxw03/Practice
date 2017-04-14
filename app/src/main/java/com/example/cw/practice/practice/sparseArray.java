package com.example.cw.practice.practice;

import android.util.SparseArray;

/**
 * Created by cw on 2017/4/13.
 */

// hashMap默认使用16位的数组来存储数据，数组中每一个元素又是一个链表的头节点，内部结构是哈希表的拉链结构
//      每一个节点都是Entry类型，存储key，value，hash，next下一个Entry
//      hash(key)%/len 来存储 比如16存在0，32存在0，16指向32，从而解决hash冲突
//    hashMap以2倍空间扩容，而且在此过程中也需要不断做hash运算，而且获取数据是通过遍历Entry[]来获得的，耗内存

//    sparseArray key只能是int，避免int自动装箱，
//    内部2各数组 一个存储key 一个value
//    存储读取的时候采用二分法
public class sparseArray {

    SparseArray<String> mSparseArray = new SparseArray<String>();


    static int binarySearch(int[] arr, int size, int value){
        int ol=0;
        int hi=size-1;
        while (ol<=hi){
            final int mid = (ol+hi) >>>1;
            if (value>arr[mid]){
                ol = mid+1;
            }else if (value<arr[mid]){
                ol = mid-1;
            }else {
                return mid;
            }
        }
        return ~ol;
    }

    static int[] shuffle(int[] arr){
        int length = arr.length -1;
        for (int i = length;i>=0;i++){
            int randomIndex = (int) Math.floor(Math.random()*length);
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
        return arr;
    }
}
