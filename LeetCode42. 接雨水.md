# 42. 接雨水 [题目链接](https://leetcode-cn.com/problems/trapping-rain-water/)

> 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 
> 示例:
> 输入: [0,1,0,2,1,0,1,3,2,1,2,1] 
> 输出: 6

容积的计算可以看做一个个盆地：
1. 盆地地形，由两棵较高柱子围成。
2. 最内侧是所有柱子中最高的两棵组成的盆地
3. 外侧盆地，柱子高度都是呈上升趋势。


思路：

 1. 双指针法 ，left 指针从左往右，maxLeft 记录左侧最高柱索引 right 指针从右往左，maxRight 记录右侧最高柱索引
 2. 左右指针扫描过程中遇见更高柱则计算区间雨水容积更换最高柱索引
 3. 当左右指针相遇时，计算左右柱之间的雨水容量
 4. 容量计算特殊情况：左右最高柱之间有比二者最小值更高柱子（盆地中的山峰）。
 5. 从中间最高峰，分为两个可正常计算的区间。返回两个区间容积之和。

```java
class Solution {
    public int trap(int[] height){
    	// 过滤特殊情况
        if (height == null || height.length < 2) {
            return 0;
        }
        int volume = 0; // 容积
        int left = 0, // 左指针
            right = height.length - 1, // 右指针
            maxLeft = left, // 左最高柱索引
            maxRight = right; // 右最高柱索引
        left++;
        right--;
        // 找到
        while (left < right) {
            if (height[left] > height[maxLeft]) {
                volume += computeVolume(height, maxLeft, left);
                maxLeft = left;
            }
            if (height[right] > height[maxRight]) {
                volume += computeVolume(height, right, maxRight);
                maxRight = right;
            }
            left++;
            right--;
        }
        volume += computeVolume(height, maxLeft, maxRight);
        return volume;
    }

    /**
     * 计算两柱之间的容积
     * 为左右两柱的最小值与中间各柱的高度差
     */
    private int computeVolume(int[] height,int index1,int index2){
        int min = height[index1] < height[index2] ? height[index1] : height[index2];
        int volume = 0;
        for (int i = index1+1; i < index2; i++) {
            if (height[i]>min){
                int maxIndex = i;
                // 找到最高峰
                for (int j = i+1; j < index2 ; j++) {
                    if (height[j] > height[maxIndex] ) {
                        maxIndex = j;
                    }
                }
                return computeVolume(height, index1, maxIndex) + computeVolume(height, maxIndex, index2);
            }
            volume += min - height[i];
        }
        return volume;
    }
}
```

