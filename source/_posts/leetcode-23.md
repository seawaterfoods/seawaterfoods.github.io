---
title: 23 Merge k Sorted Lists
date: 2021-01-05 16:16:09
category: LeetCode
tags: [LeetCode,Java]
index_img: /img/LeetCode/23.jpg
---
![](/seawaterfoods/img/LeetCode/23.jpg)
給一個由k個linked-lists組成的數組，每個linked-lists以升序排列。 將所有linked-lists合併到一個排序的linked-lists中後回傳。
<!-- more -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> temp1 = new ArrayList<>();
        //將題目中數組所有ListNode的值塞入ArrayList中
        for(ListNode listNode:lists){
            while(listNode!=null){
                temp1.add(listNode.val);
                listNode=listNode.next;
            }
        }
        ListNode ans = new ListNode(0);
        ListNode temp2 = ans;
        Integer[] list1 = new Integer[temp1.size()];
        list1 = temp1.toArray(list1);
        //排序
        Integer[] list2 = shellSort(list1);
        
        if(temp1.size()!=0){
        //將list的值一一塞入temp2中
            for(int i =0;i<list2.length;i++){
                temp2.val=list2[i];
                if(i+1<list2.length){
                    temp2.next = new ListNode(0);
                    temp2 = temp2.next;
                }
            }
        }else{
            return null;
        }
        return ans;
    }
    
    
    /**
     * 遞減增量排序演算法 (O(n log2 n)):
     * Shell首先將間隔設為n/2，然後跳躍進行插入排序，
     * 再來將間隔設為n/4，跳躍進行排序動作，
     * 再來間隔設定為n/8、n/16，直到間隔為1的最後一次排序終止，
     * 由於上一次排序動作都會將固定間隔的元素排好，
     * 所以間隔越來越小時，某些元素位於正確位置的機率越高，
     * 因此最後幾次排序次數將可以大幅減低。
     *
     * @param needSort
     * @return
     */
    private static Integer[] shellSort(Integer[] needSort) {
        Integer[] ans = needSort;
        int increment = ans.length;
        int i,j;
        do {
//            增量序列(間距值)
            increment = increment / 3 + 1;
            for (i = increment; i < ans.length; i++) {
                if (ans[i] < ans[i - increment]) {
//                    需要將ans[i]插入有序增量子表
                    int temp = ans[i];
                    for (j = i - increment; j >= 0 && temp < ans[j]; j -= increment) {
//                       數組往後移，查找插入位置
                        ans[j + increment] = ans[j];
                    }
                    ans[j + increment] = temp;
                }
            }
        } while (increment > 1);

        return ans;
    }
}
```