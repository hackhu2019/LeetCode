# LeetCode.861翻转矩阵后的得分

## [861. 翻转矩阵后的得分](https://leetcode-cn.com/problems/score-after-flipping-matrix/)

思路：

1、将每行最高位置为1

2、从第二列开始每一列取 1 最大情况（如果该列 1 数量小于 A.length+1/2 , 则取反 A.length - oneCount）

3、单行每位值 =  Math.pow(2,len-n-1)*value 

```java
public int matrixScore(int[][] A) {
        int sum = 0;
        // 每行最高位置为1
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] != 1) {
                for (int j = 0; j < A[i].length; j++) {
                    A[i][j] = 1 - A[i][j];
                }
            }
        }
        sum += Math.pow(2, A[0].length - 1) * A.length;
        // 中值
        int middle = (A.length + 1) / 2;
        for (int i = 1; i < A[0].length; i++) {
            int oneCount = 0;
            for (int j = 0; j < A.length; j++) {
                oneCount += (A[j][i] == 1 ? 1 : 0);
            }
            oneCount = oneCount < middle ? A.length - oneCount : oneCount;
            sum += oneCount * Math.pow(2, A[0].length - i-1);
        }
        return sum;
    }
```

优化：

1、使用位运算代替 Math.pow()

2、省略首次翻转的数组值修改，根据最高位（A \[n][0]）判断该行值是否取反

3、优化每列最大值计算规则，取 （当前 1 数据量，取反后 1 数据量）最大值

```java
public int matrixScore(int[][] A) {
        int sum = 0;
    	// 使用位运算代替 Math.pow()
        int bitValue = 1 << (A[0].length - 1);
        sum += bitValue * A.length;
        for (int i = 1; i < A[0].length; i++) {
            bitValue >>= 1;
            int oneCount = 0;
            for (int j = 0; j < A.length; j++) {
                // 根据最高位（A \[n][0]）判断该行值是否取反
                oneCount += A[j][0] == 1 ? A[j][i] : 1 - A[j][i];
            }
            // 取 （当前 1 数据量，取反后 1 数据量）最大值
            oneCount = Math.max(oneCount, A.length - oneCount);
            sum += oneCount * bitValue;
        }
        return sum;
    }
```

