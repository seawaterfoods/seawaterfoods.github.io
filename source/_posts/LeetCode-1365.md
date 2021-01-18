---
title: 1365 How Many Numbers Are Smaller Than the Current Number
date: 2020-12-24 17:03:52
category: LeetCode
tags: [LeetCode,Java]
index_img: /img/LeetCode/1365.jpg
---
![](/seawaterfoods/img/LeetCode/1365.jpg)
### 題目 1365: How Many Numbers Are Smaller Than the Current Number
給定數組nums，在每個nums[i]找出數組中比其小的數字後記錄下來，並且以數組形式return。
<!-- more -->
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //2 <= nums.length <= 500
        //0 <= nums[i] <= 100
        
        int[] cts = new int[101];
        //因為int[]中每個數字最多為100，所以設置一個int[101] (cts)的數組。
        
                for (int num : nums) {
                    cts[num]++;
                }
        //將nums中每個數依大小，將cts數組的特定位數++
        //EX: if num=10,15,15,30 then cts[10]++, cts[15]++, cts[15]++, cts[30]++,
        
        int sum = 0;
        //設置sum為0
        
        for (int i = 0; i < 101; i++) {
            int temp = cts[i];
        //暫存每個數組中的數，依前例， cts[10]:temp=1, cts[15]:temp=2, cts[30]:temp=1
            cts[i] = sum;
            //將前面有的數相加。
            //EX: cts[10]=0, cts[11]=1... cts[15]=1 cts[16]=1... cts[30]=4..cts[100]=4
            sum += temp;
            //這裡將sum加上temp
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = cts[nums[i]];
             //將數組nums[i]輸入數組cts[nums[i]]的數
             //EX:nums[0]=10->cts[10]=0->將0指派給nums[0]->nums[0]=0
             //EX:nums[1]=15->cts[15]=1->將1指派給nums[1]->nums[1]=1
             //EX:nums[2]=15->cts[15]=1->將1指派給nums[2]->nums[2]=1
             //EX:nums[3]=30->cts[20]=3->將3指派給nums[3]->nums[3]=3
        }
        return nums;
    }
}
//ANS:[0,1,1,3]
```