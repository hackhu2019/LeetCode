# LeetCode1423. 可获得的最大点数

#### [1423. 可获得的最大点数](https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/)

解题思路：正难则反，剩余元素之和最小时，拿走的纸牌之和最大

1. 一次遍历，计算 cardPoints 元素之和
2. 滑动窗口，slideSum 存储窗口元素之和，minSum 存储窗口最小值,窗口大小为 cardPoints.length-k
3. 滑动窗口每次移出 cardPoints[left],加入 cardPoints[right+1]

```java
public int maxScore(int[] cardPoints, int k) {
        int left = 0, right = 0, cardSum = 0, slideLen = cardPoints.length - k;
        for (int cardPoint : cardPoints) {
            cardSum += cardPoint;
        }
    	// 窗口长度为 0，所有纸牌都被拿出
        if (slideLen == 0) {
            return cardSum;
        }
        int slideSum = 0, minSum = Integer.MAX_VALUE;
        while (right < cardPoints.length) {
            slideSum += cardPoints[right++];
            if (right - left < slideLen) {
                continue;
            }
            minSum = Math.min(slideSum, minSum);
            slideSum -= cardPoints[left++];
        }
        return cardSum - minSum;
    }
```

