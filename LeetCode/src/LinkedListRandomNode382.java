/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/9/17
 * Time: 11:02 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LinkedListRandomNode382 {
    private ListNode head;
    private Random rand = new Random();

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public LinkedListRandomNode382(ListNode head) {
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        ListNode pick = null;
        ListNode p = head;
        int count = 1;
        while (p != null) {
            if (rand.nextInt(count) == 0) {
                pick = p;
            }
            p = p.next;
            count++;
        }
        return pick.val;
    }

}
