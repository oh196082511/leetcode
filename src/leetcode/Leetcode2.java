package leetcode;
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
//        请你将两个数相加，并以相同形式返回一个表示和的链表。
//
//        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//输入：l1 = [2,4,3], l2 = [5,6,4]
//        输出：[7,0,8]
//        解释：342 + 465 = 807.
//        示例 2：
//
//        输入：l1 = [0], l2 = [0]
//        输出：[0]
//        示例 3：
//
//        输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//        输出：[8,9,9,9,0,0,0,1]
//         
//
//        提示：
//
//        每个链表中的节点数在范围 [1, 100] 内
//        0 <= Node.val <= 9
//        题目数据保证列表表示的数字不含前导零

import leetcode.data.ListNode;

public class Leetcode2 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null || l2 == null) {
                return null;
            }
            // 都是非0有值的l1和l2
            return add(l1, l2, false);

        }

        private ListNode add(ListNode l1, ListNode l2, boolean carry) {
            if (l1 == null && l2 == null && carry) {
                return new ListNode(1);
            }
            if (l1 == null) {
                if (carry) {
                    l2.val+=1;
                    if (l2.val > 9) {
                        l2.val -= 10;
                        l2.next = add(l1, l2.next, true);
                    }
                }
                return l2;
            }
            if (l2 == null) {
                if (carry) {
                    l1.val+=1;
                    if (l1.val > 9) {
                        l1.val -= 10;
                        l1.next = add(l1.next, l2, true);
                    }
                }
                return l1;
            }
            // l1 l2 相加得到l3
            ListNode listNode = new ListNode();
            int val = l1.val + l2.val;
            if (carry) {
                val++;
            }
            if (val > 9) {
                listNode.val = val - 10;
                listNode.next = add(l1.next, l2.next, true);
            } else {
                listNode.val = val;
                listNode.next = add(l1.next, l2.next, false);
            }
            return listNode;
        }

    }
    public static void main(String[] args) {
        Leetcode2.Solution solution = new Leetcode2().new Solution();
        ListNode listNode1 = new ListNode();
        listNode1.val = 9;

        ListNode listNode2 = new ListNode();
        listNode2.val = 9;
        listNode2.next = new ListNode(9);

        ListNode listNode = solution.addTwoNumbers(listNode1, listNode2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}
