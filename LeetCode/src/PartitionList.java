/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode tail = new ListNode(-1);
        tail.next = head;
        head = tail;

        ListNode runner = tail;
        while(runner.next != null) {
            if(runner.next.val < x) {
                if(runner == tail) {
                    runner = runner.next;
                    tail = runner;
                } else {
                    ListNode l = runner.next;
                    runner.next = l.next;
                    l.next = tail.next;
                    tail.next = l;
                    tail = l;
                }
            } else
                runner = runner.next;
        }
        return head.next;
    }
}
