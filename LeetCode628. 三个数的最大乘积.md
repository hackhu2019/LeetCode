# LeetCode628. 三个数的最大乘积

#### [628. 三个数的最大乘积](https://leetcode-cn.com/problems/maximum-product-of-three-numbers/)

解题思路：本题主要考察代码的健壮性

nums 元素的取值范围是 [-1000,1000]，其三个数的最大乘积，我们要真的正负数的个数进行考虑。

1、无正数，取最大的三个数

2、1-2个正数，取最小两个负数*正数最大值

3、3 个及以上正数 Math(最小两数*最大数，最大三数乘积)

综合情况 max=Math.max(min1 * min2 * max1, max1 * max2 * max3)

```java
public int maximumProduct(int[] nums) {
    	// min1、min2 分别记录最小、第二小值
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
    	// max1、max2、max3 分别记录第一大、第大二、第三大值
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
    	// 遍历 nums 更新 5 个数
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
            if (max1 < num) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 < num) {
                max3 = max2;
                max2 = num;
            } else if (max3 < num) {
                max3 = num;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
```

