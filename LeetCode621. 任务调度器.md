# LeetCode621. 任务调度器

#### [621. 任务调度器](https://leetcode-cn.com/problems/task-scheduler/)

参考：[官方题解](https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode-solution-ur9w/)

思路分析：

1、任务的执行时间由出现次数最多的任务决定 = （出现次数最多的任务次数-1）* 等待时间+最多任务个数 -- (maxTask-1) *(n+1) + count(maxTask)* ，可以画图分析

2、特殊情况：非重复任务个数>冷却时间，此时冷却时间可省略，取任务总数

```java
public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> countTask = new HashMap<>(16);
        int maxCount = 0;
        for (char task : tasks) {
            int count = countTask.getOrDefault(task, 0) + 1;
            maxCount = Math.max(count, maxCount);
            countTask.put(task, count);
        }
        int countMaxTask = 0;
        for (Map.Entry<Character, Integer> entry : countTask.entrySet()) {
            if (entry.getValue() == maxCount) {
                countMaxTask++;
            }
        }
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + countMaxTask);
    }
```

