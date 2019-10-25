# 前 K 个高频元素 [题目链接](https://leetcode-cn.com/problems/top-k-frequent-elements/)
>给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

>示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]

思路分析：

1、根据 nums 统计每个元素出现的次数，以 Integer 为 key 出现次数为 value

2、再根据次数进行桶排序，对排序后的数倒序遍历加入 result 结果集，当前 k 个数 遍历完成结束遍历，返回 result。
```java
class Solution {
 public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) { // 统计出现次数
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }
        }
        int bucketSize = 0;
        for (Integer key : hashMap.keySet()) { // 计算桶数量
            bucketSize = bucketSize > hashMap.get(key) ? bucketSize : hashMap.get(key); //
        }
        List<Integer>[] lists = new List[bucketSize + 1];
        for (int i = 1; i < lists.length; i++) { // 初始化桶集合
            lists[i] = new ArrayList<>();
        }
        for (Integer key : hashMap.keySet()) {
            lists[hashMap.get(key)].add(key); // 将元素放入对应桶中
        }
        List<Integer> result = new ArrayList<>();
        // 找出前 k 高 的元素
        for (int index = bucketSize; index > 0; index--) {
            if (lists[index].size()!=0) {
                k -= lists[index].size();
                result.addAll(lists[index]);
                if (k == 0) {
                    break;
                }
            } else {
                continue;
            }
        }
        return result;
    }
}
```

