# 最大间距 [题目链接](https://leetcode-cn.com/problems/maximum-gap/)
>给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

>如果数组元素个数小于 2，则返回 0。
>示例 1:
>输入: [3,6,9,1]
>输出: 3
>解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。

思路：
 - 问题的关键在于如何正确的分桶
 - 根据 nums 的最大值和最小值确定桶要存储的数据大小 size
 - 以每个桶能容纳的大小确定实际需要的桶的个数 len
 - 分别建立两个桶存储当前索引的最大值以及最小值
 - 从第二个桶开始，最小值桶与前一位最大值桶的最大差值为返回值

```java
class Solution {
    public int maximumGap(int[] nums) {
        
        if(nums.length<2)
            return 0;
        int max=nums[0];
        int min=nums[0];
        for(int i=1;i<nums.length;i++) // 找出 nums 数组最大最小值
        {
            if(nums[i]>max)
                max=nums[i];
            if(nums[i]<min)
                min=nums[i];
        }
        if(min==max) return 0; // 如果最大最小值相对说明数组元素值一致则最大差值为 0
        int size = (max-min)/nums.length+1; // 每个桶能容纳的最大尺寸 +1 防止除数为 0
        int len = (max-min)/size+1; // 计算桶数 +1 防止溢出
        int [] maxNums=new int[len];
        Arrays.fill(maxNums, Integer.MIN_VALUE); // 最大值全部初始化为整数类型最小值
        int [] minNums=new int[len];
        Arrays.fill(minNums,Integer.MAX_VALUE); // // 最小值全部初始化为整数类型最大值
        for(int i=0;i<nums.length;i++)
        {
            int index=(nums[i]-min)/size; // 分桶找到 nums[i] 需要落入的通的索引值
            if(nums[i]>maxNums[index])
                maxNums[index]=nums[i];
            if(nums[i]<minNums[index])
                minNums[index]=nums[i];
            
        }
        int differ=0; // 差值，前面已排除 differ 可能为 0 的情况
        int flag=0,i=0; // flag 标记上一个有效桶索引
        for(;i<len;i++)
        {
            if(minNums[i]!=Integer.MAX_VALUE)
                {
                    flag=i; // 存储第一个有效桶索引
                    break;
                }
        }
        for(;i<len;i++)
        {
            if(minNums[i]!=Integer.MAX_VALUE) // 每出现一个有效桶则比较差值
            {
                if(minNums[i]-maxNums[flag]>differ)
                differ=minNums[i]-maxNums[flag];
                flag=i;
            }
        }
        return differ;
    }
}
```

