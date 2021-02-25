# LeetCode867. 转置矩阵

#### [867. 转置矩阵](https://leetcode-cn.com/problems/transpose-matrix/)

解题思路：按列遍历依次按行加入数据

```java
public int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
```

