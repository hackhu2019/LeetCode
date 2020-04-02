# 289. 生命游戏 [题目链接](https://leetcode-cn.com/problems/game-of-life/)
解题思路：

1、创建 res[][] 存储下一状态，countAlive(int x,int n,board) 计算当前坐标点八个相邻位置的活细胞数。

2、遍历 board，当遍历元素值为 0，且活细胞数为 0；或遍历元素值为 1 ，活细胞数小于 2、大于 3，活细胞变死细胞，死细胞变活细胞；否，则与 board 状态一致。

```java
public void gameOfLife(int[][] board) {
        boolean[][] needChange = new boolean[board.length][board[0].length];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                int count = countAlive(x, y, board);
                if ((board[x][y] == 1 && (count < 2 || count > 3))||(board[x][y] == 0 && count == 3)) {
                    needChange[x][y] = true;
                } 
            }
        }
        for (int i = 0; i < needChange.length; i++) {
            for (int j = 0; j < needChange[i].length; j++) {
                if (needChange[i][j]) {
                    board[i][j] = board[i][j] == 1 ? 0 : 1;
                }
            }
        }
    }

    private int countAlive(int x, int y, int[][] board) {
        int count = 0;
        int[] moveX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] moveY = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < moveX.length; i++) {
            int tempX = x - moveX[i], tempY = y - moveY[i];
            if (tempX >= 0 &&
                    tempX < board.length &&
                    tempY >= 0 &&
                    tempY < board[x].length &&
                    board[tempX][tempY] == 1) {
                count++;
            }
        }
        return count;
    }
```
解法优化，直接使用 board 存储改变，当活细胞变成死细胞，当前位标志为 -1；死细胞变成活细胞，当前位标志为 2,最后根据标志转换为 0，1.

```java
public void gameOfLife(int[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                int count = countAlive(x, y, board);
                // 活细胞变成死细胞
                if (board[x][y] == 1 && (count < 2 || count > 3)) {
                    board[x][y] = -1;  
                } else if ((board[x][y] == 0 && count == 3)) {
                    // 死细胞变为活细胞
                    board[x][y] = 2;
                }
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                switch (board[row][col]) {
                    case 2:
                        board[row][col] = 1;
                        break;
                    case -1:
                        board[row][col] = 0;
                    default:
                        break;
                }
            }
        }
    }

    private int countAlive(int x, int y, int[][] board) {
        int count = 0;
        int[] moveX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] moveY = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < moveX.length; i++) {
            int tempX = x - moveX[i], tempY = y - moveY[i];
            if (tempX >= 0 &&
                    tempX < board.length &&
                    tempY >= 0 &&
                    tempY < board[x].length &&
                    (board[tempX][tempY] == 1||board[tempX][tempY]==-1)) {
                count++;
            }
        }
        return count;
    }
```

