# LeetCode189. 旋转数组

#### [189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/)

解法一：额外数组

思路：将右移操作看为，截取数据 [k,nums.length-1]  拼接至数据首部

```java 
public void rotate(int[] nums, int k) {
        int len = nums.length;
    	// 获取实际要右移长度
        k = k % len;
        int[] temp = new int[k];
    	// 暂存末尾 k 个数字
        for (int i = 0; i < k; i++) {
            temp[i] = nums[len - k + i];
        }
    	// 右移剩余数字
        for (int i = len - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
    	// 拼接至数组首部
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
```

解法二：翻转数组，[参考官方题解](https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/)

思路：将右移操作看为 3 次翻转。先翻转整个数组，再分别翻转[0,k-1]、[k,nums.length-1]

```java
public void rotate(int[] nums, int k) {
        int len = nums.length;
    	// 获取实际要右移长度
        k = k % len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
```

