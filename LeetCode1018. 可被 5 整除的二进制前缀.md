# LeetCode1018. 可被 5 整除的二进制前缀

#### [1018. 可被 5 整除的二进制前缀](https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/)

解题思路：

1、cur 记录上一位计算数值余数，每次遍历新一位数，更新 curr=（上一位余数*2+当前位数值）%5
2、resultList 记录结果集，若 curr%5==0，加入 true，否 加入 false

```java
public List<Boolean> prefixesDivBy5(int[] A) {
        int curr = 0;
        List<Boolean> resultList = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            curr = ((curr << 1) + A[i])%5;
            resultList.add(curr == 0);
        }
        return resultList;
    }
```

