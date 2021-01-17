# LeetCode1232.缀点成线

#### [1232. 缀点成线](https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/)

解题思路：已知直线 ( x,y ) 关系可表示为 y=kx+b

特殊情况：直线与 x 轴垂直

1、处理特殊情况，当 coordinates\[0]\[0] == coordinates\[1]\[0]，后续所有点的 x 坐标都必须恒等于 coordinates\[1]\[0]

2、通过 coordinates\[0]，coordinates\[1] 计算出 k 和 b，后续所有坐标都必须满足该表达式才能在同一直线

```java
public boolean checkStraightLine(int[][] coordinates) {
    	// 处理特殊情况
        if (coordinates[0][0] == coordinates[1][0]) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] != coordinates[0][0]) {
                    return false;
                }
            }
            return true;
        }
    	// 计算出 k 和 b
        double k = (coordinates[0][1] - coordinates[1][1]) / (1.0*(coordinates[0][0] - coordinates[1][0]));
        int b = coordinates[0][1] - (int) (k * coordinates[0][0]);
    	// 判断后续坐标点是否满足表达式
        for (int i = 1; i < coordinates.length; i++) {
            if (k * coordinates[i][0] + b != coordinates[i][1]) {
                return false;
            }
        }
        return true;
    }
```

