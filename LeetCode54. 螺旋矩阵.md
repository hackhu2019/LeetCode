# 螺旋矩阵
>给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

>示例 1:
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/spiral-matrix

思路分析：
1. 螺旋遍历的规律：按照 上第一行（从左到右）-> 右竖行（从上到下） -> 下第一行（从右到左）-> 左竖行（从下到上）遍历放入 list
2. 特殊情况处理，只有一行时，只添加该行每项元素即可
3. 只有一列时，
4. 只有两行/两列时，不需要遍历内圈
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        help(matrix, 0, matrix.length - 1, result, 0);
        return result;
    }

    private void help(int[][] matrix, int startRow, int endRow,List<Integer> result,int n) {
        // 第一行（从左到右）
        for (int col = n; col < matrix[startRow].length-n; col++) {
            result.add(matrix[startRow][col]);
        }
        if (endRow - startRow < 1) { // 只有一行
            return;
        }
        // 右竖行（从上到下）
        for (int row = startRow+1; row <= endRow ; row++) {
            result.add(matrix[row][matrix[row].length - n-1]);
        }
        if (matrix[startRow].length - 2*n <= 1) { // 只有一列
            return;
        }
        // 下第一行（从右到左）
        for (int col = matrix[endRow].length - n-2; col >= n; col--) {
            result.add(matrix[endRow][col]);
        }
        // 左竖行（从下到上）
        for (int row = endRow-1; row >startRow ; row--) {
            result.add(matrix[row][n]);
        }
        if (endRow - startRow < 2||matrix[startRow].length - 2*n == 2) {  // 只有两行/两列，不需要再遍历内圈
            return;
        }
        help(matrix, startRow + 1, endRow - 1, result, n + 1); // 遍历内圈
    }
}
```

