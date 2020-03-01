# 增减字符串匹配 [题目链接](https://leetcode-cn.com/problems/di-string-match/)
> 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
>返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
>如果 S[i] == "I"，那么 A[i] < A[i+1]
>如果 S[i] == "D"，那么 A[i] > A[i+1]

>示例 1：
>输出："IDID"
>输出：[0,4,1,3,2]

>示例 2：
>输出："III"
输出：[0,1,2,3]

>示例 3：
>输出："DDI"
>输出：[3,2,0,1]

原始代码，注意 returnSize 是没有初始值的，要根据字符串长度进行复制（代码中使用的是 strlen() 方法）
```c
/**
* Return an array of size *returnSize.
* Note: The returned array must be malloced, assume caller calls free().
*/
int* diStringMatch(char* S, int* returnSize) {
	*returnSize = strlen(S)+1;
	int *nums = (int *)malloc((*returnSize)*sizeof(int)), i = 0, count = 0,n=*returnSize-1;
	for (; i <*returnSize-1; i++)
	{
		if (*S == 'I')
		{
			nums[i] = count++;
			nums[i + 1] = nums[i] + 1;
		}
		else
		{
			nums[i] = n--;
			nums[i + 1] = nums[i] - 1;
		}
		S++;
	}
	return nums;
}
```
根据社区的其他解答进行了代码简化

***解题思路***：因为返回的数组长度为字符串长度 +1，通过规律可以发现，结尾多出的数恒为 n（当前减小数）
每出现一个 D 则 n--，若未出现 D 则最后排列的增大数恒为 returnSize-1。
```c
int* diStringMatch(char* S, int* returnSize) {
	*returnSize = strlen(S)+1;
	int *nums = (int *)malloc((*returnSize)*sizeof(int)), i = 0, count = 0,n=*returnSize-1;
	for (; i <*returnSize; i++)
		nums[i]=S[i]=='I'?count++:n--;
	return nums;
}
```

