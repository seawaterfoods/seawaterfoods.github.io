package com.joe.practice.datastructure;


import java.util.ArrayList;
import java.util.List;

/**
 * 題目:有一陣列arr[10,3,12,7,15]
 * 需求: 刪除以上陣列中數字是奇數的數字
 * 例如:arr[10,3,12,7,15] 刪除後會變成 [10,12]
 */
public class ArrayQuestion1 {

    public static void main(String[] args) {
        int[] Q1 = new int[]{10,3,12,7,15};
        System.out.print("doActionByList : [ ");
        for(int num:doActionByList(Q1))
            System.out.print(num+" ");
        System.out.println("]");
        System.out.print("doActionByArray : [ ");
        for(int num:doActionByArray(Q1))
            System.out.print(num+" ");
        System.out.println("]");
    }

    public static int[] doActionByList(int[] x)
    {
        List<Integer> temp = new ArrayList<>();
        for(int num : x)
        {
            if(num%2 != 1)
            {
                temp.add(num);
            }
        }
        int[] ans = new int[temp.size()];
        for(int i= 0 ; i < temp.size() ; i++)
        {
            ans[i] = temp.get(i);
        }
        return ans;
    }

    public static int[] doActionByArray(int[] x)
    {
        int round = 0;
        int[] temp = new int[x.length];
        for(int i= 0 ; i < x.length ; i++)
        {
            if(x[i]%2 != 1)
            {
                round++;
                temp[round-1] = x[i];
            }
        }
        int[] ans = new int[round];
        for(int i= 0 ; i < round ; i++)
        {
            ans[i] = temp[i];
        }
        return ans;
    }
}
