# 460. LFU缓存 [题目链接](https://leetcode-cn.com/problems/lfu-cache/)
解题思路：哈希表+优先队列

Map<Integer, Node> cache 存储缓存 key、value，PriorityQueue<Node> freQueue 按使用频率和最近使用时间降序存储缓存元素，static int timer 计数表示每个元素的使用时间，int capacity 记录缓存的容量

put 元素时，判断是否已存在：存在则更新使用频率和最近使用时间；不存在，则判断是否缓存空间已慢，已满则移除 freQueue 堆顶元素，同时更新 freQueue、cache，未满则直接在 freQueue、cache 中插入元素

get 元素时，判断 cache 是否存在 key，不存在则直接返回 -1；存在则更新使用频率和最近使用时间，返回 value。

参考：https://leetcode.com/problems/lfu-cache/discuss/537093/Java-HashMap%2BPriorityQueue

```java
class LFUCache {

    Map<Integer, Node> cache;
    // 存储每个频率对应的双向链表
    private PriorityQueue<Node> freQueue;
    private static int timer;
    int capacity;
    class Node{
        int key;
        int value;
        int count;
        int timer;
		
		public Node(int key, int value, int count, int timer) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.timer = timer;
        }
    }
    /**
     * 最少使用淘汰算法
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        freQueue = new PriorityQueue<>(((o1, o2) ->
                (o1.count == o2.count ? o1.timer - o2.timer : o1.count - o2.count)));
        cache = new HashMap<>();
        timer = 0;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(new Node(node.key, node.value, node.count + 1, timer++));
            return node.value;
        } else {
            return -1;
        } 
    }

    private void remove(Node node) {
        cache.remove(node.key);
        freQueue.remove(node);
    }

    private void add(Node node) {
        cache.put(node.key, node);
        freQueue.offer(node);
    }
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(new Node(node.key, value, node.count + 1, timer++));
        } else {
            if (capacity != 0) {
                if (cache.size() == capacity) {
                    remove(freQueue.peek());
                }
                add(new Node(key, value, 1, timer++));
            }
        }                 
    }
}
```

