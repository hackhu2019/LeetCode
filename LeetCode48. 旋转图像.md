# 旋转图像

> 给定一个 n × n 的二维矩阵表示一个图像。
> 将图像顺时针旋转 90 度。
> 说明：
> 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

> 示例 1:
> 给定 matrix =  [  
>  [1,2,3],   
> 	[4,5,6],  
> 	[7,8,9] ],
> 原地旋转输入矩阵，使其变为: [  
>  [7,4,1],  
> [8,5,2],  
> 	[9,6,3] ]
> 
> 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rotate-image

参考 [大佬的思路](https://leetcode-cn.com/problems/rotate-image/solution/zi-wai-xiang-nei-shun-shi-zhen-xun-huan-jiao-huan-/)  可以将 90度 翻转分为两个部分：对角线对称翻转、水平线对称翻转。
![流程](https://img-blog.csdnimg.cn/20190818095525732.png)
注意对角线翻转坐标的规律：（ row, col ）——> ( n-col-1, n-row-1 )

水平翻转坐标规律：( row,col ) ——> (n-row-1,col)
```java
 public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int len = matrix.length;
        // 对角线对称
        for (int row = 0; row < len-1; row++) { // 对称线不需要翻转
            for (int col = len-row-1; col >=0 ; col--) {
                swap(matrix,row,col,len-col-1,len-row-1);
            }
        }
        int mid = len >> 1;
        // 上下翻转
        for (int row = 0; row < mid; row++) {
            for (int col = 0; col <len ; col++) {
                swap(matrix, row, col, len - row - 1, col);
            }
        }
    }
    private void swap(int[][] matrix,int row1,int col1,int row2,int col2){
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }
```

方法二：把选择看成围绕矩阵中心点四块区域的值交换

1、matrix\[row]\[col] = matrix\[len - col - 1]\[row];
2、matrix\[len - col - 1]\[row] = matrix\[len - row - 1]\[len - col - 1];
3、matrix\[len - row - 1]\[len - col - 1] = matrix\[col]\[len - row - 1];
4、matrix\[col]\[len - row - 1] = matrix\[row]\[col]

```java
public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int row = 0; row < len / 2; row++) {
            for (int col = 0; col < (len + 1) / 2; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[len - col - 1][row];
                matrix[len - col - 1][row] = matrix[len - row - 1][len - col - 1];
                matrix[len - row - 1][len - col - 1] = matrix[col][len - row - 1];
                matrix[col][len - row - 1] = temp;
            }
        }
    }
```

