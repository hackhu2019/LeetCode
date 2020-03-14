# LeetCode334 [题目链接](https://leetcode-cn.com/problems/increasing-triplet-subsequence/)
>给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

思路分析：
1、基于 [LeetCode300. 最长上升子序列解法](https://github.com/hackhu2019/LeetCode/blob/master/LeetCode300.%20%E6%9C%80%E9%95%BF%E4%B8%8A%E5%8D%87%E5%AD%90%E5%BA%8F%E5%88%97.md)，increaseSequence 存储递增序列依序遍历 nums。

2、若遍历数值小于递增序列末尾数值则加入序列，若大于递增序列末尾则替换序列中首个大于当前遍历数的值。

3、当序列长度等于 3 时，返回true，否，返回 false。

```java
	public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        List<Integer> increaseSequence = new ArrayList<>(3);
        increaseSequence.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int lastIndex = increaseSequence.size() - 1;
            if (nums[i] > increaseSequence.get(lastIndex)) {
                increaseSequence.add(nums[i]);
            } else {
                searchAndReplace(increaseSequence, nums[i]);
            }
            if (increaseSequence.size() == 3) {
                break;
            }
        }
        return increaseSequence.size() == 3;
    }
    /**
     * 查找替换 number 应插入位置
     */
    private void searchAndReplace(List<Integer> longSequence, int number) {
        for (int i = 0; i < longSequence.size(); i++) {
            if (longSequence.get(i) >= number) {
                longSequence.set(i, number);
                break;
            }
        }
    }
```
在当前思路上，进一步优化 **大于递增序列末尾则替换序列中首个大于当前遍历数的值** 这一种情况的处理。

因为此时序列最大长度为 2，最小长度为 1，只需和 increaseSequence.get(0) 比较大小即可知道应替换元素位置。

```java
public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        List<Integer> increaseSequence = new ArrayList<>(3);
        increaseSequence.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int lastIndex = increaseSequence.size() - 1;
            if (nums[i] > increaseSequence.get(lastIndex)) {
                increaseSequence.add(nums[i]);
            } else if (increaseSequence.get(0) < nums[i]) {
                increaseSequence.set(lastIndex, nums[i]);
            }else {
                increaseSequence.set(0, nums[i]);
            }
            if (increaseSequence.size() == 3) {
                break;
            }
        }
        return increaseSequence.size() == 3;
    }
```
进一步优化，只需要 first、second 两个变量即可替代 increaseSequence

```java
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            } 
        }
        return false;
    }
```

