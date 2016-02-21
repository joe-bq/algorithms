package list;



/* leetcode: 
 * 	reorder list
 * 
 * 
 * problem link : https://leetcode.com/problems/reorder-list/
 * 
 * solution1: 
 *   recursive solution:
 *   	l0, l1, ... ,ln-1, ln
 *   
 *   in one recursion, we can have 
 *   	l0, ln, (l1,....ln-1)
 *   then we do the same recursion to the 
 *   	(l1, .. ln-1)
 *   
 *   then we will have the result 
 *   
 *   
 *  Solution 2:
 *  	1. we first find the middle of the List, with slow, and fast
 *  	2. starting from middle (slow) we reverse towards ends, and the tail is "one before fast", we name the head of reversed as tail. the middle shall points to null now
 *  	3. we start merging head and tail
 */


/* definition of the ListNode is as such */
/*

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

*/

public class ReorderList {
	public static void main(String[] args) { 
		ListNode head = new ListNode(1);
		ListNode p = head;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(4);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = new ListNode(6);
		p = p.next;
		p.next = new ListNode(7);
		p = p.next;
//		p.next = new ListNode(8);
//		p = p.next;
		
		
		ReorderList solution = new ReorderList();
		
		// solution.reorderList(head);
		solution.reorderList2(head);
		
		
		for (p = head; p != null; p = p.next) {
			System.out.print(p.val + " ");
		}
		System.out.println();
	}
	
	public void reorderList(ListNode head) { 
		recurseBuild(head);
	}
	
	public ListNode recurseBuild(ListNode head) {
		
		if (head != null && head.next != null) {
				ListNode tail = head.next;
				ListNode prev = head;
				while (tail != null && tail.next != null) {
					prev = prev.next;
					tail = tail.next;
				}
				
			prev.next = null;
			tail.next = recurseBuild(head.next);
			head.next = tail;
		}
		
		return head;
	}
	
	
	public void reorderList2(ListNode head) {
		/* another solution is to have both slow and fast point to 0 (head) at start, which may not sound right */
		ListNode slow = head;
		ListNode fast = head != null ? head.next : null;
		ListNode tail = null;

		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}
		}
		
		// reverse from slow to fast n/2 to n
		if (slow != null) { 
			ListNode p = null;
			ListNode q = slow.next;
			ListNode r = null;
			while (q != null && q.next != null) {
				r = q.next;
				q.next = p;
				p = q;
				q = r;
			}
			
			
			slow.next = null;
			if (q != null) {
				q.next = p;
			}
			
			tail = q;
		}
		
		
		// now we merge q and head
		ListNode merged = new ListNode(0);
		merged.next = head;		
		while (head != null) {
			merged.next  = head;
		    head  = head.next;
		    merged = merged.next;
		    if (tail != null) { 
		    	merged.next = tail;
		    	tail = tail.next;
		    	merged = merged.next;
		    }
		}
		
		
		
	}
	
	
	/* this is one input which caused the above code to time-out, the input is available in 
	 * 
	 * 	SampleInput.txt
	 * 
	 */
	public ListNode createListNode(String input) {
		/*
		 	 
		 */
		return null;
	}
	
}
