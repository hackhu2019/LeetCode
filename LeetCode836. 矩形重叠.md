# 836. 矩形重叠 [题目链接](https://leetcode-cn.com/problems/rectangle-overlap/)
解法一：

**正难则反**，若 rec1 与 rec2 不相交，则 rec1 四条边都应该在 rec2 外部。

rec1 位于 rec2 左侧，rec1[2]<=rec2[0]，
rec1 位于 rec2 右侧，rec2[2]<=rec1[0]，
rec1 位于 rec2 上侧，rec2[3]<=rec1[1]，
rec1 位于 rec2 下侧，rec1[3]<=rec2[1]。

```java
public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2]<=rec2[0]||rec2[2]<=rec1[0]||rec2[3]<=rec1[1]||rec1[3]<=rec2[1]);
    }
```
解法二：若 rec1、rec2 重叠，则二者重叠部分必为矩形 ==> rec1、rec2 在 x 轴 y 轴上投影必然同时有重叠.

```java
public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
    }
```
题解参考：https://leetcode-cn.com/problems/rectangle-overlap/solution/ju-xing-zhong-die-by-leetcode-solution/


