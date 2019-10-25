# 分发饼干
>假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

>注意：
你可以假设胃口值为正。
一个小朋友最多只能拥有一块饼干。

>示例 1:
输入: [1,2,3], [1,1]
输出: 1

>来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/assign-cookies

思路分析：

1、对小朋友的胃口值、饼干大小进行排序，每次从饼干中选出能够满足 小朋友胃口的最小值

2、每满足 1 个小朋友 count++，继续匹配下一对

3、indexS 存储上一次饼干坐标，当饼干或者小朋友遍历结束时结束分配

```java
public int findContentChildren(int[] g, int[] s) {
        int count = 0, indexG = 0, indexS = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (indexG < g.length && indexS < s.length) {
            if (s[indexS] >= g[indexG]) { // 最小值匹配成功
                indexG++;
                indexS++;
                count++;
            } else {
                indexS++;
            }
        }
        return count;
    }
```

