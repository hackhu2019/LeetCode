# LeetCode330. 按要求补齐数组

#### [330. 按要求补齐数组](https://leetcode-cn.com/problems/patching-array/)

思路参考：[ 官方题解 ](https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode-solu-klp1/)

思路分析：贪心算法

定理：[1,x-1] 能表示的值范围为 [1,2x-1]

1、x表示 nums 当前能表示数最大值

2、依序遍历 nums，当 ```index < len && nums[index] <= x``` 说明 x 能正常满足，更新 x，判断nums 下一遍历数是否满足

3、不满足条件，则需要在 nums 添加一个 x，x 值更新

```java
public int minPatches(int[] nums, int n) {
        int patch = 0;
        long x = 1;
        int index = 0, len = nums.length;
        while (x <= n) {
            if (index < len && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                patch++;
                x *= 2;
            }
        }
        return patch;
    }
```