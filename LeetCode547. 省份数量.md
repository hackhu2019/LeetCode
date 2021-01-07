# LeetCode547. 省份数量

#### [547. 省份数量](https://leetcode-cn.com/problems/number-of-provinces/)

解题思路：深度优先遍历

1、visited 记录每个城市是否被访问

2、依序遍历 n 个城市，若从未被访问，则遍历和它联通的城市，将其可访问性设为 true

```java
public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, n, i);
                count++;
            }
        }
        return count;
    }

    /**
     * 深度优先遍历，设置当前关联城市的可访问性
     * @param isConnected
     * @param visited
     * @param n
     * @param curCity
     */
    private void dfs(int[][] isConnected, boolean[] visited, int n, int curCity) {
        for (int i = 0; i < n; i++) {
            if (isConnected[curCity][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(isConnected, visited, n, i);
            }
        }
    }
```

