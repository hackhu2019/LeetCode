# LeetCode697. 数组的度

#### [697. 数组的度](https://leetcode-cn.com/problems/degree-of-an-array/)

解题思路：

1. 首次遍历找到数组的度，同时记录每个数字出现的数字、首索引、结束索引

2. 遍历哈希表，更新与 nums 相同度的数字长度最小值

```java
public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int maxDegree = 0;
        for (int index = 0; index < nums.length; index++) {
            int[] degree = map.getOrDefault(nums[index], new int[]{0, index, index});
            degree[0]++;
            degree[2] = index;
            maxDegree = Math.max(maxDegree, degree[0]);
            map.put(nums[index], degree);
        }
        int result = nums.length;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] degree = entry.getValue();
            if (degree[0] == maxDegree) {
                result = Math.min(result, degree[2] - degree[1] + 1);
            }
        }
        return result;
    }
```

