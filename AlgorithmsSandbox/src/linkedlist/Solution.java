package linkedlist;

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
	
	class ListNode { 
		int val;
		ListNode next;
		ListNode(int x) { 
			val = x;
			next = null;
		}
	}
	
    public boolean hasCycle(ListNode head) {
    	
    	ListNode p = head, q = head;
    	
    	while (p != null && q != null && p != q) { 
    		p = p.next;
    		q = q.next;
    		if (q != null) q = q.next;
    		
    	}
        return p == q && head != null;
    }
    
    public static void main(String[] args) { 
    	Solution solution = new Solution();
    	Solution.ListNode listnode = solution.new ListNode(1); // how to call constructor outside the class.
    	Solution.ListNode p = listnode;
    	p.next = solution.new ListNode(2);
    	p = p.next;
    	p.next = solution.new ListNode(3);
    	p = p.next;
    	p.next = listnode.next; // loop up
    	
    	System.out.println("the linked list has loop:" + solution.hasCycle(listnode));
    	
    }
}