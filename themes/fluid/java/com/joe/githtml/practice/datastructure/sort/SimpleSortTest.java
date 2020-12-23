package com.joe.githtml.practice.datastructure.sort;

import java.util.Arrays;

public class SimpleSortTest {
    private static final int MAX_LENGTH_INSERT_SOUT = 7;//or 50 ;視情況調整
    static int i, j;
    static int temp;
    static int round;
    static int[] sAns;

    public static void main(String[] args) {
        long time1, time2;
        int[] needSort = {90, 10, 50, 80, 30, 70, 40, 60, 20};
//        int[] needSort = {50, 10, 90, 30, 70, 40, 80, 60, 20};
        sout(needSort);
        init();
        time1 = System.currentTimeMillis();
//        sout(bubbleSort(needSort));
//        sout(simpleSelectionSort(needSort));
//        sout(straightInsertionSort(needSort));
//        sout(shellSort(needSort));
//        sout(heapSort(needSort));
//        sout(MergeSort(needSort));
        sout(QuickSort(needSort));
        time2 = System.currentTimeMillis();


        System.out.println("執行時間：" + (time2 - time1) / 1000 + "秒");
    }

    /**
     * 泡沫排序 n(n-1)/2 (時間複雜度:O(n^2)):
     * 從數組的最後數值逐一與前一位比較，將較小值放入前方，即可求出排序。
     *
     * @param needSort
     * @return
     */
    private static int[] bubbleSort(int[] needSort) {
        int[] ans = needSort;
        boolean flag = true;
//        flag用來標記,若flag為true則退出循環
        for (i = 1; i < ans.length && flag; i++) {
//            初始為false
            flag = false;
            for (j = ans.length - 1; j >= i; j--) {
                if (ans[j - 1] > ans[j]) {
                    swap(ans, j - 1, j);
//                    如果有數值交換，則flag轉為true
                    flag = true;
                }
            }
        }
        return ans;
    }

    /**
     * 簡單選擇排序 n(n-1)/2 (時間複雜度:O(N^2)):
     * 通過n-i次數值間的比較後，紀錄i個值中最小數值後，與第i(1<=i<=n)個數值做交換。
     *
     * @param needSort
     * @return
     */
    private static int[] simpleSelectionSort(int[] needSort) {
        int[] ans = needSort;
        int min;
        int bout = 0;
        for (i = 1; i < ans.length; i++) {
            bout++;
//            將當前下標定義為最小值下標
            min = i - 1;
//            循環之後的數值
            for (j = i; j < ans.length; j++) {
//                如果有小於當前最小值的數值
                if (ans[min] > ans[j]) {
//                    將此數值的下標賦予min
                    min = j;
                }
            }
//            若min不等於i-1，說明找到最小值，交換其值
            if (ans[i - 1] != ans[min]) {
                swap(ans, i - 1, min);
            }
        }
        System.out.println(bout + "回");
        return ans;
    }

