# LeetCode1046. 最后一块石头的重量

#### [1046. 最后一块石头的重量](https://leetcode-cn.com/problems/last-stone-weight/)

解题思路：排序法，基于大顶堆优化排序

1、创建优先队列，初始化队列长度为 stones.length，排队策略为从大到小排队（实际就是大顶堆）

2、依序遍历 stones 元素，完成优先队列初始化

3、循环从 队列中取两个数 x,y ，若仅有一个元素，则返回 y，存在两个元素则进行比较，x,y不相等时，将 y-x 加入队列

4、若队列为空结束循环 ，返回 0

```java
public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(stones.length, (n1, n2) -> n2 - n1);
        for (int stone : stones) {
            priorityQueue.add(stone);
        }
        while (!priorityQueue.isEmpty()) {
            int y = priorityQueue.poll();
            if (priorityQueue.isEmpty()) {
                return y;
            }
            int x = priorityQueue.poll();
            if (x != y) {
                priorityQueue.add(y-x);
            }
        }
        return 0;
    }
```

优化代码，提取出队列仅有1个元素的判断逻辑，仅在队列元素数量大于 1 时进行循环

```java
public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(stones.length, (n1, n2) -> n2 - n1);
        for (int stone : stones) {
            priorityQueue.add(stone);
        }
        while (priorityQueue.size()>1) {
            int y = priorityQueue.poll();
            int x = priorityQueue.poll();
            if (y != x) {
                priorityQueue.add(y - x);
            }
        }
        return priorityQueue.isEmpty() ? 0 : priorityQueue.poll();
    }
```

