package com.example.leetcode;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2  缓存容量 );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * 通过次数55,078提交次数115,209
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LRUCache {
    DoublyLinkedListNode head, tail;
    HashMap<Integer, DoublyLinkedListNode> hashMap;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.hashMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        DoublyLinkedListNode node = this.hashMap.get(key);
        //存在key
        if (node != null) {
            remove(key);
            addHead(key, node.value);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        remove(key);
        addHead(key, value);
        if (this.size > this.capacity) {
            remove(tail.key);
        }
    }

    private void addHead(int key, int value) {
        DoublyLinkedListNode temp = head;
        head = new DoublyLinkedListNode(key, value);
        this.hashMap.put(key, head);
        if (tail == null) {
            tail = head;
        }
        if (temp != null) {
            temp.next = head;
            head.pre = temp;
        }
        size++;
    }

    private void remove(int key) {
        DoublyLinkedListNode node = this.hashMap.get(key);
        if (node == null) {
            return;
        }
        //如果删除的是尾
        if (node.equals(tail)) {
            tail = node.next;
            if (tail != null) {
                tail.pre = null;
            }
        }
        //如果删除的是头
        if (node.equals(head)) {
            head = node.pre;
            if (head != null) {
                head.next = null;
                ;
            }
        }
        size--;
        this.hashMap.put(key, null);
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
    }
}
