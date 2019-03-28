# 缺失的第一个正数 [题目链接](https://leetcode-cn.com/problems/first-missing-positive/)

> 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

>示例 1:
>输入: [1,2,0]
>输出: 3

>示例 2:
>输入: [3,4,-1,1]
>输出: 2

>示例 3:
>输入: [7,8,9,11,12]
>输出: 1

参考了社区的解答。

***解题思路*** ：

1、由题可得数组索引 index 对应的值为 index + 1，整个数组的取值范围为 [ 1 , length] (length 为数组长度即元素个数)，且无重复。（可看做简单的 hashtable）

2、代码中的循环，在当前满足 nums[i] = i + 1 时 i++, 超出取值范围时将数值与末尾数交换，若是满足取值则进行对应索引值交换，直到满足 nums[i] = i + 1 

最后输出的 length 为按升序排列的最大符合要求值，length + 1 则为缺失正数
```c
void swap(int* nums, int a, int b)
{
	int temp = nums[a];
	nums[a] = nums[b];
	nums[b] = temp;
}
int firstMissingPositive(int* nums, int numsSize) {
	int length = numsSize, i = 0;
	while (i<length)
	{
		if (nums[i] == i + 1) ++i;
		else if (nums[i]<=0 || nums[i]>length || nums[i] == nums[nums[i]-1])
			swap(nums, i, --length);//将不符合的数放在末尾
		else
			swap(nums,i,nums[i]-1);
	}
	return length + 1;
}
```

