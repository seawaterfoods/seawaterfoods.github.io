package com.joe.leetcodetest;

import com.joe.leetcodetest.entry.ListNode;

public class 2_AddTwoNumbers{
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode output=new ListNode(0);
        output.val=0;

        ListNode refl1=l1;
        ListNode refl2=l2;
        ListNode refoutput=output;

        while(l1!=null||l2!=null){
            if(l1!=null)
                refoutput.val+=l1.val;
            if(l2!=null)
                refoutput.val+=l2.val;
            if(refoutput.val>=10){
                refoutput.val-=10;
                ListNode nextrefoutput=new ListNode(1);
                refoutput.next=nextrefoutput;
            }else{
                ListNode nextrefoutput=new ListNode(0);
                refoutput.next=nextrefoutput;
            }

            if(l1!=null)
                l1=l1.next;
            if(l2!=null)
                l2=l2.next;
            if(l1==null && l2==null && refoutput.next.val==0)
                refoutput.next=null;

            System.out.println(refoutput.val);
            refoutput=refoutput.next;
        }
        return output;
    }
}