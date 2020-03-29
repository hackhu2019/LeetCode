# 1162. 地图分析
解题思路：动态规划

1、当前坐标距离为上下左右距离最小值+1

2、两次距离计算，第一次以上左为参照，第二次以下右为参照，如果是陆地标记为 0

3、maxDistance 记录最大距离

```java
public int maxDistance(int[][] grid) {
        int maxDistance = -1;
        int[][] distance = new int[grid.length][grid[0].length];
        // 初始化
        for (int[] ints : distance) {
            Arrays.fill(ints, Integer.MAX_VALUE-1);
        }
        // 第一次距离计算，以上左为参照，是陆地则初始化为 1
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    distance[i][j] = 0;
                    continue;
                }
                if (i - 1 >= 0) {
                    distance[i][j] = Math.min(distance[i][j], distance[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][j - 1] + 1);
                }
            }
        }
        // 第二次距离计算，以右下为参照，同时更新最大 maxDistance
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i + 1 < grid.length) {
                    distance[i][j] = Math.min(distance[i][j], distance[i + 1][j] + 1);
                }
                if (j + 1 < grid[i].length) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][j + 1] + 1);
                }
                maxDistance = Math.max(distance[i][j], maxDistance);
            }
        }
        // 对于全为海洋情况进行特殊处理
        return maxDistance == Integer.MAX_VALUE - 1 ? -1 : maxDistance;
    }
```

