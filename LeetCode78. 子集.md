# 78. 子集 [题目链接](https://leetcode-cn.com/problems/subsets/)
解法一：回溯法

依次创造 1...nums.length 长度的子数组

createSubSet，创造子数组

subSets 存储已生成子数组

```java
private List<List<Integer>> subSets = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        subSets.add(new ArrayList<>());
        for (int i = 1; i <= nums.length; i++) {
            createSubSet(nums, 0, i, new ArrayList<>());
        }
        return subSets;
    }

    private void createSubSet(int[] nums, int startIndex, int k, List<Integer> subset) {
        if (subset.size() == k) {
            subSets.add(new ArrayList<>(subset));
            return;
        }
        for (int i = startIndex ; i < nums.length; i++) {
            subset.add(nums[i]);
            createSubSet(nums, i+1, k, subset);
            subset.remove(subset.size() - 1);
        }
    }
```
解法优化：用递归代替循环，用 Interge[] 替代 ArrayList，减少 remove（）的时间复杂度。思路参考：https://leetcode.com/problems/subsets/discuss/543960/JAVA-0-ms-Recursive-solution

```java
private List<List<Integer>> subSets = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        subsetRec(nums, new Integer[nums.length], 0, 0);
        return subSets;
    }

    private void subsetRec(int[] nums, Integer[] arr, int index, int cur) {
        if (index < nums.length) {
            if (index == nums.length - 1) {
                subSets.add(Arrays.asList(Arrays.copyOfRange(arr, 0, cur)));
            }
            subsetRec(nums, arr, index + 1, cur);
            arr[cur] = nums[index];
            if (index == nums.length - 1) {
                subSets.add(Arrays.asList(Arrays.copyOfRange(arr, 0, cur+1)));
            }
            subsetRec(nums, arr, index + 1, cur + 1);
        }
    }
```
