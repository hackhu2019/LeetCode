# 设计循环队列 [题目链接](https://leetcode-cn.com/problems/design-circular-queue/)

> 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。

>循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。

>你的实现应该支持如下操作：

>MyCircularQueue(k): 构造器，设置队列长度为 k 。
>Front: 从队首获取元素。如果队列为空，返回 -1 。
>Rear: 获取队尾元素。如果队列为空，返回 -1 。
>enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
>deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
i>sEmpty(): 检查循环队列是否为空。
>isFull(): 检查循环队列是否已满。

解题思路：

这里采用的是顺序队列（数组）的方式来实现循环队列。

用三个变量：head 、tail 、count 分别代表队列的首元素位置、尾元素的下一位置（所以 Rear 方法与 Front 稍有不同）、队列的元素个数

对头和尾变量超出数组长度时做取模运算。
```java
class MyCircularQueue {
    private int[] nums; // 定义一个顺序队列，用数组来存储队列元素
    private int count = 0; // count 来表示队列中当前存在的元素个数
    private int head = 0; // head 表示队列的头
    private int tail =0; // tail 表示队列的尾
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        nums =  new int[k]; // 根据 k 来初始化数组的长度
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(count>=nums.length) return false; // 队列元素个数超出数组长度则无法再入队
        nums[tail++] = value; // 队列未满则将元素入队，尾指针以及数组元素 +1
        count++;
        tail = tail % nums.length;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(count==0) return false; // 队列为空则无法删除元素
        head++;
        head = head % nums.length; // 减少数组搬运操作直接将队列头移向下一元素
        count--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(count==0) return -1;
        return nums[head]; 
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(count==0) return -1;
        if(tail==0) return nums[nums.length-1];
        return nums[tail-1];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return count==0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return count == nums.length;
    }
}
```

