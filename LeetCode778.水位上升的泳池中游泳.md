# LeetCode778.水位上升的泳池中游泳

#### [778. 水位上升的泳池中游泳](https://leetcode-cn.com/problems/swim-in-rising-water/)

解题思路：与 [1631. 最小体力消耗路径](https://leetcode-cn.com/problems/path-with-minimum-effort/) 思路一致 

「Dijkstra 算法」

1、将问题看作，寻找从起点 [0,0] 到终点[n-1,n-1] 的最短边，各各点之间的边的长度为两个之间的最大高度

2、\[]\[]move 存储上下左右移动、\[]\[]visited记录坐标是否被访问过，避免重复计算，\[]\[]times 存储到达每个点所花费的最短时间、priority 按照从小到达顺序存储下一可遍历点以及最小耗时

3、从起点开始依次遍历可探索坐标，选择最小边依次遍历，遍历至终点，结束探索

```java
public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] times = new int[n][n];
        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[n][n];
    	// 初始化耗时
        for (int[] time : times) {
            Arrays.fill(time, Integer.MAX_VALUE);
        }
    	// 初始化起点信息
        times[0][0] = grid[0][0];
        PriorityQueue<int[]> priority = new PriorityQueue<>(Comparator.comparingInt(p -> p[2]));
        priority.offer(new int[]{0, 0, 0});
    	// 开始依次探索上下左右路径
        while (!priority.isEmpty()) {
            int[] curr = priority.poll();
            // 不重复计算
            if (visited[curr[0]][curr[1]]) {
                continue;
            }
            // 到达终点，结束探索
            if (curr[0] == n - 1 && curr[1] == n - 1) {
                break;
            }
            int time = times[curr[0]][curr[1]];
            visited[curr[0]][curr[1]] = true;
            // 更新下一可探索坐标集，times 仅存储最小耗时
            for (int i = 0; i < move.length; i++) {
                int nextX = move[i][0] + curr[0];
                int nextY = move[i][1] + curr[1];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n &&
                        Math.max(time, grid[nextX][nextY]) < times[nextX][nextY]) {
                    times[nextX][nextY] = Math.max(time, grid[nextX][nextY]);
                    priority.offer(new int[]{nextX, nextY, times[nextX][nextY]});
                }
            }
        }
        return times[n - 1][n - 1];
    }
```

