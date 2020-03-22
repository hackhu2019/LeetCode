# 945. 使数组唯一的最小增量 [题目链接](https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/)
解法一：

对数组 A 升序排序,count 记录需要操作的次数。

若 A[i]<=A[i-1],count+=A[i-1]+1-A[i]，A[i]=A[i-1]+1。

```java
public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int count = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                count += A[i - 1] + 1 - A[i];
                A[i] = A[i - 1] + 1;
            }
        }
        return count;
    }
```
解法二：

建立哈希表 set，存储数组无重复元素，count 记录操作次数 (使用  boolean[50000] 代替)

依序遍历数组 A，若元素在 set 中不存在则遍历下一元素

若已存在，则 元素值、count++，直到在 set中无重复再遍历下一元素

```java
public int minIncrementForUnique(int[] A) {
        boolean[] used = new boolean[50000];
        int count = 0, index = 0;
        while (index < A.length) {
            if (!used[A[index]]) {
                used[A[index]] = true;
                index++;
            } else {
                A[index]++;
                count++;
            }
        }
        return count;
    }
```

