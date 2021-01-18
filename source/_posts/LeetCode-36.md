---
title: 36 Valid Sudoku
date: 2021-01-08 15:45:19
category: LeetCode
tags: [LeetCode,Java]
index_img: /img/LeetCode/36.jpg
---
![](/seawaterfoods/img/LeetCode/36.jpg)
### 題目 36: Valid Sudoku
確認9 x 9數獨板是否有效，只有有值的網格需要使用以下規則進行驗證：
1. 每行必須包含數字1-9，且不能重複。
2. 每列必須包含數字1-9，且不能重複。
3. 網格的9個3 x 3子框中的每個必須包含數字1-9，且不能重複。
<!-- more -->
```java
class Solution {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
//            檢查Row
            if (!validateRow(board, i)) return false;
//            檢查Col
            if (!validateCol(board, i)) return false;
        }

        for (int i = 0; i < 9; i++) {
//            檢查Box
            if (!validateBox(board, i)) return false;
        }

        return true;
    }

    private boolean validateRow(char[][] board, int r) {
//        準備使用位移計算元紀錄有無重複數字
        int seen = 0;
        for (int i = 0; i < 9; i++) {
            char ch = board[r][i];
//            System.out.println("board[" + r + "][" + i + "]:" + ch);
//            排除字元為'.'
            if (ch == '.')
                continue;
            int indicator = checkChar(ch, seen);
            if (indicator < seen)
                return false;
            seen = indicator;
        }
        return true;
    }

    private boolean validateCol(char[][] board, int c) {

        int seen = 0;
        for (int i = 0; i < 9; i++) {
            char ch = board[i][c];
//            System.out.println("board[" + i + "][" + c + "]:" + ch);
            if (ch == '.')
                continue;
            int indicator = checkChar(ch, seen);
            if (indicator < seen)
                return false;
            seen = indicator;

        }
        return true;
    }

    private boolean validateBox(char[][] board, int a) {

        int seen = 0;
        for (int i = 0; i < 9; i++) {
            char ch = board[i / 3 + (a / 3) * 3][i % 3 + (a % 3) * 3];
//            System.out.println("board[" + (i / 3 + (a / 3) * 3) + "][" + (i % 3 + (a % 3) * 3) + "]:" + ch);
            if (ch == '.') continue;
            int indicator = seen ^ (1 << (ch - '0'));
            if (indicator < seen) return false;
            seen = indicator;

        }
        return true;
    }

    private int checkChar(char ch, int seen) {
//            查看ch的數字
        int charNum = (ch - '0');
//            紀錄二制進的位數　ex: 如果此char為3 那將1往往左移動3位數: 1 -> 1000;若為9則移動9位數:1 -> 1000000000
        int thornsOfTwo = 1 << charNum;
//            以二制進互斥檢查有無重複數字，前兩次為3跟9則seen會為1000001000，然後再新增3計算後會為1000000000(indicator)
        int indicator = seen ^ thornsOfTwo;
//            如果未重複的狀況下seen永遠會小於indicator，若有重複則會因為互斥導致indicator會小於seen;
        return indicator;
    }
}
```
