# 全排列

> 给定一个没有重复数字的序列，返回其所有可能的全排列。

> 示例:
> 输入: [1,2,3] 
> 输出: [   [1,2,3],   [1,3,2],   [2,1,3],   [2,3,1],   [3,1,2],   [3,2,1] ]
> 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/permutations

思路分析：

1. 用相同大小的数组记录当前数字是否使用
2. 每次从数组中选取一个数，若未使用则加入 list
3. 当 list 长度与数组一致则加入 lists
```java
ArrayList<List<Integer>> lists = new ArrayList<>(); // 存储返回结果

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        }
        boolean[] visited = new boolean[nums.length]; // 定义同大小数组，存储是否可访问，默认为 false 可访问
        permute(visited, nums, new ArrayList<>());
        return lists;
    }

    private void permute(boolean[] visited, int[] nums, List<Integer> list) {
        if (list.size() == nums.length) { // 集合大小为
            lists.add(new ArrayList<>(list) ); // 注意 list 是引用类型
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]){ // 可以访问
                visited[i] = true; // 更新访问性
                list.add(nums[i]); // 加入该元素
                permute(visited, nums, list); // 寻找下一组合
                visited[i] = false; // 恢复原状
                list.remove(list.size() - 1); // 移除之前添加的元素
            }
        }

    }
```

参考[官方解答](https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode/)，还有一种可以降低时间复杂度的思路，将数组中不同数的组合看成位置的交换。


```java
List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<Integer> newNums = new ArrayList<>(); // 转化为 List 类型方便 lists 添加操作
        for (int n : nums) {
            newNums.add(n);
        }
        permute(newNums, 0);
        return lists;
    }

    private void permute(List<Integer> nums, int index) {
        if (index==nums.size()) { // 已组合完最后一数
            lists.add(new ArrayList<Integer>(nums));
            return;
        }
        for (int i = index; i < nums.size(); i++) {
            swap(nums, index, i); // 交换
            permute(nums, index + 1); // 组合一位置数
            swap(nums, index, i); // 还原
        }
    }

    // 交换
    private void swap(List<Integer> nums,int index1,int index2){
        Integer temp = nums.get(index1);
        nums.set(index1, nums.get(index2));
        nums.set(index2, temp);
    }
```

