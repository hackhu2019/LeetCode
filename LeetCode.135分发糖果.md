# LeetCode.135分发糖果

#### [135. 分发糖果](https://leetcode-cn.com/problems/candy/)

解题思路：

1、左规则：若 ratings[n]> ratings[n-1] 则 left[n]必须大于 left[n-1]

2、右规则  若 ratings[n]> ratings[n+1] 则 right[n]必须大于 right[n+1]

3、最后发放糖果时必须同时满足左规则、右规则，所以取左右规则最大值

```java
public int candy(int[] ratings) {
        if (ratings.length < 1) {
            return 0;
        }
        int len = ratings.length;
        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 1, result = left[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            result += Math.max(right, left[i]);
        }
        return result;
    }
```

