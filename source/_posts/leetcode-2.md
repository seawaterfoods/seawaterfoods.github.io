---
title: 2 Add Two Numbers
date: 2021-01-04 15:14:04
category: LeetCode
tags: [LeetCode,Java]
index_img: /img/LeetCode/2.jpg
---
![](/seawaterfoods/img/LeetCode/2.jpg)
此題目為兩個non-empty linked lists，它們代表兩個非負整數。<br/>
這些數字以相反的順序儲存在兩條linked list中的nodes。<br/>
將兩個數字相加後每一位數以相反的順序儲存在新的linked lists中返回。
<!-- more -->
<p>
EX1:如圖<br/>
{2,4,3}->342 & {5,6,4}->465<br/>
342+465 = 807<br/>
807 -> {7,0,8}
</p>
<p>
EX2:<br/>
{9,9,9,9,9,9,9}->9999999 & {9,9,9,9}->9999<br/>
9999999+9999 = 10009998<br/>
10009998 -> {8,9,9,9,0,0,0,1}
</p>

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode output = new ListNode(0);
        output.val = 0;

        //因為ListNode是Linked list所以必須利用java的參考(將物件的記憶體位址設定給參考名稱)refoutput將參考output
        ListNode refoutput = output;

        while (l1 != null || l2 != null) {
            //將l1&l2非null的值加到refoutput值中
            if (l1 != null)
                refoutput.val += l1.val;
            if (l2 != null)
                refoutput.val += l2.val;

            //如果有進位，則建立值為1的下一個node
            if (refoutput.val >= 10) {
                refoutput.val -= 10;
                ListNode nextrefoutput = new ListNode(1);
                refoutput.next = nextrefoutput;
            } else {
                //如果沒有進位，則建立值為0的下一個node
                ListNode nextrefoutput = new ListNode(0);
                refoutput.next = nextrefoutput;
            }
            //查看l1&l2是否為null，若非null則將l1.next或l2.next指派到當前node
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
            //如果l1&l2為null且refoutput下一個node的值為0，則將refoutput下一個node設為null
            if (l1 == null && l2 == null && refoutput.next.val == 0)
                refoutput.next = null;
            //將refoutput指派為refoutput下一個node
            refoutput = refoutput.next;
        }
        //因為refoutput參考output所以直接將output回傳即可。
        return output;
    }
}
```
![](/seawaterfoods/img/LeetCode/2_AddTwoNumbers.png)