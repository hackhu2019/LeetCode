# 合并两个有序数组 [题目链接](https://leetcode-cn.com/problems/merge-sorted-array/)

> 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

>说明:
>初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

>示例:
>输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]

解题思路：

1、创建一个可以容纳两个数组有效元素的数组，再将两个数组中的数按照从大到小存储到新数组，再重新放入 nums1 。（不推荐占用了多余的内存空间）

2、推荐：将两个数组从后往前排序，每次将最大的数放入 nums1 末尾。（ 题目中有提示 ：nums1 有足够的空间容纳两个数组中的有效元素）
```c
void merge(int* nums1, int m, int* nums2, int n) {
	int i = 0,
		index_nums1=m-1,
		index_nums2=n-1,
		sum=m+n;
		while (i < sum)
		{
			i++;
			if (index_nums1 < 0)
				nums1[sum - i] = nums2[index_nums2--];
			else if (index_nums2 < 0)
				break;
			else if (nums1[index_nums1] > nums2[index_nums2])
				nums1[sum - i] = nums1[index_nums1--];
			else
				nums1[sum - i] = nums2[index_nums2--];
		}
	
}
```

