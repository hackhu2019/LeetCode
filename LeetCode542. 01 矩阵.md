# 542. 01 矩阵 [题目链接](https://leetcode-cn.com/problems/01-matrix/)
解题思路：动态规划

当前坐标最小距离=上下左右最小距离+1 or 当前坐标为0

```java
public int[][] updateMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        int[][] result = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                result[row][col] = matrix[row][col] == 0 ? 0 : 10000;
            }
        }
        // 第一遍计算根据左、上取最小值
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (row > 0) {
                    result[row][col] = Math.min(result[row][col], result[row - 1][col]+1);
                }
                if (col > 0) {
                    result[row][col] = Math.min(result[row][col], result[row][col - 1] + 1);
                }
            }
        }
        // 第二遍计算根据右、下取最小值
        for (int row = rowLen - 1; row >= 0; row--) {
            for (int col = colLen - 1; col >= 0; col--) {
                if (row+1<rowLen) {
                    result[row][col] = Math.min(result[row][col], result[row + 1][col]+1);
                }
                if (col + 1 < colLen) {
                    result[row][col] = Math.min(result[row][col], result[row][col + 1] + 1);
                }
            }
        }
        return result;
    }
```

