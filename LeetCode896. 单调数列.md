# LeetCode896. 单调数列

#### [896. 单调数列](https://leetcode-cn.com/problems/monotonic-array/)

解题思路：

1、单调序列规则，一定满足 (A[A.length-1]-A[0])*(A[i]-A[i-1])>=0

2、特殊情况 A[A.length-1]-A[0]=0，则 A[i]-A[i-1] 必须也为 0

3、计算 sub=A[A.length-1]-A[0]，从第二个元素开始遍历 A，依次判断是否满足单调规则

```java
public boolean isMonotonic(int[] A) {
        int sub = A[A.length - 1] - A[0];
        for (int i = 1; i < A.length; i++) {
            if ((sub == 0 && A[i] != A[i - 1]) || (sub * (A[i] - A[i - 1]) < 0)) {
                return false;
            }
        }
        return true;
    }
```

