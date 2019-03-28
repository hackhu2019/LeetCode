# 最接近原点的 K 个点

> 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
>（这里，平面上两点之间的距离是欧几里德距离。）
>你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。 

>示例 1：
>输入：points = [[1,3],[-2,2]], K = 1
>输出：[[-2,2]]

>解释： 
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。

>示例 2：
>输入：points = [[3,3],[5,-1],[-2,4]], K = 2
>输出：[[3,3],[-2,4]]
>（答案 [[-2,4],[3,3]] 也会被接受。）

***解题思路***：用一个动态数组存储 poions 每个坐标的原点距离，再调用 <stdlib.h> 库的 qsort() 对数组进行排序，找到距离按升序排序的第 K 个点距离，把所有小于这个距离的点放入要返回的数组中即可。
***可优化点***：记录排序前每个距离对应的坐标，可以在比较距离时少做一次重复的距离计算，减少内存消耗。
```c
int count_sqrt(int x, int y)
{
	return x*x + y*y;
}
int compare(const void  *a, const void *b)
{
	return (*(int*)a - *(int*)b);
}
int** kClosest(int** points, int pointsRowSize, int *pointsColSizes, int K, int** columnSizes, int* returnSize) {
	*returnSize = 0;
	int **arrys = (int **)malloc(K*sizeof(int *));// 要返回的二维数组
	int *points_sqrt = (int*)malloc(pointsRowSize*sizeof(int));
	int*  cols = (int*)malloc(K*sizeof(int));
	for (int i = 0; i < pointsRowSize; i++)
		points_sqrt[i] = count_sqrt(points[i][0], points[i][1]);
	qsort(points_sqrt, pointsRowSize, sizeof(int), compare);
	for (int i = 0; i < pointsRowSize; i++)
	{
		if (count_sqrt(points[i][0], points[i][1]) <= points_sqrt[K - 1])
		{
			cols[*returnSize] = 2;
			arrys[*returnSize] = (int*)malloc(2 * sizeof(int));
			arrys[*returnSize][0] = points[i][0];
			arrys[*returnSize][1] = points[i][1];
			++(*returnSize);
		}
	}
	*columnSizes = cols;
	free(points_sqrt);
	*returnSize = K;
	return arrys;
}
```

