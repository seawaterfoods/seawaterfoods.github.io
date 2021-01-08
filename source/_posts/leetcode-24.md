---
title: 24 Swap Nodes in Pairs 
date: 2021-01-06 10:41:03 
category: LeetCode 
tags: [LeetCode,Java]
index_img: /img/LeetCode/24.jpg
---
![](/seawaterfoods/img/LeetCode/24.jpg)
### 題目 24: Swap Nodes in Pairs
給定一個 linked list，每隔兩個相鄰node交換一次並返回其linked list。
<!-- more -->

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode body = head;
        while (body != null && body.next != null) {
            int temp = body.val;
            body.val = body.next.val;
            body.next.val = temp;
            body = body.next.next;
        }
        return head;
    }
}
```
