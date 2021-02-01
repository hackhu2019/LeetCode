# LeetCode888. 公平的糖果棒交换

#### [888. 公平的糖果棒交换](https://leetcode-cn.com/problems/fair-candy-swap/)

解题思路：哈希表法

1. 第一次遍历 A\B 计算二者的糖果差值 diff，同时以 B糖果值建立哈希表
2. diff/2= 求出平均差值
3. 依次枚举 A 糖果，当哈希表中存在  A[i]+diff 时返回该组合

```java
public int[] fairCandySwap(int[] A, int[] B) {
        int diff = 0;
        Set<Integer> set = new HashSet<>(10000);
        for (int i = 0; i < A.length; i++) {
            diff += A[i];
        }
        for (int i = 0; i < B.length; i++) {
            diff -= B[i];
            set.add(B[i]);
        }
        int[] result = new int[2];
        diff /= 2;
        for (int a : A) {
            Integer key = a - diff;
            if (set.contains(key)) {
                result[0] = a;
                result[1] = key;
                break;
            }
        }
        return result;
    }
```

