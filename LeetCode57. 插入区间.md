# 57. 插入区间 [题目链接](https://leetcode-cn.com/problems/insert-interval/)
解题思路：

1、依据题意可以将区间列表分为 3 个部分：小于 newInterval[0]、与 newInterval 重叠、大于 newInterval[1].

2、对于 newInterval 非重叠区域，只需要保持原样即可，对于 newInterval 重叠区间，进行合并。

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
        // cur 代表当前操作 output 索引，index 代表当前操作 intervals 索引
        int newStart = newInterval[0], newEnd = newInterval[1], cur = 0, index = 0;
        // output 存储新的区间列表，最长不超过 intervals.length + 1
        int[][] output = new int[intervals.length + 1][2];
        // 填补小于 newStart 的区域
        while (index < intervals.length && intervals[index][0] < newStart) {
            output[cur++] = intervals[index++];
        }
        // 填补 newStart 所在区域
        if (cur == 0 || output[cur - 1][1] < newStart) {
            output[cur][0] = newStart;
            output[cur++][1] = newEnd;
        } else {
            output[cur-1][1] = Math.max(output[cur-1][1], newEnd);
        }
        // 填补大于 newEnd 区域
        while (index < intervals.length) {
            if (output[cur - 1][1] < intervals[index][0]) {
                output[cur++] = intervals[index++];
            } else {
                output[cur - 1][1] = Math.max(intervals[index][1], output[cur - 1][1]);
                index++;
            }
        }
```

