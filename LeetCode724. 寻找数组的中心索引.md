# LeetCode724. 寻找数组的中心索引

#### [724. 寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)

注：本题无法使用双指针法，因为无法确定何时该移动左右指针

解法一：暴力法，依次枚举每次遍历坐标是否满足中心索引要求

```java
public int pivotIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (sum(nums, 0, i) == sum(nums, i + 1, nums.length)) {
                return i;
            }
        }
        return -1;
    }

    private long sum(int[] nums, int starIndex, int endIndex) {
        int total = 0;
        for (int i = starIndex; i < endIndex; i++) {
            total += nums[i];
        }
        return total;
    }
```

解法二：前缀和 参考[官方题解](https://leetcode-cn.com/problems/find-pivot-index/solution/xun-zhao-shu-zu-de-zhong-xin-suo-yin-by-gzjle/)

在暴力法的基础上优化，先一次遍历计算整个 nums 的元素和

第二次从左到右遍历，若 sum-nums[i]=prefixSum*2 ,说明当前遍历坐标为中心索引 ，否则加入当前遍历元素值继续遍历下一元素。

```java
public int pivotIndex(int[] nums) {
        int sum = 0, prefixSum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum - nums[i] == 2 * prefixSum) {
                return i;
            }
            prefixSum += nums[i];
        }
        return -1;
    }
```

