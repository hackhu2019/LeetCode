# LeetCode1013
[题目链接](https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/)：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum

解法一：
1. 对数组求和/3=三等分值 flag
2.  count 记录满足要求的等分数，依序遍历数组 A
3. 当 flag！=A【i】 时继续遍历数组 flag-=A【i】
4. 当 flag==A【i】 时，count++，重置 flag

```java
public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0, flag = 0, count = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        flag = sum / 3;
        for (int i = 0; i < A.length; i++) {
            if (flag == A[i]) {
                flag = sum / 3;
                count++;
            } else {
                flag -= A[i];
            }
        }
        return count == 3;
    }
```
解法二：双指针法寻找三等分的两个分界点

leftPoint 为数组第一段和等于三等分值的索引

rightPoint 为数组第san段和等于三等分值的索引

当左右区间有效且区间元素之和也等于三等分值时，返会true，否则返回 false

```java
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0, flag = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        flag = sum / 3;
        int leftPoint = 0, rightPoint = 0;
        // 寻找左分界点
        for (int i = 0; i < A.length; i++) {
            if (flag == A[i]) {
                flag = sum / 3;
                leftPoint = i;
                break;
            } else {
                flag -= A[i];
            }
        }
        // 寻找右分界点
        for (int i = A.length-1; i >= 0; i--) {
            if (flag == A[i]) {
                flag = sum / 3;
                rightPoint = i;
                break;
            } else {
                flag -= A[i];
            }
        }
        int rangSum=0;
        for (int i = leftPoint+1; i < rightPoint; i++) {
            rangSum += A[i];
        }
        return rightPoint - leftPoint > 1 && rangSum == flag;
    }
```


