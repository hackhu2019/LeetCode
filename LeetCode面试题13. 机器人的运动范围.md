# 面试题13. 机器人的运动范围 [题目链接](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)
解题思路：从坐标 ( 0 ,0 ) 开始向右、下遍历，判断是否满足规则（坐标位数之和不大于 k），且未被访问。

满足要求则运动范围 count++，继续遍历；不满足，则返回上一有效坐标，进行有效遍历。

```java
private int[] moveX = {1, 0},
            moveY = {0, 1};
    private int count = 0;
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        recursion(m, n, k, 0, 0, visited);
        return count;
    }

    private void recursion(int m, int n, int k, int curX, int curY, boolean[][] visited) {
        if (curX >= m || curX < 0 ||
                curY >= n || curY < 0 ||
                visited[curX][curY] ||
                sumOfDigits(curX) + sumOfDigits(curY) > k) {
              return;
        }
        count++;
        visited[curX][curY] = true;
        for (int i = 0; i < moveX.length; i++) {
            int tempX = curX + moveX[i], tempY = curY + moveY[i];
            recursion(m, n, k, tempX, tempY, visited);
        }
    }
    
    private int sumOfDigits(int nums) {
        int sum = 0;
        while (nums != 0) {
            sum += nums % 10;
            nums /= 10;
        }
        return sum;
    }
```
解法二：动态规划，递推公式，当前坐标可访问性=上方坐标可访问性 OR 左方坐标可访问性

思路参考：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/
```java
class Solution {
    public int movingCount(int m, int n, int k) {
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    visited[i][j] |= visited[i - 1][j];
                }
                if (j - 1 >= 0) {
                    visited[i][j] |= visited[i][j - 1];
                }
                if (visited[i][j] && sumOfDigits(i) + sumOfDigits(j) <= k) {
                    count++;
                } else {
                    visited[i][j] = false;
                } 
            }
        }
        return count;
    }
    
    private int sumOfDigits(int nums) {
        int sum = 0;
        while (nums != 0) {
            sum += nums % 10;
            nums /= 10;
        }
        return sum;
    }
}
```

