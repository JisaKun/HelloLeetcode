package DeleteNodeinaLinkedList;

/**
 * Created by Zhang Hongchuan on 2016/6/27.
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeinaLinkedList {

    public void deleteNode(ListNode node) {
        // code 237
        ListNode thisNode = node;
//        ListNode nextNode = node.next;
        while (true) {
            thisNode.val = thisNode.next.val;
            if (thisNode.next.next == null) {
                thisNode.next = null;
                break;
            } else {
                thisNode = thisNode.next;
            }
        }

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
