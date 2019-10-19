# 合并区间
>给出一个区间的集合，请合并所有重叠的区间。

>示例 1:
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals

思路分析：
1、将 intervals 数组放入 List 中根据 左区间大小对数组进行排序

2、当后一数组的左区间位于前一数组的右区间时进行合并，右区间为二者最大值

3、依次遍历 list 数组，每合并一个数组从 list 中删除被合并项，继续匹配下一数组，看是否能够合并
```java
public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return new int[0][];
        }
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            lists.add(intervals[i]);
        }
        Collections.sort(lists,(l1,l2)->l1[0]-l2[0]);
        int i = 1;
        while (i < lists.size()) {
            int start1=lists.get(i-1)[0],end1=lists.get(i-1)[1]
                    ,start2=lists.get(i)[0],end2=lists.get(i)[1];
            if (start2 >= start1 && start2 <= end1) {
                lists.get(i - 1)[1] = end1 > end2 ? end1 : end2;
                lists.remove(i);
            } else {
                i++;
            }
        }
        return lists.toArray(new int[0][]);
    }
```

