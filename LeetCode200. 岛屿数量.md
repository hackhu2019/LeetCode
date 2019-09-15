# 岛屿数量
>给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

>示例 1:
输入:
11110
11010
11000
00000
输出: 1

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-islands

并查集解法

```java
class Union{
        private int[] union;
        int count = 0;
        Union(char[][] grid){
            int len1 = grid.length, len2 = grid[0].length;
            union = new int[len1 * len2];
            // 初始化并查集
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    if (grid[i][j] == '1') {
                        int index = i * len2 + j;
                        union[index] = index; // 初始化指向自己
                        count++;
                    }
                }
            }
        }
        // 找到终点
        int find(int index) {
            if (union[index] == index) {
                return index;
            }
            int end = find(union[index]);// 递归查找
            while (union[index] != end) { // 优化并查集合并
                int temp = index;
                index = union[index];
                union[temp] = end;
            }
            return  end;
        }
        // 合并并查集
        void union(int index1,int index2){
            int end1 = find(index1);
            int end2 = find(index2);
            if (end1 != end2) { // 不是同一终点
                union[end2] = end1; // 合并
                count--; // 岛屿数 -1
            }
        }

    }
    private int[] moveX = {-1, 1, 0, 0},
            moveY = {0, 0, -1, 1};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        Union union = new Union(grid);
        int len1 = grid.length;
        for (int i = 0; i < len1; i++) {
            int len2 = grid[0].length;
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == '1') { // 将上下左右的 1 合并在一起
                    for (int k = 0; k < moveX.length; k++) {
                        int x = i + moveX[k], y = j + moveY[k];
                        if (isValid(grid, len1, len2, x, y)) {
                            union.union(i * len2 + j, x * len2 + y);
                        }
                    }
                }
            }
        }
        return union.count;
    }


    private boolean isValid(char[][] grid,int len1,int len2, int x, int y) {
        return x >= 0 && x < len1 && y >= 0 && y < len2 && grid[x][y] == '1';
    }
```

DFS 解法
```java
class Solution {
    class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private int[] moveX = {-1, 1, 0, 0},
            moveY = {0, 0, -1, 1};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int count = 0, len1 = grid.length;

        for (int i = 0; i < len1; i++) {
            int len2 = grid[i].length;
            for (int j = 0; j < len2; j++) {
                count += dfs(grid, len1, len2, i, j);
            }
        }
        return count;
    }
    private int  dfs(char[][] grid,int len1,int len2,int x,int y) {
        if ((!isValid(len1, len2, x, y))||grid[x][y]!='1') {
            return 0;
        }
        grid[x][y] = '0';
        for (int i = 0; i < moveX.length; i++) {
            dfs(grid,len1, len2, x + moveX[i], y+moveY[i]);
        }
        return 1;
    }

    private boolean isValid(int len1,int len2, int x, int y) {
        return x >= 0 && x < len1 && y >= 0 && y < len2;
    }
}
```

BFS 解法
```java
class Solution {
    class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int count = 0, len1 = grid.length;
        int[] moveX = {-1, 1, 0, 0}, moveY = {0, 0, -1, 1};
        LinkedList<Point> queue = new LinkedList();
        for (int i = 0; i < len1 ; i++) {
            int len2 = grid[i].length;
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    queue.add(new Point(i, j));
                    // 广度优先遍历，上下左右染色
                    while (queue.size() != 0) {
                        Point p = queue.poll();
                        for (int k = 0; k < moveX.length; k++) {
                            int x = p.x + moveX[k], y = p.y + moveY[k];;
                            if (isValid(len1, len2, x, y) && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                queue.add(new Point(x, y));
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean isValid(int len1,int len2, int x, int y) {
        return x >= 0 && x < len1 && y >= 0 && y < len2;
    }
}
```

