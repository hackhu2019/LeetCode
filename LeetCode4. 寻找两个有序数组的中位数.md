# 寻找两个有序数组的中位数 [题目链接](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

> 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
>请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
>你可以假设 nums1 和 nums2 不会同时为空。

>示例 1:
>nums1 = [1, 3]
>nums2 = [2]
>则中位数是 2.0

解题思路：

先根据两数的长度判断返回的中位数如何计算，奇数个则返回两个有序数组中的第 (length/2)+1 个数

偶数个则返回 (length/2 +length/2+1)/2

因为两数组都是有序则每次遍历两数组最小值，计数+1 ，直到遍历中位数

对于特殊情况进行处理，两个数组中若有一个已经为空则只需要遍历另一数组即可。
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result=0;
        int flag = (nums1.length+nums2.length)%2; // 奇数个 flag 为 1，偶数个 flag 为 0
        int mid = (nums1.length+nums2.length)/2+1; // 中位数长
        int nums1Index=0;
        int nums2Index=0;
        int count=1;
        while(count<=mid)
        {
            if(nums1Index>=nums1.length)
                {
                    if(flag==1&&count==mid) // 根据个数的奇偶返回特定位置的值
                        return nums2[nums2Index];
                    else if(flag==0&&(count==mid-1||count==mid))
                        result+=nums2[nums2Index];
                    nums2Index++;
                }
            else if(nums2Index>=nums2.length)
                {
                    if(flag==1&&count==mid)
                        return nums1[nums1Index];
                    else if(flag==0&&(count==mid-1||count==mid))
                        result+=nums1[nums1Index];
                    nums1Index++;
                } 
            else if(nums2[nums2Index]<=nums1[nums1Index]){ // 依序遍历两数组中较小值
                if(flag==1&&count==mid)
                        return nums2[nums2Index];
                    else if(flag==0&&(count==mid-1||count==mid))
                        result+=nums2[nums2Index];
                    nums2Index++;
            }
            else{
                if(flag==1&&count==mid)
                        return nums1[nums1Index];
                    else if(flag==0&&(count==mid-1||count==mid))
                        result+=nums1[nums1Index];
                    nums1Index++;
            }
            count++;
        }
        return result/2;
    }
}
```

