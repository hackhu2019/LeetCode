# 翻转图像
[题目链接](https://leetcode-cn.com/problems/flipping-an-image/)
> 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

>水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

>反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

>示例 1:
>输入: [[1,1,0],[1,0,1],[0,0,0]]
>输出: [[1,0,0],[0,1,0],[1,1,1]]
>解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
>     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]

>示例 2:
>输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
>输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
>解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
>然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

***解题思路***：
这里涉及到 C 语言中二级指针的定义，二维数组实际上就是数组的数组，所以对于动态分配内存的二维数组，需要对每个成员单独申请一次内存。
而翻转操作，把 0 置 1，把 1 置 0，可以用异或 1（ ^1 ）来计算。 
```c
int** flipAndInvertImage(int** A, int ARowSize, int *AColSizes, int** columnSizes, int* returnSize) {
	int **arrys =malloc(ARowSize*sizeof(int *));// 要返回的二维数组
	*columnSizes = malloc(ARowSize* sizeof(int));//每一列的数组长度
	for (int i = 0; i < ARowSize; i++)
	{
		int colsize = AColSizes[i];//对应的每一列的长度
		arrys[i] =malloc(colsize*sizeof(int));
		for (int j = 0; j < colsize; j++)
			arrys[i][colsize - 1 - j] = A[i][j] ^ 1;//异或运算
        *(*columnSizes+i)=colsize;//返回的二维数组中每个数组成员的长度
	}
    *returnSize = ARowSize;//二维数组长度
	return arrys;
}
```

