# 盛最多水的容器 [题目链接](https://leetcode-cn.com/problems/container-with-most-water/)

> 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i
> 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

> 说明：你不能倾斜容器，且 n 的值至少为 2。

> 示例:
> 输入: [1,8,6,2,5,4,8,3,7] 
> 输出: 49

解法一 思路：

 1. 暴力求解，计算所有可能的组合，时间复杂度：O(n) 
 2. 外层循环遍历每个点坐标 内层循环遍历后序坐标 
 3. max存储每次内层遍历可存储容量的最大值 
 4. 容量=两点高度最小值 * 两点 x 坐标差

```java
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int tempArea=0;
        int len=height.length;
        for(int i=0;i<=len-2;i++){
            for(int j=i;j<=len-1;j++){
                tempArea=(height[i]>height[j]?height[j]:height[i])*(j-i); // 容量=两点高度最小值 * 两点 x 坐标差
                if(tempArea>max){
                    max=tempArea;
                    // System.out.println(i+","+j);
                }
            }
        }
        return max;
    }
}
```

解法二 思路：[参考自中文社区思路](https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode/)

 1. 双指针法，start 从坐标的起始点开始，end 从坐标的末尾点开始，时间复杂度 O(n)，在数据量大时比解法一性能优势明显（提交答案，解法一最后执行时间：488ms，解法二最后执行时间：5ms）。
 2.  容量最大的两点必位于 start~end之间
 3.  计算两点所能组成的容量，每次将两点高度更小的那个点向内侧移动 
 4. max 存储遍历两点所构成的最大容积 
 5. 两点坐标合并则遍历结束

```java
class Solution {
    public int maxArea(int[] height){
        int max=0; // 所能存储的最大容积
        int start=0; // 起始点
        int end=height.length-1; // 末位点
        int tempArea=0; // 存储两点所构成的最大容积
        while(start<end) {
            tempArea=(height[start]>height[end]?height[end]:height[start])*(end-start); // 两点所构成的最大容积
            if(tempArea>max ){
                max=tempArea; // max 存储遍历两点所构成的最大容积
            }
            if(height[start] > height[end]) { // 每次将两点高度更小的那个点向内侧移动
                --end;
            }
            else{
                ++start;
            }
        }
        return max;
    }
}
```

