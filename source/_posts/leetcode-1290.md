---
title: 1290 Convert Binary Number in a Linked List to Integer
date: 2020-12-24 16:46:03
category: LeetCode
tags: [LeetCode,Java] 
index_img: /img/LeetCode/1290.jpg
---
![](/seawaterfoods/img/LeetCode/1290.jpg)
此題目中每個ListNode中只會有0 or 1，這題要求出由01組成的二制進以右至左轉成十制進。
<!-- more -->
### 以下是用while的作法
```java
class Solution {
public int getDecimalValue(ListNode head) {
    //設置decimal為最終回覆十制進的ANS
    int decimal = 0;
    
    //於reverse將head的ListNode轉製成由後至前的節點EX:將[1,0,0]->[0,0,1]
    head = reverse(head);
    
    //設置index為2的次方數
    int index = 0;
    
    //使用while迴圈來判斷當前head中是否為null
    while (head != null) {
        //將2^index*當前node的val取值加至decimal
        decimal += (Math.pow(2, index) * head.val);
        //讓head推至下一個node
        head = head.next;
        index ++;
    }
    return decimal;
}
//reverse的轉製方式
private ListNode reverse(ListNode head) {
		//前一個Node
        ListNode prev = null;
		//當前Node
        ListNode cur = head;
		//下一個Node		
        ListNode next = null;
        
		//判斷當前Node是否為空
        while (cur != null) {
			//將cur.next置入next中
            next = cur.next;
            
			//將prev置入cur.next
            cur.next = prev;
            
			//將cur置入prev
            prev = cur;
			
			//將next置入cur
            cur = next;
        }
        
        return prev;
    }
}
```
### 以下是用遞迴的作法
```java
class Solution {
    int i =0;
    int ans =0;
    public int getDecimalValue(ListNode head) {
        if(head.next!=null){
            getDecimalValue(head.next);
        }
        ans += head.val*Math.pow(2,i);
        i++;
        return ans;
    }
}
```