    /**
     * 直接插入排序 (n+4)(n-1)/2 (時間複雜度:O(N^2)):
     * 將一個數值直接插入已經排好續的有序表中，直接得到新的數值加一的數組列表。
     *
     * @param needSort
     * @return
     */
    private static int[] straightInsertionSort(int[] needSort) {
        int[] ans = needSort;
        int j;//已排序列表的下標
        int temp;//待排序數值
        for (i = 1; i < ans.length; i++) {
//            將數值給待排序數值
            temp = ans[i];
            for (j = i - 1; j >= 0 && ans[j] > temp; j--) {
//                由後往前讀取已排序列表，逐一與待排序數值比較，如果已排序數值較大則將其往後移
                ans[j + 1] = ans[j];
            }
//            將待排序數值插入正確位置
            ans[j + 1] = temp;
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
    private static int[] shellSort(int[] needSort) {
        int[] ans = needSort;
        int increment = ans.length;
        do {
//            增量序列(間距值)
            increment = increment / 3 + 1;
            for (i = increment; i < ans.length; i++) {
                if (ans[i] < ans[i - increment]) {
//                    需要將ans[i]插入有序增量子表
                    temp = ans[i];
                    for (j = i - increment; j >= 0 && temp < ans[j]; j -= increment) {
//                       數組往後移，查找插入位置
                        ans[j + increment] = ans[j];
                    }
                    ans[j + increment] = temp;
                    roundPrint(ans);
                }
            }
        } while (increment > 1);

        return ans;
    }

    /**
     * 堆積排序法 (O(n log2 n)):
     * 將待排序的序列整理成heap，這時候整個序列最大值就是heap的root node，
     * 再將root與heap最末端數值交換，之後將剩餘的n-1序列重新整理成新的heap，
     * 如此反覆即可得有序序列。
     *
     * @param needSort
     * @return
     */
    private static int[] heapSort(int[] needSort) {
        int[] ans = needSort;
        for (i = ans.length / 2; i > 0; i--) {
            heapAdjust(ans, i, ans.length);
        }

        sout(ans);
        for (i = ans.length - 1; i > 1; i--) {
//            將heap root與未經排序子序列的最後數值做交換
            swap(ans, 0, i);
            sout(ans);
//            將ans[1.. i-1]重新整理成heap
            heapAdjust(ans, 1, i - 1);

            roundPrint(ans);
        }

        return ans;
    }

    /**
     * 將序列整理成heap。
     *
     * @param ans
     * @param s
     * @param m
     */
    private static void heapAdjust(int[] ans, int s, int m) {
        temp = ans[s - 1];
        System.out.println("s:" + s + " m:" + m);
//        沿child node向下篩選
        for (j = 2 * s - 1; j < m; j = 2 * (j + 1) - 1) {
            System.out.println("j:" + j + " temp:" + temp);
            if ((j + 1 != m) && ans[j] < ans[j + 1]) {
//                j須為index:s的child node中較大的index
                ++j;
                System.out.println("*j*:" + j);
            }
            System.out.println("ans[j]:" + ans[j]);
//            如果ans[s-1]的值大於其child node則不交換
            if (temp >= ans[j]) {
                break;
            }
            swap(ans, j, s - 1);
            s = j + 1;
            System.out.println("s:" + s);
            sout(ans);
        }
        System.out.println("---heap end---");
    }

    /**
     * 合併排序法
     *
     * @param needSort
     * @return
     */
    private static int[] MergeSort(int[] needSort) {
        int[] ans = needSort;
        mSort(needSort);
        ans = sAns;

        return ans;
    }

    /**
     * 非遞回歸併排序法 (時間複雜度:O(n log2 n),空間複雜度:O(n+log n)):
     * 占用記憶體多，但是效率高且穩定。
     *
     * @param needSort
     */
    private static void mSort(int[] needSort) {
        int[] workArray = new int[needSort.length];

        for (int count = 1; count < needSort.length; count *= 2)
            for (int leftStart = 0; leftStart < needSort.length; leftStart += 2 * count) {
                if (count > needSort.length - leftStart)
                    break;
                merge(needSort, workArray, leftStart, count, leftStart + count, Math.min(count, needSort.length - leftStart - count));
            }
        sAns = needSort;
    }

    /**
     * 合併
     *
     * @param array
     * @param workArray
     * @param leftStart
     * @param leftCount
     * @param rightStart
     * @param rightCount
     */
    private static void merge(int[] array, int[] workArray, int leftStart, int leftCount, int rightStart, int rightCount) {
        int i = leftStart, j = rightStart, leftBound = leftStart + leftCount, rightBound = rightStart + rightCount, index = leftStart;
        while (i < leftBound || j < rightBound) {
            if (i < leftBound && j < rightBound) {
                if (array[j] < array[i])
                    workArray[index] = array[j++];
                else
                    workArray[index] = array[i++];
            } else if (i < leftBound)
                workArray[index] = array[i++];
            else
                workArray[index] = array[j++];
            ++index;
        }
        for (i = leftStart; i < index; ++i)
            array[i] = workArray[i];
        System.out.println("array:");
        sout(array);
        System.out.println("workArray:");
        sout(workArray);

    }

    /**
     * 快速排序法(時間複雜度:O(n^2)):
     * 採用分而治之法(Divide-and-conquer)，
     * 把大問題切割成小問題，會於資料中找到一個基準值(Pivot)，
     * 並將資料逐一與這個值相比較，最後可以得到兩個部分，
     * 分別為大於這個值與小於這個值的數值，
     * 大於基準值的數值放在右邊，小於基準值的數值放左邊，
     * 基準值就很像是我們使用的篩子，篩完之後物品就會被區分成兩塊。
     * @param needSort
     * @return
     */
    private static int[] QuickSort(int[] needSort) {
        int[] ans = needSort;
        QSout(ans, 0, ans.length - 1);
        return ans;
    }

    private static void QSout(int[] ans, int low, int high) {
        int pivot;
        if ((high-low)>MAX_LENGTH_INSERT_SOUT) {
            while (low<high){
                pivot = Partition(ans,low,high);

                QSout(ans,low,pivot-1);
                low=pivot+1;
            }

        }else {
            straightInsertionSort(ans);
        }
    }

    /**
     * 分割數組
     * @param ans
     * @param low
     * @param high
     * @return
     */
    private static int Partition(int[] ans, int low, int high) {
        int pivotkey;
        pivotkey = ans[low];
        temp = pivotkey;
        while (low<high){
            while (low<high && ans[high]>=pivotkey)
                high--;
            ans[low] = ans[high];
            while (low<high && ans[low]<=pivotkey)
                low++;
            ans[high] = ans[low];
        }
        ans[low] = temp;
        return low;
    }


    /**
     * 印出數組值
     *
     * @param array
     */
    private static void sout(int[] array) {
        System.out.print("[");
        for (int temp : array)
            System.out.print(" " + temp);
        System.out.println(" ]");
    }

    /**
     * 交換數組值
     *
     * @param array
     * @param a
     * @param b
     * @return
     */
    private static int[] swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        System.out.println("---swap---");
        return array;
    }

    /**
     * 初始化數值
     */
    private static void init() {
        i = 0;
        j = 0;
        temp = 0;
        round = 1;
    }

    /**
     * 印出排序回數與其動作
     *
     * @param ans
     */
    private static void roundPrint(int[] ans) {
        System.out.println("round[" + round++ + "]");
        sout(ans);
        System.out.println();
    }
}
