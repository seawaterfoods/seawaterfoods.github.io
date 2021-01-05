---
title: 11 Container With Most Water
date: 2021-01-05 11:15:29 
category: LeetCode 
tags: [LeetCode,Java]
index_img: /img/LeetCode/11.jpg
---
![](/seawaterfoods/img/LeetCode/11.jpg)
給定n個非負整數a1, a2, ..., an，其中每個代表坐標(i, ai)上的一個點。 繪製n條垂直線，使線i的兩個端點位於 (i, ai) 和 (i, 0)。 <br/>
找兩條線，它們與x軸一起形成一個容器，以便該容器包含最多的水。
<!-- more -->

###  

```
public int maxArea(int[] height) {
    int ans=0;
    
    //取最左邊的線與最右邊的線。
    for(int left =0,right = height.length-1;left<right;){
        int area = 0;
        int width = right-left;
        
        //左右邊線相互比較後，以較小邊為高再與寬相乘得面積，之後將較小邊往對邊前進一位。
        if(height[left]>height[right]){
            area = width*height[right];
            right--;
        }else{
            area = width*height[left];
            left++;
        }
        if(ans<area)
            ans=area;
       }
    return ans;
}
```
![](/seawaterfoods/img/LeetCode/11_ContainerWithMostWater.png)
