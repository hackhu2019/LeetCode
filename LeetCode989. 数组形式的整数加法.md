# LeetCode989. 数组形式的整数加法

#### [989. 数组形式的整数加法](https://leetcode-cn.com/problems/add-to-array-form-of-integer/)

解题思路：按序相加

1、carry 记录进位值

2、sum 记录 A 当前遍历值（超出索引则为 0）+ K 的个位值+ carry

3、每次将sum个位置放入结果集中，carry 更新为 sum 十分位值，K/=10 降位

4、当 A 遍历结束，K 和 carry 都为 0 时遍历结束

```java
public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        int carry = 0, index = A.length - 1;
        while (K > 0 || index >= 0 || carry > 0) {
            int sum = (index >=0 ? A[index] : 0) + K % 10 + carry;
            K /= 10;
            result.add(sum % 10);
            carry = sum / 10;
            index--;
        }
        Collections.reverse(result);
        return result;
    }
```

