# 905. 按奇偶排序数组
[题目链接](https://leetcode-cn.com/problems/sort-array-by-parity/)
>给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。

>你可以返回满足此条件的任何数组作为答案。
>示例：
>输入：[3,1,2,4]
>输出：[2,4,3,1]
>输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。

解题思路：这里将判断是否为偶数改为了「&」（与运算），当计算数据量过大时「&」运算比求余运算的效率更高，更节省内存
同时将奇数和偶数分别用两个变量标记，偶数则按题要求从前往后放入数组，奇数则从后往前放入数组。用前缀表达式节省了一个变量
```c
int* sortArrayByParity(int* A, int ASize, int* returnSize) {
	*returnSize = 0;
	int i = 0,k=ASize,*newA=(int *)malloc(ASize*sizeof(int));
	for (; i < ASize; i++)
		if ((A[i] &1) == 0)
			newA[(*returnSize)++] = A[i];
		else
			newA[--k] = A[i];
	(*returnSize) = ASize;
	return newA;
}
```

