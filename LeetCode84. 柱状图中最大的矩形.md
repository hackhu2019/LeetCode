#  柱状图中最大的矩形

## 解法一，暴力法
思路分析：当前柱子所能形成的最大面积=到结束点柱子（left~right）最低高度 * 索引差（宽度）
max 记录所能达到的最大面积
```java
public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        int max = heights[0];
        for (int left = 0; left < heights.length; left++) {
            int minHeight = heights[left];
            for (int right = left; right < heights.length; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                max = Math.max(max, minHeight * (right - left + 1));
            }
        }
        return max;
    }
```

## 解法二，栈
思路分析：

基于解法一，当前柱子所能形成的最大面积=到结束点柱子（left~right）最低高度 * 索引差（宽度）

所以，当遍历下一柱子时，我们有两种可能：

1、柱子高度递减（或保持不变），我们需要重新记录最低柱子高度

2、柱子高度递增，记录最新柱子的高度。

stack 存储递增柱子索引，先压栈 -1 方便计算宽度（索引差），遍历结束后，计算所有递增柱所能围成的最大面积，max 记录所能围成的最大面积
```java
public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 占位
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) { // 柱子高度递减（或保持不变）
                max = Math.max(max, heights[stack.pop()] * (i-stack.peek()-1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek()-1));
        }
        return max;
    }
```

思路参考：[官方题解](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode/)

