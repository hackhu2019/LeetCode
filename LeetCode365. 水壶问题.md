# 365. 水壶问题 [题目链接](https://leetcode-cn.com/problems/water-and-jug-problem/)
解法一：BFS

把问题化简为：寻找一条从 0 -> z 的路径，每次可以有 6 种操作。

1、 将 X 倒满

2、将 Y 倒满

3、将 X 水倒出

4、将 Y 水倒出

5、将 X 水倒入 Y 中

6、将 Y 水导入 X 中

通过化简我们可以将操作精简为：+x，+y，-x，-y。（3、5合并为 -x，4、6合并为 -y）

使用变量 cur 记录当前状态，visuted 散列表存储已探索有效路径，防止「迷路」（死循环），queue 记录之前路径，走不到目标时退回。

思路参考：https://leetcode.com/problems/water-and-jug-problem/discuss/172968/Java-BFS

```java
public boolean canMeasureWater(int x, int y, int z) {
        if (z < 0 || z > x + y) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] options = {x, y, -x, -y};
        while (!queue.isEmpty()) { // 队列为空说明无法找到满足要求的路径
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                int cur = queue.poll();
                if (cur == z) {
                    return true;
                }
                for (int option : options) {
                    int next = cur + option;
                    if (next < 0 || next > x + y) { // 当前操作结果无效，无需存储状态
                        continue;
                    }
                    if (!visited.contains(next)) {  // 对未探究路径进行尝试
                        if (next == z) {
                            return true;
                        }
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
```
解法二：基于数学的「[贝祖定律](https://baike.baidu.com/item/%E8%A3%B4%E8%9C%80%E5%AE%9A%E7%90%86/5186593?fromtitle=%E8%B4%9D%E7%A5%96%E5%AE%9A%E7%90%86&fromid=5185441)」

```java
public boolean canMeasureWater(int x, int y, int z) {
        if (x == 0 && y == 0) {
            return z == 0;
        }
        return z == 0 || (z % gcd(x, y) == 0 && x + y >= z);
    }

    /**
     * 求最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
```


