//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
//
// 实现 LFUCache 类：
//
//
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量
//capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
//
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
//
//
//
// 示例：
//
//
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3
//
//
//
// 提示：
//
//
// 0 <= capacity <= 10⁴
// 0 <= key <= 10⁵
// 0 <= value <= 10⁹
// 最多调用 2 * 10⁵ 次 get 和 put 方法
//
// Related Topics 设计 哈希表 链表 双向链表 👍 512 👎 0


import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {

    private final int capacity;
    private int minFre;
    private HashMap<Integer, Integer> keyToFre;
    private HashMap<Integer, Integer> keyToVal;
    private HashMap<Integer, LinkedHashSet<Integer>> freToKey;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFre = 0;
        keyToVal = new LinkedHashMap<>();
        keyToFre = new LinkedHashMap<>();
        freToKey = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 更新 fre
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (keyToVal.containsKey(key)) {
            increaseFreq(key);
            keyToVal.put(key, value);
            return;
        }
        if (capacity <= keyToVal.size()) {
            removeMinFre();
        }
        keyToVal.put(key, value);
        keyToFre.put(key, 1);
        freToKey.putIfAbsent(1, new LinkedHashSet<>());
        freToKey.get(1).add(key);
        this.minFre = 1;
    }

    private void increaseFreq(int key) {
        Integer fre = keyToFre.get(key);
        keyToFre.put(key, fre + 1);
        freToKey.get(fre).remove(key);
        freToKey.putIfAbsent(fre + 1, new LinkedHashSet<>());
        freToKey.get(fre + 1).add(key);
        // 若当前 key 使用频率恰好是最小
        if (freToKey.get(fre).isEmpty()) {
            freToKey.remove(fre);
            if (fre == this.minFre) {
                this.minFre = fre + 1;
            }
        }
    }

    private void removeMinFre() {
        LinkedHashSet<Integer> keySet = freToKey.get(this.minFre);
        Integer deleteKey = keySet.iterator().next();
        keySet.remove(deleteKey);
        if (keySet.isEmpty()) {
            freToKey.remove(this.minFre);
        }
        keyToVal.remove(deleteKey);
        keyToFre.remove(deleteKey);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
