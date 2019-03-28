#  滑动窗口最大值 [题目链接](https://leetcode-cn.com/problems/sliding-window-maximum/)

> 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。

>返回滑动窗口最大值。

>示例:
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

>  滑动窗口的位置                最大值
>[1  3  -1] -3  5  3  6  7       3
>1 [3  -1  -3] 5  3  6  7       3
>1  3 [-1  -3  5] 3  6  7       5
>1  3  -1 [-3  5  3] 6  7       5
>1  3  -1  -3 [5  3  6] 7       6
>1  3  -1  -3  5 [3  6  7]      7

***解法一：***

对数组遍历 nums.length-k+1 次

每次比较从当前索引起 k 个数中的最大值，并将最大值放入返回的新数组中

```java
public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0||k==0) { return new int[0]; }
        int len = nums.length-k+1;
        int[]  maxNums = new int[len];;
        int max;
        for(int i = 0;i < len;i++)
        {
            max = nums[i];
            for(int j = i+1;j < k + i ;j++)
            {
                if(nums[j] > max)
                    max = nums[j];
            }
            maxNums[i] = max;
        }
        return maxNums;
    }
```
***解法二：***

创建动态数组队列，将数组索引入队，从数组中第 k 个数开始将每次队列中的索引对应的值和 nums 当前索引值比较，将最大值放入新数组中。

队列始终维持 K 个数

```java
public int[] maxSlidingWindowTwo(int[] nums, int k) {
        if(nums.length==0||k==0) { return new int[0]; }
        int len = nums.length-k+1;
        int nums_len=nums.length;
        int[]  maxNums = new int[len];;
        int count=0;
        Deque<Integer> deque = new ArrayDeque<>() ;

        for(int i = 0;i < nums_len;i++)
        {
            if(!deque.isEmpty()&&deque.peek()<i-k+1)
                deque.poll(); // 保持队列长度始终为 k-1 以内
            if(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i])
                deque.pollLast(); // 队列中最多有两个数，过滤滑动窗口中更小的数
            deque.offer(i);//入队
            if(i>=k-1) // 从第 k 个数开始放入新数组
                maxNums[count++]=nums[deque.peek()];
        }
        return maxNums;
    }
```

