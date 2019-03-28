# 求众数 [题目链接](https://leetcode-cn.com/problems/majority-element/)

> 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
>你可以假设数组是非空的，并且给定的数组总是存在众数。

>示例 1:
>输入: [3,2,3]
>输出: 3

>示例 2:
>输入: [2,2,1,1,1,2,2]
>输出: 2

***解题思路***：将数组排序，统计每个数字出现的次数，当满足众数条件时返回。时间复杂度 nlogn
```c
int compare(const void  *a, const void *b)
{
	return (*(int*)a - *(int*)b);
}
int majorityElement(int* nums, int numsSize) {
	qsort(nums,numsSize,sizeof(int), compare);
	int num = nums[0],flag=numsSize>>1,count=1;
	for (int i = 1; i < numsSize; i++)
	{
		if (nums[i] != num)
		{
			num = nums[i]; count = 1;
		}
		else
		{
			count++;
		}
		if (count > flag)
			break;
	}
	return num; 
}
```
更优解

提示：数组元素为奇数个，众数数量大于半数，所以相互抵消后最后剩余的一定为众数
```c
int majorityElement(int* nums, int numsSize)
{
	int count = 1,num=nums[0];
	for (int i = 1; i < numsSize; i++)
		if (count == 0 || num == nums[i])
		{
			count++; num = nums[i];
		}
		else
			count--;
	return num;
}
```

