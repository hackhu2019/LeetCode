# 912. 排序数组 [题目链接](https://leetcode-cn.com/problems/sort-an-array/)
解法一：桶排序

```java
public int[] sortArray(int[] nums) {
        int[] bucket = new int[100001];
        for (int num : nums) {
            bucket[num + 50000]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                for (int count = 0; count < bucket[i]; count++) {
                    nums[index++] = i - 50000;
                }
            }
        }
        return nums;
    }
```
解法二：插入排序

```java
public int[] sortArray(int[] nums) {
        insertSort(nums);
        return nums;
    }

    /**
     * 插入排序
     * @param nums
     */
    private void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1, value = nums[i];
            for (; j >= 0; j--) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                } 
            }
            nums[j + 1] = value;
        }
    }
```
解法三：快速排序

```java
public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int point = partition(nums, left, right);
        quickSort(nums, left, point-1);
        quickSort(nums, point + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int point = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, point);
                point++;
            }
        }
        swap(nums, point, right);
        return point;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
```

