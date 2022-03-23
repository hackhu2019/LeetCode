//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯 👍 1868 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> resultList;

    public List<List<Integer>> permute(int[] nums) {
        resultList = new ArrayList<>();
        traverse(new LinkedList<>(), nums);
        return resultList;
    }

    private void traverse(LinkedList<Integer> result, int[] nums) {
        if (result.size() == nums.length) {
            resultList.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (result.contains(nums[i])) {
                continue;
            }
            result.add(nums[i]);
            traverse(result, nums);
            result.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
