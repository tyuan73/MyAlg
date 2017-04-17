/**
 * Created by yuantian on 4/5/17.
 */

import java.util.*;

public class LRUCache146 {
    class DoublelyLinkedNode {
        int key, value;
        DoublelyLinkedNode pre, next;

        DoublelyLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }

    DoublelyLinkedNode head, tail;
    int capacity = 0;
    int size = 0;
    Map<Integer, DoublelyLinkedNode> map;

    private void remove(DoublelyLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.next = null;
        node.pre = null;

        size--;
    }

    private void add(DoublelyLinkedNode node) {
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;

        size++;
    }

    public LRUCache146(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();

        this.head = new DoublelyLinkedNode(0, 0);
        this.tail = new DoublelyLinkedNode(0, 0);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        DoublelyLinkedNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoublelyLinkedNode node = map.get(key);
        if (node == null) {
            node = new DoublelyLinkedNode(key, value);
            map.put(key, node);
        } else {
            node.value = value;
            remove(node);
        }
        add(node);
        if (size > capacity) {
            DoublelyLinkedNode rm = tail.pre;
            remove(rm);
            map.remove(rm.key);
        }
    }
}
