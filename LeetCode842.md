# LeetCode842.

## [842. 将数组拆分成斐波那契序列](https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/)
解题思路：回溯剪枝

1、前两个数任意取值

2、第三个数开始，需满足 `F[i] + F[i+1] = F[i+2]` ，不满足则回退上一步

3、若遍历完字符串，且返回 List 长度 >=3 ，说明结果成立

4、首位为 0 时，生成数字只能有一位

```java
public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> reusult = new ArrayList<>();
        if (S == null || S.length() < 3) {
            return reusult;
        }
        helper(S, 0, reusult, 0, 0);
        return reusult;
    }

    private boolean helper(String s, int index, List<Integer> result, int sum, int pre) {
        // 若遍历完字符串，且返回 List 长度 >=3 ，说明结果成立
        if (s.length() == index) {
            return result.size() >= 3;
        }
        long currLong = 0L;
        for (int i = index; i < s.length(); i++) {
            // 最高位为 0 特殊处理
            if (i > index && s.charAt(index) == '0') {
                break;
            }
            // 计算当前组成数字
            currLong = currLong * 10 + s.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            // currLong 不断增长，currLong > sum 时进行剪枝 
            if (result.size() > 1) {
                if (currLong > sum) {
                    break;
                } else if (currLong < sum) {
                    continue;
                }
            }
            int curr = (int) currLong;
            result.add(curr);
            // 第三个数开始，需满足 `F[i] + F[i+1] = F[i+2]` ，不满足则回退上一步
            if (helper(s, i+1, result, pre + curr, curr)) {
                return true;
            } else {
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
```





  