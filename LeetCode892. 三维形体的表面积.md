# 892. 三维形体的表面积 [题目链接](https://leetcode-cn.com/problems/surface-area-of-3d-shapes/)
解题思路：

总表面积 = 柱体表面积 - 重叠面积

每一个坐标点上的立方体可看作一个柱体（长方体），柱体上下两面面积一定存在，中间四面表面积受重叠影响，减去的面积为相邻坐标点更低柱体侧面积。

思路参考：https://leetcode-cn.com/problems/surface-area-of-3d-shapes/solution/shi-li-you-tu-you-zhen-xiang-jiang-jie-yi-kan-jiu-/

```java
public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] > 0) {
                    sum += grid[x][y] * 4 + 2;
                    sum -= x > 0 ? Math.min(grid[x - 1][y], grid[x][y]) * 2 : 0;
                    sum -= y > 0 ? Math.min(grid[x][y - 1], grid[x][y]) * 2 : 0;
                }
            }
        }
        return sum;
    }
```

