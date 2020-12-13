# 999. 车的可用捕获量
解题思路：

1、设白色车的坐标为 (x，y)，白色车的行进路线为(0,x)、(x,7)、(0,y)、(y,7) 

2、count 记录能捕获卒的最大数量，最小为0，最大为 4

3、当行进路程中遭遇 ‘P’（卒）或 ‘B’（象） 时停止前进，且在遭遇卒时 count++
```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int count = 0, x = 0, y = 0;
        // 找到车的位置
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    x=i;
                    y = j;
                    break;
                }
            }
        }
        int[] moveX = {-1, 1, 0, 0};
        int[] moveY = {0, 0, -1, 1};
        for (int i = 0; i < moveX.length; i++) {
            int indexX = x + moveX[i],
                    indexY = y + moveY[i];
            while (indexX >= 0 && indexX < 8 && indexY >= 0 && indexY < 8) {
                if (board[indexX][indexY] == 'B' || board[indexX][indexY] == 'p') {
                    count = board[indexX][indexY] == 'p' ? count + 1 : count;
                    break;
                }
                indexX += moveX[i];
                indexY += moveY[i];
            }
        }
        return count;
    }
}
```

