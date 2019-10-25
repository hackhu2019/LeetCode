# 无重叠区间
>给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

>注意:
可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

>示例 1:
输入: [ [1,2], [2,3], [3,4], [1,3] ]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/non-overlapping-intervals

思路分析：

1、根据结束区间进行升序排序，每次选取最小的 end 为后续留出更大的存储空间

3、当 end > intervals[i][0] 说明空间重叠，count++

2、不重叠则更新 end 值，遍历结束返回 count
```java
public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 1) {
            return 0;
        }
        Arrays.sort(intervals, ((o1, o2) ->o1[1] - o2[1]));
        int count = 0, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                count++;
                continue;
            }
            end = intervals[i][1];
        }
        return count;
    }在这里插入代码片
```

