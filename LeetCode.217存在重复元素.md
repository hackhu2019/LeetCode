# LeetCode.217存在重复元素

#### [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

解法一：遍历数组哈希表存储元素，当遍历元素在 哈希表 中已存在返回 true，遍历结束无重复返回 false

```java
public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>(nums.length); // 提前初始化容器大小，减少扩容消耗
        for (int num : nums) {
            if (numSet.contains(num)) {
                return true;
            } else {
                numSet.add(num);
            }
        }
        return false;
    }
```

解法二：对数组进行排序，再遍历有序数组，发现前后重复返回 true，遍历结束无重复返回 false

```java
public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
```

解法三，在解法二的思想上进一步优化，对数组 nums 采用归并排序，在合并数组时，判断数组是否有重复，参考 [题解 : C-分治](https://leetcode-cn.com/problems/contains-duplicate/solution/c-fen-zhi-by-dong-feng-32-1245/)

```java
public boolean containsDuplicate2(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }

    private boolean mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return false;
        }
        int mid = (right + left) / 2;
        if (mergeSort(nums, left, mid) || mergeSort(nums, mid + 1, right)) {
            return true;
        }
        int[] temp = new int[right - left + 1];
        // 合并数组
        for (int i = 0, j = left, k = mid + 1; j <= mid || k <= right; i++) {
            // 左边数组遍历结束
            if (j > mid) {
                temp[i] = nums[k++];
            } else if (k > right) { // 右边数组遍历结束
                temp[i] = nums[j++];
            } else if (nums[j] < nums[k]) { // 选取最小值
                temp[i] = nums[j++];
            } else {
                temp[i] = nums[k++];
            }
            if (i > 0 && temp[i] == temp[i - 1]) { // 判断是否存在重复数字
                return true;    
            }
        }
        // 归并数组
        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }
        return false;
    }
```





