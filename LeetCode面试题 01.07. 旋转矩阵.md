# 面试题 01.07. 旋转矩阵 [题目链接](https://leetcode-cn.com/problems/rotate-matrix-lcci/)
解题思路：顺时针旋转 90° 对应转换规则 (x,y)->(y,n-x) ，n=N-1

```java
public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return;
        }
        int n = matrix.length - 1;
        int[][] tempMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                tempMatrix[i][j] = matrix[n-j][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = tempMatrix[i];
        }
    }
```
解法2：不借助辅助空间，先按对角线（右上角-左下角）对称交换值，在上下对称交换值

```java
public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return;
        }
        int n = matrix.length - 1;
        // 对角线交换：（x,y）->(n-y,n-x)
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n - row; col++) {
                swap(matrix, row, col, n - col, n - row);
            }
        }
        // 上下交换：(x,y)->(n-x,n-y)
        for (int row = 0; row < matrix.length / 2; row++) {
            for (int col = 0; col < matrix.length; col++) {
                swap(matrix, row, col, n - row, col);
            }
        }
    }
```

