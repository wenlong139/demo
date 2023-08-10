package leetcode;

/**
 * @Author qinwenlong
 * @Date 2021/11/27
 **/
public class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode cur = head;
            ListNode pre = null;
            ListNode next = null;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode2.next=listNode3;
        listNode1.next=listNode2;

        Solution solution = new Solution();
        solution.reverseList(listNode1);

    }
}
