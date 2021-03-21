# LeetCode73. 矩阵置零

#### [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/)

解法一：空间复杂度 O(m+n)

1、第一次遍历，boolean[] rowZero、boolean[] colZero 分别记录行列需要转换为 0 的坐标索引

2、第一次遍历，当 rowZero[i] 或 colZero[j]= true 时，将当前坐标值置 0

```java
public void setZeroes(int[][] matrix) {
        boolean[] rowZero = new boolean[matrix.length], colZero = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowZero[i] == true || colZero[j] == true) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
```

解法二：参考 [官方题解](https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/)

思路：

1、第一行第一列代替 boolean[] rowZero、boolean[] colZero，判断当前行/列是否需要置零，flag 标记第一列是否要全置 0 

2、逆序遍历 matrix，将满足条件的行/列是置零

```java
public void setZeroes(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        boolean flag = false;
        for (int i = 0; i < rowLen; i++) {
            if (matrix[i][0] == 0) {
                flag = true;
            }
            for (int j = 1; j < colLen; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = 1; j < colLen; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flag) {
                matrix[i][0] = 0;
            }
        }
    }
```

