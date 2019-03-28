# 三数之和[题目链接](https://leetcode-cn.com/problems/3sum/)

> 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

>注意：答案中不可以包含重复的三元组。

>例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
>满足要求的三元组集合为：
>[
>  [-1, 0, 1],
>  [-1, -1, 2]
>]

解题思路：

1、先将一维数组升序排序，第 1 、2、3 三个数也按照升序选取，但出现符合题目要求的组合出现则加入要返回的二维数组中。

2、在新的组合出现时判断是否与前一个组合相同，若是则放弃该组合。

3、初始化申请二维数组大小为 64 若不够则扩充 2 倍大小。

4、优化代码，防止超时。第 1 个数为正数时即可结束循环，将三重循环优化成双重循环。
```c
int compare(const void  *a, const void *b)
{
	return (*(int*)a - *(int*)b);
}
int** threeSum(int* nums, int numsSize, int* returnSize)
{
	qsort(nums, numsSize, sizeof(int), compare);
	int flag = numsSize, size = 64;
	for (int i = 0; i < numsSize; i++)
		if (nums[i] > 0)
		{
			flag = i; break;
		}
	if (flag == 0 || flag > numsSize - 2)
		flag = numsSize - 2;
	int **arrys = (int **)malloc(size * sizeof(int *));
	*returnSize = 0;
	for (int i = 0; i < flag; i++)
	{
		int  start = i + 1, end = numsSize - 1;
		if (i > 0 && nums[i] == nums[i - 1])
			continue;
		while (start < end)
		{
			int sum = nums[i] + nums[start] + nums[end];
			if (sum== 0)
			{
				if (start > i + 1 && nums[start] == nums[start - 1])
				{
					start++; continue;
				}
				arrys[*returnSize] = (int*)malloc(3 * sizeof(int));
				arrys[*returnSize][0] = nums[i]; arrys[*returnSize][1] = nums[start]; arrys[*returnSize][2] = nums[end];
				//printf("%d %d %d\n", arrys[*returnSize][0], arrys[*returnSize][1], arrys[*returnSize][2]);
				(*returnSize)++;
				if (size == *returnSize)
				{
					size <<= 1;
					arrys = (int **)realloc(arrys, sizeof(int *) * size);
				}
				start++; end--;//因为是从小到大排序，start 增大 则第 3 个数需要减小
				continue;
			}
			else if (sum > 0)
				end--;
			else
			{
				start++;// end = numsSize - 1;
			}
		}
	}
	return arrys;
}
```

