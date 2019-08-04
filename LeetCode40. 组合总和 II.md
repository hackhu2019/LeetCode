# 组合总和 II

> 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
> candidates 中的每个数字在每个组合中只能使用一次。

> 说明：
> 所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。  

> 示例 1:
> 输入: candidates = [10,1,2,7,6,1,5], target = 8, 
> 所求解集为: 
> [   [1, 7],  [1, 2, 5],   [2, 6],   [1, 1, 6] ]
> 来源：力扣（LeetCode） 
> 链接：https://leetcode-cn.com/problems/combination-sum-ii

思路分析 —— 回溯法：

1. 排序数组 candidates，相同组合数字顺序一致，方便忽略重复项
2. 遍历数组 candidates，依序遍历 canidates 数字，将已遍历数字加入新集合 list
3. 若 list 集合数字相加之和与 target 值相等，且在 lists 不存在相同解，将当前组合加入 lists，继续寻找下一可能组合
4. 剪枝条件：遍历完数组，list 集合元素超过目标值

```java
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target)  {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates); // 使数组元素有序化
        backtracking(candidates,0,target,list,0);
        return lists;
    }

    private void backtracking(int[] candidates, int nowIndex, int target, List<Integer> list,int sum) {
        if (sum == target&&!lists.contains(list)) { // 忽略重复答案
            lists.add(new ArrayList<>(list)); // List 存储的是对象的引用，这里需要存储一个新的对象
            return ;
        }
        if (sum > target||candidates.length==nowIndex) { // 剪枝
            return ;
        }
        for (int i = nowIndex; i < candidates.length; i++) {
            if (sum+candidates[i]<=target){
                list.add(candidates[i]);
                backtracking(candidates, i+1 , target, list, sum + candidates[i]); // 数组对象不可重复使用，所以传递索引为当前索引+1
                list.remove(list.size() - 1);
            }
        }
    }
}
```

