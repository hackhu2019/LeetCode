# 颜色分类
>给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
注意:
不能使用代码库中的排序函数来解决这道题。

>示例:
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-colors

思路分析：依序遍历 nums，遇到 0 ，从起始点开始放，遇到 2 从末尾开始放。

```java
public void sortColors(int[] nums) {
        int start = 0, end = nums.length - 1, index = 0,
            RED = 0, BLUE = 2;
        while (index <= end) {
            if (nums[index] == RED) {
                swap(nums, start++, index++); // start 交换的值只可能为 0 和 1，所以不用进行二次比较
            } else if (nums[index] == BLUE) {
                swap(nums, end--, index); // 可能为 0，还需要再次比较
            } else {
                index++;
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
```

