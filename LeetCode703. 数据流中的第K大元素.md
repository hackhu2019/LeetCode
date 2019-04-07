# 数据流中的第K大元素 [题目链接](https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/)

> 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。

>你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。

>示例:
>int k = 3;
>int[] arr = [4,5,8,2];
>KthLargest kthLargest = new KthLargest(3, arr);
>kthLargest.add(3);   // returns 4
>kthLargest.add(5);   // returns 5
>kthLargest.add(10);  // returns 5
>kthLargest.add(9);   // returns 8
>kthLargest.add(4);   // returns 8

解题思路：
1、求第 K 大元素都可以通过维护容量为 K 的小顶堆来实现
2、先建立大小固定的小顶堆，每次往堆中添加数据前先判断堆是否已满，未满则放至堆底，再进行「自底向上」的**堆化**。
3、若堆空间已满，则判断新元素是否大于堆顶，否则无需对堆进行操作。
4、若新元素大于堆顶，则先移除堆顶元素，对堆进行「自顶向下」的 **堆化**。再将新元素插入执行步骤 2.
    
```java
class KthLargest {
    private int[] heap;
    private int len;
    private int count;
    public KthLargest(int k, int[] nums) {
        heap = new int[k+1];
        count=0;
        len = k;
        for(int i=0;i<nums.length;i++)
            insert(nums[i]); // 插入新元素，建堆
    }
    private void insert(int num)
    { // 插入新元素
        count++;
        if(count>len)
        {
            count=len;
            if(num<=heap[1])
                return ;
            else
            {
                removeMin();
                count++;
            }
        }
        heap[count]=num;
        int i = count;
        while(i/2>0&&heap[i]<heap[i/2]) // 自底向上堆化
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
    public int add(int val) {
        insert(val);
        return heap[1];
    }
    
}
```

