# LeetCode85. 最大矩形

#### [85. 最大矩形](https://leetcode-cn.com/problems/maximal-rectangle/)

解题思路：暴力解题

1、首次遍历，记录当前座标所能围成的最大宽度（横向连续为 1 的数量）

2、第二次遍历，获取当前坐标所能围成的最大高度（纵向连续为 1 的数量），当前所能围成最大矩形面积为 最小宽度*高度

3、maxRectangle 记录最大矩形面积

```java
public int maximalRectangle(char[][] matrix) {
        if (matrix.length < 1) {
            return 0;
        }
        int maxRectangle = 0, rowLen = matrix.length, colLen = matrix[0].length;
        int[][] maxWidth = new int[rowLen][colLen];
        // 初始化最大宽度
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] == '1') {
                    maxWidth[i][j] = j == 0 ? 1 : maxWidth[i][j - 1] + 1;
                }
            }
        }
        // 计算最大矩形面积
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == '0') {
                    continue;
                }
                int area = maxWidth[row][col];
                int minWight = maxWidth[row][col];
                for (int i = row - 1; i >= 0; i--) {
                    minWight = Math.min(minWight, maxWidth[i][col]);
                    area = Math.max(area, minWight * (row - i + 1));
                }
                maxRectangle = Math.max(maxRectangle, area);
            }
        }
        return maxRectangle;
    }
```

