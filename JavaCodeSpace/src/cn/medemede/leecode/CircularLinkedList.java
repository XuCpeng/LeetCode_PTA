/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
            return false;
        if(head.next==head)
            return true;
        ListNode p=head.next;
        head.next=head;
        ListNode q;
        while(p.next!=null){
           if(p.next.next==head)
               return true;
            else{
                q=p.next;
                p.next=head;
                p=q;
            }
        }
        return false;
    }
}
