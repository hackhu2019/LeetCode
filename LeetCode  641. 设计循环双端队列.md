# 设计循环双端队列 [题目链接](https://leetcode-cn.com/problems/design-circular-deque/)
> 设计实现双端队列。
>你的实现需要支持以下操作：
>MyCircularDeque(k)：构造函数,双端队列的大小为k。
>insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
>insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
>deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
>deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
>getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
>getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
>isEmpty()：检查双端队列是否为空。
>isFull()：检查双端队列是否满了。

***解题思路：***

这道题和 LeetCode 622. 设计循环队列思路基本一致，可以先完成这题再来看 641

依旧用三个变量：head 、tail 、count 分别代表队列的首元素位置、尾元素的下一位置（所以 Rear 方法与 Front 稍有不同）、队列的元素个数

对头和尾变量超出数组长度时做取模运算。
```java
class MyCircularDeque {
    private int[] nums; // 定义一个顺序队列，用数组来存储队列元素
    private int count = 0; // count 来表示队列中当前存在的元素个数
    private int head = 0; // head 表示队列的头
    private int tail =0; // tail 表示队列的尾

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        nums =  new int[k]; // 根据 k 来初始化数组的长度
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(count>=nums.length) return false; // 队列元素个数超出数组长度则无法再入队
        if(head==0)
            head=nums.length-1;
        else
            head-=1;
        nums[head] = value; // 队列未满则将元素入队，尾指针以及数组元素 +1
        count++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(count>=nums.length) return false; // 队列元素个数超出数组长度则无法再入队
        nums[tail++] = value; // 队列未满则将元素入队，尾指针以及数组元素 +1
        count++;
        tail = tail % nums.length;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(count==0) return false; // 队列为空则无法删除元素
        head++;
        head = head % nums.length; // 减少数组搬运操作直接将队列头移向下一元素
        count--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(count==0) return false; // 队列为空则无法删除元素
        if(tail==0)
            tail=nums.length-1; // 减少数组搬运操作直接将队列尾移向上一元素
        else
            tail-=1;
        count--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if(count==0) return -1;
        return nums[head]; 
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if(count==0) return -1;
        if(tail==0) return nums[nums.length-1];
        return nums[tail-1];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count==0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == nums.length;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
```

