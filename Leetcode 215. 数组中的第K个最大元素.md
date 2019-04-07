# 数组中的第K个最大元素 [题目链接](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)
> 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

>示例 1:
>输入: [3,2,1,5,6,4] 和 k = 2
>输出: 5

解法一：接近作弊。

用 Arrays.sort() 方法将数组排序，按序输出第 K 大的值。
也可以自己实现一个快速排序来解答
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
```
解法二：
利用二叉堆 **求 Top K** 的思路解答。维护一个容量为 K 的小顶堆，当数组遍历结束，完成堆的建立与更新，此时的小顶堆堆顶就是第 K 大的元素。
```java
class Solution {
    private int[] heap;
    private int len;
    private int count;
    public int findKthLargest(int[] nums, int k) {
        heap = new int[k+1];
        len = k;
        for(int i=0;i<nums.length;i++)
            insert(nums[i]);
        return heap[1];
    }
    private void insert(int num)
    {
        if(count==len)
        {
            count=len;
            if(num<=heap[1])
                return ;
            else
            {
                removeMin();
                
            }
        }
        count++;
        heap[count]=num;
        int i = count;
        while(i/2>0&&heap[i]<heap[i/2])
        {
            swap(heap,i,i/2);
            i/=2;
        }
    }
    public void removeMin() // 移除小顶堆堆顶
    {
        
        heap[1] = heap[count];
        
        count--;
        heapfy();
    }
    private void heapfy() // 自顶向下堆化
    {
        int minPos;
        int i=1;
        while(true)
        {
            minPos=i;
            if(i*2<=count&&heap[minPos]>heap[2*i])
                minPos=2*i;
            if(2*i+1<=count&&heap[minPos]>heap[2*i+1])
                minPos=2*i+1;
            if(minPos==i)
                break;
            swap(heap, i, minPos);
            i=minPos;
        }
    }
    private void swap(int[] nums,int index1,int index2)
    {
        int temp = nums[index1];
        nums[index1]=nums[index2];
        nums[index2]=temp;
    }
    
}
```

