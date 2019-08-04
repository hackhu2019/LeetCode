# 组合总和

> 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target
> 的组合。 
> candidates 中的数字可以无限制重复被选取。

> 说明：
> 所有数字（包括 target）都是正整数。 解集不能包含重复的组合。 

>  示例 1:
> 输入: candidates = [2,3,6,7], target = 7, 
> 所求解集为: [   [7],   [2,2,3] ]
> 来源：力扣（LeetCode） 
> 链接：https://leetcode-cn.com/problems/combination-sum

思路分析 —— 回溯法：
1. 遍历数组 candidates，依序遍历 canidates 数字，将已遍历数字加入新集合 list
2. 若 list 集合数字相加之和与 target 值相等，将当前组合加入 lists，继续寻找下一可能组合
3. 剪枝条件：遍历完数组，list 集合元素超过目标值
```java
class Solution {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        backtracking(candidates,0,target,list,0);
        return lists;
    }

    private void backtracking(int[] candidates, int nowIndex, int target, List<Integer> list,int sum) {
        if (sum == target) {
            lists.add(new ArrayList<>(list)); // List 存储的是对象的引用，这里需要存储一个新的对象
            return ;
        }
        if (sum > target||candidates.length==nowIndex) {
            return ;
        }
        for (int i = nowIndex; i < candidates.length; i++) {
            if (sum+candidates[i]<=target){
                list.add(candidates[i]);
                backtracking(candidates, i , target, list, sum + candidates[i]); // // 数组对象可重复使用，所以传递索引为当前索引
                list.remove(list.size() - 1);
            }
        }
    }
}
```

