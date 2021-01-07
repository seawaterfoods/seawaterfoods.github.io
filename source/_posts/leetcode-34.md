---
title: 34 Find First and Last Position of Element in Sorted Array
date: 2021-01-07 11:36:05
category: LeetCode
tags: [LeetCode,Java]
index_img: /img/LeetCode/34.jpg
---
![](/seawaterfoods/img/LeetCode/34.jpg)
給定一個以升序排列的整數nums數組，找到給定目標值的開始和結束位置。

如果在數組中未找到目標，則返回[-1，-1]。

後續：如果可以的話，請寫出運行時間複雜度為O（log n）的算法。
<!-- more -->

```
    public int[] searchRange(int[] nums, int target) {
       if (nums.length == 0) {
            return new int[]{-1, -1};
       }
        
       int start, end, mid;
       int[] bound = new int[2]; 
        
       // 開始搜尋最開始的target位置
       start = 0; 
       end = nums.length - 1;
       while (start < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else  {
                start = mid + 1;
            } 
       }
       bound[0]=start;
        
       // 開始搜尋結束的target位置
       start = 0;
       end = nums.length - 1;
       while (start + 1 < end) {
           mid = start + (end - start) / 2;
           if (nums[mid] <= target) {
               start = mid;
           } else {
               end = mid;
           }
       }
       if (nums[end] == target) {
           bound[1] = end;
       } else if (nums[start] == target) {
           bound[1] = start;
       } else {
//             如果都沒有的話回傳[-1,-1]
           bound[0] = bound[1] = -1;
           return bound;
       }
        
       return bound;
    }
```
